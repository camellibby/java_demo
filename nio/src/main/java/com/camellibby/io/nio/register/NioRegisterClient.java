package com.camellibby.io.nio.register;

import java.io.*;
import java.net.Socket;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class NioRegisterClient {
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws IOException, InterruptedException {
        LocalDateTime start = LocalDateTime.now();
        for (int i = 0; i < 10; i++) {
            Socket socket = new Socket("127.0.0.1", 9990);
            executorService.submit(new SocketHandler(socket, "thread" + i));
            System.out.println(i);
        }
        LocalDateTime end = LocalDateTime.now();
        while (((ThreadPoolExecutor) executorService).getActiveCount() > 0) {
        }
        System.out.println("共耗时：" + Duration.between(start, end).toMillis() + "毫秒");
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
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                System.out.println(name + "向服务器端发送一条消息");
                writer.write(name + "\n");
                writer.flush();

                //读取服务器返回的消息
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                // 阻塞
                String msg = reader.readLine();
                System.out.println("服务器：" + msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
