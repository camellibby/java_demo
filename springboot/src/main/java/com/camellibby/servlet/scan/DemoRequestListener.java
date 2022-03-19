package com.camellibby.servlet.scan;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class DemoRequestListener implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent requestEvent) {
        System.out.println("Request listener is initialized");
    }

    @Override
    public void requestDestroyed(ServletRequestEvent requestEvent) {
        System.out.println("Request listener is destroyed");
    }
}
