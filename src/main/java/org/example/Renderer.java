package org.example;

public class Renderer {
    private Palette palette;

    public Renderer(Palette palette) {
        this.palette = palette;
    }

    public void drawLine(){

    }

    public Color getForegoundColor() {
        return palette.getPrimaryColor();
    }

}
