package com.github.iluwa.tinyraytracer;

import javafx.geometry.Point3D;

public final class Sphere {
    private Point3D center;
    private double radius;

    public Sphere(Point3D center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public boolean rayIntersect(Point3D orig, Point3D dir) {
        Point3D l = center.subtract(orig);
        double tca = l.dotProduct(dir);
        double d2 = l.dotProduct(l) - tca * tca;
        if (d2 > radius) {
            return false;
        }
        double thc = Math.sqrt(radius * radius - d2);
        double t0 = tca - thc;
        //double t1 = tca + thc;
        if (t0 < 0) {
            //t0 = t1;
            return false;
        }
        return true;
    }
}
