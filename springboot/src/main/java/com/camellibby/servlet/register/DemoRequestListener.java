package com.camellibby.servlet.register;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

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
