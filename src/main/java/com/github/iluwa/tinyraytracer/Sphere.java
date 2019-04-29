package com.github.iluwa.tinyraytracer;

import javafx.geometry.Point3D;

public final class Sphere {
    private final Point3D center;
    private final double radius;
    private final Material material;

    public Sphere(Point3D center, double radius, Material material) {
        this.center = center;
        this.radius = radius;
        this.material = material;
    }

    public Material getMaterial() {
        return material;
    }

    public RayIntersection rayIntersect(Point3D orig, Point3D dir) {
        Point3D l = center.subtract(orig);
        double tca = l.dotProduct(dir);
        double d2 = l.dotProduct(l) - tca * tca;
        if (d2 > radius) {
            return RayIntersection.NOT_INTERSECTED;
        }
        double thc = Math.sqrt(radius * radius - d2);
        double t0 = tca - thc;
        double t1 = tca + thc;
        if (t0 < 0) {
            //t0 = t1;
            return RayIntersection.NOT_INTERSECTED;
        }
        return new RayIntersection(true, t0);
    }
}
