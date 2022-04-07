package com.camellibby.thread;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        HashClass hashClass = new HashClass();

        String key = "a";
        Thread t1 = new HashTread("1", hashClass, key);
        Thread t2 = new HashTread("2", hashClass, key);
        Thread t3 = new HashTread("3", hashClass, key);
        Thread t4 = new HashTread("4", hashClass, key);
        Thread t5 = new HashTread("5", hashClass, key);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        Thread.sleep(10);
        System.out.println(hashClass.get(key));
    }
}

class HashTread extends Thread {
    private HashClass hashClass;
    private String key;
    private String num;

    public HashTread(String num, HashClass hashclass, String key) {
        this.hashClass = hashclass;
        this.key = key;
        this.num = num;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            Integer value = hashClass.increase(key);
            System.out.println(num + ":" + value);
        }
    }
}

class HashClass {
    Map<String, Integer> map = new ConcurrentHashMap<>();

    public Integer get(String key) {
        if (!map.containsKey(key)) {
            map.put(key, 0);
        }
        return map.get(key);
    }

    public  Integer increase(String key) {
        Integer value = get(key) + 1;
        map.put(key, value);
        return value;
    }
}