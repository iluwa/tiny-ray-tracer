package com.github.iluwa.tinyraytracer;

import java.io.IOException;

public class ApplicationRun {
    public static void main(String[] args) {
        System.out.println("Starting to render...");
        Render r = new Render(1024, 768);
        r.run();
        try {
            r.toFile("out/out.jpeg", "jpeg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Rendering is done.");
    }
}
