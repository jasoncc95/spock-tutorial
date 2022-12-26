package org.example;

public class ShapeFactory {
    private Renderer renderer;

    public ShapeFactory(Renderer renderer) {
        this.renderer = renderer;
    }

    public Polygon createDefaultPolygon() throws TooFewSidesException {
        return new Polygon(4);
    }

    public Renderer getRenderer() {
        return renderer;
    }
}
