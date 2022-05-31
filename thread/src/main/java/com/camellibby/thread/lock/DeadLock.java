package com.camellibby.thread.lock;

public class DeadLock {
    static final Object lockOne = new Object();
    static final Object lockTwo = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(new ThreadOne());
        Thread thread2 = new Thread(new ThreadTwo());
        thread1.start();
        thread2.start();
    }

    static class ThreadOne implements Runnable {

        @Override
        public void run() {
            try {
                synchronized (lockOne) {
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(3000);
                    synchronized (lockTwo) {
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(3000);
                    }
                }
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class ThreadTwo implements Runnable {

        @Override
        public void run() {
            try {
                synchronized (lockTwo) {
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(3000);
                    synchronized (lockOne) {
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(3000);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

