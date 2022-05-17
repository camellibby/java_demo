package com.camellibby.thread;

/**
 * @author luoxinliang
 */
public class UniqueThreadIdGenerator {

    // 线程局部整型变量
//    private static final ThreadLocal <Integer> uniqueNum =
//            new ThreadLocal < Integer > () {
//                @Override protected Integer initialValue() {
//                    return 0;
//                }
//            };

    private static int uniqueNum = 0;

    //变量值
    public static int getUniqueId() {
        return ++uniqueNum;
    }

    public static void main(String[] args) {
        UniqueThreadIdGenerator uniqueThreadId = new UniqueThreadIdGenerator();
        // 为每个线程生成一个唯一的局部标识
        TaskThread t1 = new TaskThread<>("custom-thread-1", uniqueThreadId);
        TaskThread t2 = new TaskThread<>("custom-thread-2", uniqueThreadId);
        TaskThread t3 = new TaskThread<>("custom-thread-3", uniqueThreadId);
        t1.start();
        t2.start();
        t3.start();
    }

}

