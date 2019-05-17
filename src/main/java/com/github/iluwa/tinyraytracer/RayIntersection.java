package com.github.iluwa.tinyraytracer;

import javafx.geometry.Point3D;

public final class RayIntersection {
    private final double dist;
    private final Point3D N;
    private final Point3D hit;
    private final Material material;

    public RayIntersection(double dist, Point3D N, Point3D hit, Material material) {
        this.dist = dist;
        this.N = N;
        this.hit = hit;
        this.material = material;
    }

    public double getDist() {
        return dist;
    }

    public Point3D getN() {
        return N;
    }

    public Point3D getHit() {
        return hit;
    }

    public Material getMaterial() {
        return material;
    }
}
