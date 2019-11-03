package sample;

import javafx.scene.paint.Color;

public class Door {
    private boolean isLocked;
    private Color doorColor;

    public Door(Color doorColor) {
        this.isLocked = true;
        this.doorColor = doorColor;
    }

    public Color getColor() {
        return doorColor;
    }
}
