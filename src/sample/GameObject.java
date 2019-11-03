package sample;

import javafx.scene.canvas.GraphicsContext;

public abstract class GameObject {
    protected int row;
    protected int column;

    public GameObject(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public abstract void draw(GraphicsContext gc);
}
