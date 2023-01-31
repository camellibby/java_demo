package com.camellibby.io.bio.single;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class BioSingleServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket server = new ServerSocket(8888);
        System.out.println("启动服务器....");
        // 阻塞
        Socket socket = server.accept();

        System.out.println("客户端[" + socket.getRemoteSocketAddress() + "]已连接到服务器");
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // 阻塞
        String msg = reader.readLine();
        System.out.println("客户端：" + msg);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        writer.write("你好, " + msg + "\n");
        writer.flush();
        server.close();
    }
}
