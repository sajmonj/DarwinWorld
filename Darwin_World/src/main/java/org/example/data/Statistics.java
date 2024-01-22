package org.example.data;

public enum Statistics {
    MAP_TYPE(0),
    GENOM_TYPE(1),
    NUMBER_OF_ANIMALS(2),
    FIELD_WITH_GRASS(3),
    FREE_FIELDS(4),
    MOST_POPULAR_GENOTYPE(5),
    AVG_ANIMALS_ENERGY(6),
    AVG_LENGTH_OF_LIFE(7),
    AVG_NUMBER_OF_CHILDREN(8);
    private final int value;

    Statistics(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
