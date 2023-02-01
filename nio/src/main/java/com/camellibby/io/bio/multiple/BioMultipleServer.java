package com.camellibby.io.bio.multiple;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BioMultipleServer {
    private static ExecutorService executorService = Executors.newFixedThreadPool(1);

    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(8888)) {
            System.out.println("启动服务器....");
            int i = 0;
            while (true) {
                // 阻塞
                Socket socket = server.accept();
                System.out.println("客户端:" + (i++) + socket.getRemoteSocketAddress() + "已连接到服务器");
                executorService.submit(new SocketHandler(socket));
            }
        }
    }

    public static class SocketHandler implements Runnable {
        private final Socket socket;

        public SocketHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    // 阻塞
                    String msg = reader.readLine();
                    System.out.println("客户端：" + msg);
                    Thread.sleep(1000);

                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    writer.write(("你好，" + msg + "\n"));
                    writer.flush();
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
