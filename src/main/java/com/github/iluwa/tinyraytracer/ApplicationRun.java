package com.github.iluwa.tinyraytracer;

import javafx.geometry.Point3D;


import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationRun {
    public static void main(String[] args) {
        System.out.println("Starting to render...");
        Render r = new Render(1024, 768);

        Material ivory = new Material(new Color(0.4f, 0.4f, 0.3f), 50d, 0.6f, 0.3f);
        Material redRubber = new Material(new Color(0.3f, 0.1f, 0.1f), 10d, 0.9f, 0.2f);
        List<Sphere> spheres = new ArrayList<>();
        spheres.add(new Sphere(new Point3D(-3, 0, -16), 2, ivory));
        spheres.add(new Sphere(new Point3D(-1, -1.5, -12), 3.5, redRubber));
        spheres.add(new Sphere(new Point3D(1.5, -0.5, -18), 3, redRubber));
        spheres.add(new Sphere(new Point3D(5, 3, -18), 4, ivory));

        List<Light> lights = new ArrayList<>();
        lights.add(new Light(new Point3D(-20, 20, 20), 1.5));
        lights.add(new Light(new Point3D(30, 50, -25), 1.8));
        lights.add(new Light(new Point3D(30, 20, 30), 1.7));
        r.run(spheres, lights);
        try {
            r.toFile("out/out.jpeg", "jpeg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Rendering is done.");
    }
}
