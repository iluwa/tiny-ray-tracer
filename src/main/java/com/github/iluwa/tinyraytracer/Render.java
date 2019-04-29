package com.github.iluwa.tinyraytracer;

import javafx.geometry.Point3D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public final class Render {
    private static final double FOV = Math.PI / 4;
    private final int width;
    private final int height;
    private Color[] frameBuffer;

    public Render(int width, int height) {
        this.width = width;
        this.height = height;
        frameBuffer = new Color[width * height];
    }

    public void run(List<Sphere> spheres) {
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                frameBuffer[i + j * width] = new Color((float) j / height, (float) i / width, 0);
            }
        }
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                double x = (2 * (i + 0.5d) / width - 1) * Math.tan(FOV / 2) * width / height;
                double y = -(2 * (j + 0.5d) / height - 1) * Math.tan(FOV / 2);
                Point3D dir = new Point3D(x, y, -1).normalize();
                frameBuffer[i + j * width] = castRay(Point3D.ZERO, dir, spheres);
            }
        }
    }

    private Color castRay(Point3D orig, Point3D dir, List<Sphere> spheres) {
        Optional<Sphere> intersectedSphere = Optional.ofNullable(sceneIntersect(orig, dir, spheres));
        if (!intersectedSphere.isPresent()) {
            return new Color(0.2f, 0.7f, 0.8f);
        }
        return intersectedSphere.get().getMaterial().getColor();
    }

    private Sphere sceneIntersect(Point3D orig, Point3D dir, List<Sphere> spheres) {
        double sphereDist = Double.MAX_VALUE;
        Sphere closestSphere = null;
        for (Sphere sphere : spheres) {
            RayIntersection rayIntersection = sphere.rayIntersect(orig, dir);
            if (rayIntersection.isIntersected() && rayIntersection.getDist() < sphereDist) {
                sphereDist = rayIntersection.getDist();
                closestSphere = sphere;
            }
        }
        return closestSphere;
    }

    public void toFile(String fileName, String fileFormat) throws IOException {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                image.setRGB(i, j, frameBuffer[i + j * width].getRGB());
            }
        }
        File output = new File(fileName);
        ImageIO.write(image, fileFormat, output);
    }
}
