package com.github.iluwa.tinyraytracer;

import javafx.geometry.Point3D;

public final class Light {
    private final double intensivity;
    private final Point3D position;

    public Light(Point3D position, double intensivity) {
        this.intensivity = intensivity;
        this.position = position;
    }

    public double getIntensivity() {
        return intensivity;
    }

    public Point3D getPosition() {
        return position;
    }
}
