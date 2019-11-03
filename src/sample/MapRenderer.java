package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class MapRenderer {

    private final static int FIELD_PIXEL_SIZE = 70;
    private final static int WALL_PIXEL_SIZE = 5;
    private final static int PLAYER_PIXEL_SIZE = 40;

    public static void renderMap(Map map, Player player, GraphicsContext gc) {

        Field[][] fields = map.getFields();
        for(int row = 0; row < map.getNumRows(); row++) {
            for(int column = 0; column < map.getNumColumns(); column++) {
                if(fields[row][column] == null) {
                    continue;
                }

                Field field = fields[row][column];
                int offsetX = column*FIELD_PIXEL_SIZE;
                int offsetY = row*FIELD_PIXEL_SIZE;
                gc.setFill(Color.GREEN);
                gc.fillRect(offsetX, offsetY, FIELD_PIXEL_SIZE, FIELD_PIXEL_SIZE);
                gc.setFill(Color.BLACK);

                if(field.isTopWall()) {
                    gc.fillRect(offsetX, offsetY, FIELD_PIXEL_SIZE, WALL_PIXEL_SIZE);
                }

                if(field.isRightWall()) {
                    gc.fillRect(offsetX + FIELD_PIXEL_SIZE - WALL_PIXEL_SIZE, offsetY, WALL_PIXEL_SIZE, FIELD_PIXEL_SIZE);
                }

                if(field.isBottomWall()) {
                    gc.fillRect(offsetX, offsetY + FIELD_PIXEL_SIZE - WALL_PIXEL_SIZE, FIELD_PIXEL_SIZE, WALL_PIXEL_SIZE);
                }

                if(field.isLeftWall()) {
                    gc.fillRect(offsetX, offsetY, WALL_PIXEL_SIZE, FIELD_PIXEL_SIZE);
                }

                Door rightDoor = field.getRightDoor();
                if(rightDoor != null) {
                    gc.setFill(rightDoor.getColor());
                    gc.fillRect(offsetX + FIELD_PIXEL_SIZE - WALL_PIXEL_SIZE, offsetY, WALL_PIXEL_SIZE, FIELD_PIXEL_SIZE);
                }

                Door leftDoor = field.getLeftDoor();
                if(leftDoor != null) {
                    gc.setFill(leftDoor.getColor());
                    gc.fillRect(offsetX, offsetY, WALL_PIXEL_SIZE, FIELD_PIXEL_SIZE);
                }

                Door bottomDoor = field.getBottomDoor();
                if(bottomDoor != null) {
                    gc.setFill(bottomDoor.getColor());
                    gc.fillRect(offsetX, offsetY + FIELD_PIXEL_SIZE - WALL_PIXEL_SIZE, FIELD_PIXEL_SIZE, WALL_PIXEL_SIZE);
                }

                Door topDoor = field.getTopDoor();
                if (topDoor != null) {
                    gc.setFill(topDoor.getColor());
                    gc.fillRect(offsetX, offsetY, FIELD_PIXEL_SIZE, WALL_PIXEL_SIZE);
                }
            }
        }

        gc.setFill(Color.ORANGE);
        gc.fillOval(getCenterX(player.getColumn()) - PLAYER_PIXEL_SIZE / 2, getCenterY(player.getRow()) - PLAYER_PIXEL_SIZE / 2, PLAYER_PIXEL_SIZE, PLAYER_PIXEL_SIZE);

        for(GameObject gameObject : map.getGameObjects()) {
            gameObject.draw(gc);
        }
    }

    public static int getCenterX(int column) {
        return column*FIELD_PIXEL_SIZE + FIELD_PIXEL_SIZE / 2;
    }

    public static int getCenterY(int row) {
        return row*FIELD_PIXEL_SIZE + FIELD_PIXEL_SIZE / 2;
    }
}
