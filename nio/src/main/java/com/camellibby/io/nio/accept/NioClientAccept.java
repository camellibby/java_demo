package com.camellibby.io.nio.accept;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class NioClientAccept {
    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            new Thread(() -> {
                try {
                    Socket socket = new Socket("127.0.0.1", 9999);
                    while (true) {
                        //向服务器端发送一条消息
                        socket.getOutputStream().write(LocalDateTime.now().toString().getBytes(StandardCharsets.UTF_8));
                        socket.getOutputStream().flush();
                        Thread.sleep(1000);
                        //读取服务器返回的消息
                        InputStream inputStream = socket.getInputStream();
                        byte[] bytes = new byte[1024];
                        inputStream.read(bytes, 0, bytes.length);
                        System.out.println("服务器：" + new String(bytes));
                    }
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
