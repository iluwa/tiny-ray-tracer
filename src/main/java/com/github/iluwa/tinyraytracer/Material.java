package com.github.iluwa.tinyraytracer;

import java.awt.*;

public final class Material {
    private final Color diffuseColor;
    private final double specularExponent;
    private final float diffuseAlbedo;
    private final float specularAlbedo;

    public Material(Color diffuseColor, double specularExponent, float diffuseAlbedo, float specularAlbedo) {
        this.diffuseColor = diffuseColor;
        this.specularExponent = specularExponent;
        this.diffuseAlbedo = diffuseAlbedo;
        this.specularAlbedo = specularAlbedo;
    }

    public Color getDiffuseColor() {
        return diffuseColor;
    }

    public double getSpecularExponent() {
        return specularExponent;
    }

    public float getDiffuseAlbedo() {
        return diffuseAlbedo;
    }

    public float getSpecularAlbedo() {
        return specularAlbedo;
    }
}
