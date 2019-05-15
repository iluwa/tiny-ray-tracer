package com.github.iluwa.tinyraytracer;

import java.awt.*;

public final class Material {
    private final Color diffuseColor;
    private final double specularExponent;
    private final double refractiveIndex;
    private final float diffuseAlbedo;
    private final float specularAlbedo;
    private final float reflectAlbedo;
    private final float refractAlbedo;

    public Material(Color diffuseColor, double specularExponent, double refractiveIndex,
                    float diffuseAlbedo, float specularAlbedo,
                    float reflectAlbedo, float refractAlbedo) {
        this.diffuseColor = diffuseColor;
        this.specularExponent = specularExponent;
        this.refractiveIndex = refractiveIndex;
        this.diffuseAlbedo = diffuseAlbedo;
        this.specularAlbedo = specularAlbedo;
        this.reflectAlbedo = reflectAlbedo;
        this.refractAlbedo = refractAlbedo;
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

    public float getReflectAlbedo() {
        return reflectAlbedo;
    }

    public double getRefractiveIndex() {
        return refractiveIndex;
    }

    public float getRefractAlbedo() {
        return refractAlbedo;
    }
}
