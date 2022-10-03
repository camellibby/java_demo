package com.camellibby.gateway;


import cn.hutool.http.HttpUtil;

public class HttpRequestRushApp {
    public static void main(String[] args){
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                System.out.println(HttpUtil.createGet("http://localhost:8863/foo/demo/index").execute().body());
            }).start();
        }
    }
}
