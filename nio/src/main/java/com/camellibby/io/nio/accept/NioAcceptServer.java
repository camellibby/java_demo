package com.camellibby.io.nio.accept;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class NioAcceptServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocketChannel server = ServerSocketChannel.open();
        server.socket().bind(new InetSocketAddress(9999));
        server.configureBlocking(false);
        System.out.println("启动服务器....");
        while (true) {
            // 阻塞
            SocketChannel socketChannel = server.accept();
            if (socketChannel == null) {
                Thread.sleep(10);
                continue;
            }
            System.out.println("客户端:" + socketChannel.getRemoteAddress() + "已连接到服务器");
            new Thread(() -> {
                ByteBuffer buffer = ByteBuffer.allocateDirect(4096);
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
            }).start();
        }
    }
}
