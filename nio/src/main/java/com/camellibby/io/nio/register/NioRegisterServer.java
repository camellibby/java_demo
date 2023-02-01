package com.camellibby.io.nio.register;

import com.camellibby.io.nio.accept.NioAcceptServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NioRegisterServer {
    private static ExecutorService executorService = Executors.newFixedThreadPool(1);
    private static int i = 0;

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocketChannel server = ServerSocketChannel.open();
        server.socket().bind(new InetSocketAddress(9990), 1000);
        server.configureBlocking(false);
        Selector selector = Selector.open();
        server.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("启动服务器....");
        while (true) {
            // 阻塞
            if (selector.select() <= 0) {
                continue;
            }
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    acceptHandler(key, selector);
                }
                if (key.isReadable()) {
                    readHandler(key);
                }
                iterator.remove();
            }
        }
    }

    private static void acceptHandler(SelectionKey key, Selector selector) throws IOException {
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = ssc.accept();
        socketChannel.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.allocate(4096);
        socketChannel.register(selector, SelectionKey.OP_READ, buffer);
        System.out.println("客户端:" + (i++) + socketChannel.getRemoteAddress() + "已连接到服务器");

    }

    private static void readHandler(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        executorService.submit(new SocketHandler(buffer, socketChannel));
    }

    public static class SocketHandler implements Runnable {
        private final ByteBuffer buffer;
        private final SocketChannel socketChannel;

        public SocketHandler(ByteBuffer buffer, SocketChannel socketChannel) {
            this.buffer = buffer;
            this.socketChannel = socketChannel;
        }

        @Override
        public void run() {
            try {
                if (socketChannel.read(buffer) <= 0) {
                    return;
                }
                buffer.flip();
                byte[] bytes = new byte[buffer.limit()];
                buffer.get(bytes, 0, buffer.limit());
                String msg = new String(bytes);
                System.out.println("客户端：" + msg);
                buffer.clear();

                Thread.sleep(1000);

                buffer.put(("你好，" + msg).getBytes(StandardCharsets.UTF_8));
                buffer.flip();
                while (buffer.hasRemaining()) {
                    socketChannel.write(buffer);
                }
                buffer.clear();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
