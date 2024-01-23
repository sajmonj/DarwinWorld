package org.example.model;

import javafx.scene.paint.Paint;

public interface WorldElement {

    /**
     * Position of world element

     * @return return world element position.
     */
    Vector2d getPosition();
    /**
     * Icon of world element
     *
     * @return return world element icon.
     */
    Paint toIcon();
}
