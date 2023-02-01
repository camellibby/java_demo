package com.camellibby.io.nio.accept;

import com.camellibby.io.bio.multiple.BioMultipleClient;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NioAcceptClient {
    private static ExecutorService executorService = Executors.newFixedThreadPool(1000);

    public static void main(String[] args) throws IOException, InterruptedException {
        for (int i = 0; i < 1; i++) {
            Socket socket = new Socket("127.0.0.1", 9999);
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
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                //向服务器端发送一条消息
                System.out.println("向服务器端发送一条消息");
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
