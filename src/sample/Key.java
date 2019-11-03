package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Key extends GameObject {
    private static int KEY_PIXEL_SIZE = 10;

    private Color keyColor;

    public Key(int row, int column, Color keyColor) {
        super(row, column);
        this.keyColor = keyColor;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(keyColor);
        gc.fillOval(MapRenderer.getCenterX(column) - KEY_PIXEL_SIZE / 2, MapRenderer.getCenterY(row) - KEY_PIXEL_SIZE / 2, KEY_PIXEL_SIZE, KEY_PIXEL_SIZE);
    }

    public Color getKeyColor() {
        return keyColor;
    }
}
