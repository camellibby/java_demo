package com.camellibby.io.bio.mutiple;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BioServer {
    private static ExecutorService executorService = Executors.newFixedThreadPool(1000);

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8888);
        System.out.println("启动服务器....");
        while (true) {
            Socket socket = server.accept();
            System.out.println("客户端:" + socket.getRemoteSocketAddress() + "已连接到服务器");
            executorService.submit(new SocketHandler(socket));
        }
    }

    public static class SocketHandler implements Runnable {
        private Socket socket;

        public SocketHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                if (socket.isClosed()) {
                    return;
                }
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String msg = reader.readLine();
                System.out.println(msg);
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write(("你好，" + msg + "\n").getBytes(StandardCharsets.UTF_8));
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
