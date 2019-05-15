package com.github.iluwa.tinyraytracer;

public final class RayIntersection {
    public static final RayIntersection NOT_INTERSECTED = new RayIntersection(false, Double.MAX_VALUE, null);
    private final boolean isIntersected;
    private final double dist;
    private final Sphere sphere;

    public RayIntersection(boolean isIntersected, double dist, Sphere sphere) {
        this.isIntersected = isIntersected;
        this.dist = dist;
        this.sphere = sphere;
    }

    public boolean isIntersected() {
        return isIntersected;
    }

    public double getDist() {
        return dist;
    }

    public Sphere getSphere() {
        return sphere;
    }
}
