package com.github.iluwa.tinyraytracer;

import javafx.geometry.Point3D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

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

    public void run(List<Sphere> spheres, List<Light> lights) {
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
                frameBuffer[i + j * width] = castRay(Point3D.ZERO, dir, spheres, lights);
            }
        }
    }

    private Color castRay(Point3D orig, Point3D dir, List<Sphere> spheres, List<Light> lights) {
        RayIntersection rayIntersection = sceneIntersect(orig, dir, spheres);
        if (!rayIntersection.isIntersected()) {
            return new Color(0.2f, 0.7f, 0.8f);
        }
        float diffuseLightIntensivity = 0;
        float specularLigthIntensivity = 0;
        for (Light light : lights) {
            Point3D hit = orig.add(dir.multiply(rayIntersection.getDist()));
            Point3D n = hit.subtract(rayIntersection.getSphere().getCenter()).normalize();
            Point3D lightDir = light.getPosition().subtract(hit).normalize();
            diffuseLightIntensivity += light.getIntensivity() * Math.max(0d, lightDir.dotProduct(n));
            specularLigthIntensivity += light.getIntensivity() * Math.pow(
                    Math.max(0d, -reflect(lightDir, n).dotProduct(dir)),
                    rayIntersection.getSphere().getMaterial().getSpecularExponent());
        }
        Material material = rayIntersection.getSphere().getMaterial();
        return new Color(
                calculateColorComponent(material.getDiffuseColor().getRed(), material,
                        diffuseLightIntensivity, specularLigthIntensivity),
                calculateColorComponent(material.getDiffuseColor().getGreen(), material,
                        diffuseLightIntensivity, specularLigthIntensivity),
                calculateColorComponent(material.getDiffuseColor().getBlue(), material,
                        diffuseLightIntensivity, specularLigthIntensivity));

    }

    private float calculateColorComponent(int baseColorComponent,
                                        Material material,
                                        float diffuseLightIntensivity,
                                        float specularLigthIntensivity) {
        return Math.min(1, baseColorComponent * diffuseLightIntensivity * material.getDiffuseAlbedo() / 255f +
                specularLigthIntensivity * material.getSpecularAlbedo());
    }

    private RayIntersection sceneIntersect(Point3D orig, Point3D dir, List<Sphere> spheres) {
        RayIntersection rayIntersection = RayIntersection.NOT_INTERSECTED;
        for (Sphere sphere : spheres) {
            RayIntersection ri = sphere.rayIntersect(orig, dir);
            if (ri.isIntersected() && ri.getDist() < rayIntersection.getDist()) {
                rayIntersection = ri;
            }
        }
        return rayIntersection;
    }

    private Point3D reflect(Point3D I, Point3D N) {
        return I.subtract(N.multiply(2).crossProduct(I.crossProduct(N)));
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
