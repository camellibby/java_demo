package com.camellibby.io.bio.multiple;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BioMultipleClient {
    private static ExecutorService executorService = Executors.newFixedThreadPool(1000);

    public static void main(String[] args) throws IOException, InterruptedException {
        for (int i = 0; i < 1000; i++) {
            Socket socket = new Socket("127.0.0.1", 8888);
            executorService.submit(new SocketHandler(socket, "thread" + i));
            System.out.println(i);
        }
        executorService.shutdown();
    }

    public static class SocketHandler implements Runnable {

        private Socket socket;
        private String name;

        public SocketHandler(Socket socket, String name) {
            this.socket = socket;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write((name).getBytes(StandardCharsets.UTF_8));
                outputStream.flush();
                byte[] bytes = new byte[1024];
                // 阻塞
                socket.getInputStream().read(bytes, 0, 1024);
                String msg = new String(bytes);
                System.out.println("服务器：" + msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
