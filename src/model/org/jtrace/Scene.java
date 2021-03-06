package org.jtrace;

import static java.util.Arrays.asList;

import java.util.LinkedHashSet;
import java.util.Set;

import org.jtrace.cameras.Camera;
import org.jtrace.geometry.GeometricObject;
import org.jtrace.lights.Light;
import org.jtrace.primitives.ColorRGB;

/**
 * Represents the scene to be rendered.
 * 
 * A scene is a collection of {@link GeometricObject}, {@link Light} and a single {@link Camera}.<br>
 * 
 * The scene is passed to the {@link Tracer#render(Scene, ViewPlane)} method in order to be rendered by that {@link Tracer}<br>
 * 
 * All {@link GeometricObject} and {@link Light} added are stored in a {@link LinkedHashSet}.
 * 
 * @author raphaelpaiva
 *
 */
public class Scene {
    private final Set<GeometricObject> objects = new LinkedHashSet<GeometricObject>();
    private final Set<Light> lights = new LinkedHashSet<Light>();
    private ColorRGB backgroundColor = ColorRGB.BLACK;
    private Camera camera;

    public Scene withBackground(final ColorRGB color) {
        backgroundColor = color;
        return this;
    }

    public Scene add(final Light light) {
        lights.add(light);
        return this;
    }

    public Scene add(final Light... paramLights) {
        lights.addAll(asList(paramLights));
        return this;
    }

    public Scene add(final GeometricObject object) {
        objects.add(object);
        return this;
    }

    public Scene add(final GeometricObject... paramObjects) {
        objects.addAll(asList(paramObjects));
        return this;
    }

    public Set<GeometricObject> getObjects() {
        return objects;
    }

    public Set<Light> getLigths() {
        return lights;
    }

    public ColorRGB getBackgroundColor() {
        return backgroundColor;
    }

    public Camera getCamera() {
        return camera;
    }

    public Scene setCamera(final Camera camera) {
        this.camera = camera;
        return this;
    }

}
