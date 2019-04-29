package com.github.iluwa.tinyraytracer;

import javafx.geometry.Point3D;

import java.io.IOException;

public class ApplicationRun {
    public static void main(String[] args) {
        System.out.println("Starting to render...");
        Render r = new Render(1024, 768);
        Sphere sphere = new Sphere(new Point3D(-3, 0, -16), 2);
        r.run(sphere);
        try {
            r.toFile("out/out.jpeg", "jpeg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Rendering is done.");
    }
}
