package org.example.data;

public enum Statistics {
    MAP_TYPE(0),
    GENOM_TYPE(1),
    DAY(2),
    NUMBER_OF_ALL_ANIMALS(3),
    NUMBER_OF_LIVING_ANIMALS(4),
    FIELD_WITH_GRASS(5),
    FREE_FIELDS(6),
    MOST_POPULAR_GENOTYPE(7),
    AVG_ANIMALS_ENERGY(8),
    AVG_LENGTH_OF_LIFE(9),
    AVG_NUMBER_OF_CHILDREN(10);
    private final int value;

    Statistics(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
