package com.camellibby.io.nio.accept;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NioServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocketChannel server = ServerSocketChannel.open();
        server.socket().bind(new InetSocketAddress(9999));
        server.configureBlocking(false);
        System.out.println("启动服务器....");
        while (true) {
            SocketChannel socketChannel = server.accept();
            if (socketChannel == null) {
                Thread.sleep(10);
            } else {
                System.out.println("客户端:" + socketChannel.getRemoteAddress() + "已连接到服务器");
                ByteBuffer buffer = ByteBuffer.allocateDirect(4096);
                int num = socketChannel.read(buffer);
                if (num > 0) {
                    buffer.flip();
                    byte[] bytes = new byte[buffer.limit()];
                    buffer.get(bytes);
                    System.out.println(new String(bytes));
                    buffer.clear();

                    socketChannel.write(buffer);
                }
            }
        }
    }
}
