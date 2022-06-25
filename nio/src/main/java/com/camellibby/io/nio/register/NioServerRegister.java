package com.camellibby.io.nio.register;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class NioServerRegister {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel server = ServerSocketChannel.open();
        server.socket().bind(new InetSocketAddress(9999), 1000);
        server.configureBlocking(false);
        Selector selector = Selector.open();
        server.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("启动服务器....");
        while (true) {
            if (selector.select() <= 0) {
                System.out.println("等待中....");
                continue;
            }
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    acceptHandler(key, selector);
                }
                if (key.isReadable()) {
                    readHandler(key);
                }
            }
        }
    }

    private static void acceptHandler(SelectionKey key, Selector selector) throws IOException {
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = ssc.accept();
        socketChannel.configureBlocking(false);
        ByteBuffer buffer = ByteBuffer.allocate(8192);
        socketChannel.register(selector, SelectionKey.OP_READ, buffer);
        System.out.println("-------------------------------------------");
        System.out.println("客户端:" + socketChannel.getRemoteAddress() + "已连接到服务器");
        System.out.println("-------------------------------------------");

    }

    private static void readHandler(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        try {
            while (true) {
                if (socketChannel.read(buffer) > 0) {
                    buffer.flip();
                    byte[] bytes = new byte[buffer.limit()];
                    buffer.get(bytes, 0, buffer.limit());
                    String msg = new String(bytes);
                    System.out.println("客户端：" + msg);

                    buffer.clear();
                    buffer.put(("你好，" + msg).getBytes(StandardCharsets.UTF_8));
                    buffer.flip();
                    while (buffer.hasRemaining()) {
                        socketChannel.write(buffer);
                    }
                    buffer.clear();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
