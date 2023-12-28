package org.example.model;


public enum Gen {
    DEGREES_0(0),
    DEGREES_45(1),
    DEGREES_90(2),
    DEGREES_135(3),
    DEGREES_180(4),
    DEGREES_225(5),
    DEGREES_270(6),
    DEGREES_315(7);

    private final int value;

    Gen(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}