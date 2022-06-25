package com.camellibby.io.buffer;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class BufferApp {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        buffer.put("test\n".getBytes(StandardCharsets.UTF_8));
        buffer.flip();
        byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes, 0, buffer.limit());
        System.out.println(new String(bytes));
    }
}
