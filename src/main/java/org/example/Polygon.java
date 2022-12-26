package org.example;

public class Polygon {
    private int numberOfSides;
    private Renderer renderer;
    public Polygon(int numberOfSides) throws TooFewSidesException {
        if (numberOfSides <= 2) {
            throw new TooFewSidesException("Cant create a polygon with less than 3 sides", numberOfSides);
        }
        this.numberOfSides = numberOfSides;
    }

    public Polygon(int numberOfSides, Renderer renderer) throws TooFewSidesException {
        this(numberOfSides);
        this.renderer = renderer;
    }

    public int getNumberOfSides() {
        return numberOfSides;
    }

    public void draw() {
        for (int i = 0; i < numberOfSides; i++) {
            renderer.drawLine();
        }
    }

}
