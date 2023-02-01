package com.camellibby.io.nio.accept;

import java.io.*;
import java.net.Socket;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class NioAcceptClient {
    private static ExecutorService executorService = Executors.newFixedThreadPool(1);

    public static void main(String[] args) throws IOException, InterruptedException {
        LocalDateTime start = LocalDateTime.now();
        for (int i = 0; i < 2; i++) {
            Socket socket = new Socket("127.0.0.1", 9999);
            executorService.submit(new SocketHandler(socket, "thread" + i));
            System.out.println(i);
        }
        while (((ThreadPoolExecutor) executorService).getActiveCount() > 0) {
        }
        LocalDateTime end = LocalDateTime.now();
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
                for (int i = 0; i < 3; i++) {
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    System.out.println(name + "第" + i + "次" + "向服务器端发送一条消息");
                    writer.write(name + "\n");
                    writer.flush();

                    //读取服务器返回的消息
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    // 阻塞
                    String msg = reader.readLine();
                    System.out.println("服务器：" + msg);
                }
                System.out.println(name + "结束");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
