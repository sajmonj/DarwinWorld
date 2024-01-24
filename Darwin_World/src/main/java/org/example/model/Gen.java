package org.example.model;


public enum Gen {
    DEGREES_0("N"),
    DEGREES_45("NE"),
    DEGREES_90("E"),
    DEGREES_135("SE"),
    DEGREES_180("S"),
    DEGREES_225("SW"),
    DEGREES_270("W"),
    DEGREES_315("NW");

    private final String value;

    Gen(String value) {
        this.value = value;
    }

    public int getValue() {
        return switch (value){
            case "N" -> 0;
            case "NE" -> 1;
            case "E" -> 2;
            case "SE" -> 3;
            case "S" -> 4;
            case "SW" -> 5;
            case "W" -> 6;
            case "NW" -> 7;
            default -> throw new IllegalStateException("Unexpected value: " + value);
        };
    }

    @Override
    public String toString() {
        return value;
    }
}