package com.camellibby.io.nio.accept;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class NioClient {
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                try {
                    Socket socket = new Socket("127.0.0.1", 9999);
                    //向服务器端发送一条消息
                    socket.getOutputStream().write((LocalDateTime.now() + "\n").getBytes(StandardCharsets.UTF_8));
                    socket.getOutputStream().flush();

                    //读取服务器返回的消息
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String msg = reader.readLine();

                    System.out.println("服务器：" + msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
