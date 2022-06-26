package com.camellibby.io.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;

public class AioServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open().bind(new InetSocketAddress(9888));
        server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            @Override
            public void completed(AsynchronousSocketChannel channel, Object attachment) {
                try {
                    System.out.println("客户端:" + channel.getRemoteAddress() + "已连接到服务器");
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    channel.read(buffer, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                        @Override
                        public void completed(Integer result, ByteBuffer buffer) {
                            buffer.flip();
                            byte[] bytes = new byte[buffer.limit()];
                            buffer.get(bytes, 0, buffer.limit());
                            String msg = new String(bytes);
                            if (msg.length() == 0) {
                                return;
                            }
                            System.out.println("客户端：" + msg);
                            buffer.clear();
                            buffer.put(("你好，" + msg).getBytes(StandardCharsets.UTF_8));
                            buffer.flip();
                            while (buffer.hasRemaining()) {
                                channel.write(buffer);
                            }
                            buffer.clear();
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void failed(Throwable e, ByteBuffer buffer) {
                            e.printStackTrace();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable e, Object attachment) {
                e.printStackTrace();
            }
        });

        System.out.println("启动服务器....");
        Thread.sleep(Integer.MAX_VALUE);
    }
}
