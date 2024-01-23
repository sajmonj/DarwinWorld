package org.example.model;

public interface WorldElement {

    /**
     * Position of world element

     * @return return world element position.
     */
    Vector2d position();
    /**
     * Icon of world element

     * @return return world element icon.
     */
    String toIcon();
}
