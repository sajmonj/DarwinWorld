package org.example.model;

import java.util.Objects;

public record Grass(Vector2d position) implements WorldElement {

    @Override
    public String toString() {
        return "*"+position;
    }

    @Override
    public String toIcon() {
        return "#3A9D23";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grass grass = (Grass) o;
        return Objects.equals(position, grass.position);
    }

}

