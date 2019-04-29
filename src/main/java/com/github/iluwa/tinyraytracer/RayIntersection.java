package com.github.iluwa.tinyraytracer;

public class RayIntersection {
    public final static RayIntersection NOT_INTERSECTED = new RayIntersection(false, 0);
    private final boolean isIntersected;
    private final double dist;

    public RayIntersection(boolean isIntersected, double dist) {
        this.isIntersected = isIntersected;
        this.dist = dist;
    }

    public boolean isIntersected() {
        return isIntersected;
    }

    public double getDist() {
        return dist;
    }
}
