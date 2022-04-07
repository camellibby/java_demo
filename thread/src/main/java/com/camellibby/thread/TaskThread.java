package com.camellibby.thread;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author luoxinliang
 */
public class TaskThread<T> extends Thread {

    private T t;

    public TaskThread(String threadName, T t) {
        this.setName(threadName);
        this.t = t;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {

            try {
                Method method = t.getClass().getMethod("getUniqueId");
                int value = (int) method.invoke(t);
                System.out.println("thread[" + Thread.currentThread().getName() + "] --> uniqueId[" + value + "]");

            } catch (NoSuchMethodException e) {
                // TODO 暂不处理
                continue;

            } catch (IllegalAccessException e) {
                // TODO 暂不处理
                continue;

            } catch (InvocationTargetException e) {
                // TODO 暂不处理
                continue;

            }


        }
    }

}