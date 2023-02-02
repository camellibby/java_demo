package com.camellibby.io.bio.single;

import java.io.*;
import java.net.Socket;

public class BioSingleClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8880);

        System.out.print("请输入你的名字: ");
        String input = new BufferedReader(new InputStreamReader(System.in)).readLine();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        //向服务器端发送一条消息
        writer.write(input + "\n");
        writer.flush();

        //读取服务器返回的消息
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // 阻塞
        String msg = reader.readLine();

        System.out.println("服务器：" + msg);
        socket.close();
    }
}
