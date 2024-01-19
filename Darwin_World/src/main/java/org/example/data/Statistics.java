package org.example.data;

public enum Statistics {
    NUMBER_OF_ANIMALS(0),
    FIELD_WITH_GRASS(1),
    FREE_FIELDS(2),
    MOST_POPULAR_GENOTYPE(3),
    AVG_ANIMALS_ENERGY(4),
    AVG_LENGTH_OF_LIFE(5),
    AVG_NUMBER_OF_CHILDREN(6);
    private final int value;

    Statistics(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
