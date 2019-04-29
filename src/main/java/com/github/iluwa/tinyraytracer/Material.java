package com.github.iluwa.tinyraytracer;

import java.awt.*;

public final class Material {
    private final Color color;

    public Material(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
