package com.camellibby.thread.lock;

import java.text.MessageFormat;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author luoxinliang
 */
public class FairAndUnfairLock {

    ReentrantLock fairLock = new ReentrantLock();
    ReentrantLock unfairLock = new ReentrantLock(false);

    public void syncInvoke(int i) {
        synchronized (this) {
            try {
                System.out.print(MessageFormat.format("{0},", i));
                Thread.sleep(2);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void fairInvoke(int i) {
        fairLock.lock();
        try {
            System.out.print(MessageFormat.format("{0},", i));
            Thread.sleep(2);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            fairLock.unlock();
        }
    }

    public void unfairInvoke(int i) {
        unfairLock.lock();
        try {
            System.out.print(MessageFormat.format("{0},", i));
            Thread.sleep(2);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            unfairLock.unlock();
        }
    }

    public void invoke(int i) {
        System.out.println(MessageFormat.format("{0},", i));
        try {
            Thread.sleep(2);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    static ReentrantLock lock = new ReentrantLock(false);

    public static void main(String[] args) throws InterruptedException {
        FairAndUnfairLock fairAndUnfairLock = new FairAndUnfairLock();
        for (int i = 0; i < 50; i++) {
            int index = i;
            new Thread(() -> {
//                fairAndUnfairLock.invoke(index);
//                fairAndUnfairLock.syncInvoke(index);
//                fairAndUnfairLock.fairInvoke(index);
                fairAndUnfairLock.unfairInvoke(index);
            }).start();
            Thread.sleep(1);
        }
    }
}
