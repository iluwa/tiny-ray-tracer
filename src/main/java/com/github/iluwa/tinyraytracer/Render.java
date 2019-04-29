package com.github.iluwa.tinyraytracer;

import javafx.geometry.Point3D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public final class Render {
    private static final double FOV = Math.PI / 2;
    private final int width;
    private final int height;
    private Color[] frameBuffer;

    public Render(int width, int height) {
        this.width = width;
        this.height = height;
        frameBuffer = new Color[width * height];
    }

    public void run(Sphere sphere) {
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
                frameBuffer[i + j * width] = castRay(Point3D.ZERO, dir, sphere);
            }
        }
    }

    private Color castRay(Point3D orig, Point3D dir, Sphere sphere) {
        if (!sphere.rayIntersect(orig, dir)) {
            return new Color(0.2f, 0.7f, 0.8f);
        }
        return new Color(0.4f, 0.4f, 0.3f);
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
