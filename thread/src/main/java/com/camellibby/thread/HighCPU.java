package com.camellibby.thread;

public class HighCPU {
    static final Object lockOne = new Object();
    static final Object lockTwo = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            while (true){
                int num = 100;
                for (int i = 0; i < 1000; i++) {
                    num /= 10;
                    num *= 10;
                }
            }
        }).start();
    }
}

