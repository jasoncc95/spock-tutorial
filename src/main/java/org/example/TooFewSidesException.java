package org.example;
public class TooFewSidesException extends Exception {
    private int numberOfSides;

    public TooFewSidesException(String message, int numberOfSides) {
        super(message);
        this.numberOfSides = numberOfSides;
    }

    public int getNumberOfSides() {
        return numberOfSides;
    }

}
