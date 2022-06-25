package com.camellibby.io.bio.multiple;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BioServerMultiple {
    private static ExecutorService executorService = Executors.newFixedThreadPool(1000);

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8888);
        System.out.println("启动服务器....");
        while (true) {
            // 阻塞
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
                byte[] bytes = new byte[1024];
                // 阻塞
                socket.getInputStream().read(bytes, 0, 1024);
                String msg = new String(bytes);
                System.out.println(msg);
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write(("你好，" + msg).getBytes(StandardCharsets.UTF_8));
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
