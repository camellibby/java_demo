package com.camellibby.servlet.scan;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class DemoListener implements HttpSessionListener {

    public static long sessionCount = 0;

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        sessionCount++;
        System.out.println("创建session, 目前session数量为" + sessionCount);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        sessionCount--;
        System.out.println("销毁session, 目前session数量为" + sessionCount);
    }
}
