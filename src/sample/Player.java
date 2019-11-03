package sample;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int row;
    private int column;
    private List<GameObject> collectedObjects = new ArrayList<>();

    public Player(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void moveDown(Map map) {
        if (row == map.getNumRows()-1) {
            return;
        }

        Field myPosition = map.getField(row, column);
        Field nextPosition = map.getField(row + 1, column);
        // if(no wand between my and next position) {row++;}
        //ich muss checken ob es ein wand ismy
        //myPos bottomwand ist von nextposTopwand
        //if ((myPosition == null || myPosition.isBottomWall() == false) && (nextPosition == null || nextPosition.isTopWall() == false))
        if (isFieldBottomSidePassable(myPosition) && isFieldTopSidePassable(nextPosition))
        {
            row++;
        }

        collectObject(map);
    }

    public void moveRight(Map map) {
        if (column == map.getNumColumns() - 1) {
            return;
        }

        Field myPosition = map.getField(row, column);
        Field nextPosition = map.getField(row, column+1);

        //if((myPosition == null || myPosition.isRightWall() == false) && (nextPosition == null || nextPosition.isLeftWall() == false))
        if(isFieldRightSidePassable(myPosition) && isFieldLeftSidePassable(nextPosition))
        {
            column++;
        }

        collectObject(map);
    }

    public void moveLeft(Map map) {
        if (column == 0) {
            return;
        }
        // MustiInterceptException
        Field myPosition = map.getField(row, column);
        Field nextPosition = map.getField(row, column-1);
        //if((myPosition == null || myPosition.isLeftWall() == false) && (nextPosition == null || nextPosition.isRightWall() == false)) {
        if (isFieldLeftSidePassable(myPosition) && isFieldRightSidePassable(nextPosition)) {
            column--;
        }

        collectObject(map);
    }

    public void moveUp(Map map) {
        if (row == 0) {
            return;
        }

        Field myPosition = map.getField(row, column);
        Field nextPosition = map.getField(row-1, column);
        //if((myPosition == null || myPosition.isTopWall() == false) && (nextPosition == null || nextPosition.isBottomWall() == false)) {
        if(isFieldTopSidePassable(myPosition) && isFieldBottomSidePassable(nextPosition)) {
            row--;
        }

        collectObject(map);
    }

    private boolean isFieldRightSidePassable(Field field) {
        if(field == null) {
            return true;
        }

        if(field.isRightWall()) {
            return false;
        }

        Door door = field.getRightDoor();
        if(door == null) {
            return true;
        }

        return hasKey(door);
    }

    private boolean isFieldLeftSidePassable(Field field) {
        if(field == null) {
            return true;
        }

        if(field.isLeftWall()) {
            return false;
        }

        Door door = field.getLeftDoor();
        if(door == null) {
            return true;
        }

        return hasKey(door);
    }

    private boolean isFieldTopSidePassable(Field field) {
        if(field == null) {
            return true;
        }

        if(field.isTopWall()) {
            return false;
        }

        Door door = field.getTopDoor();
        if(door == null) {
            return true;
        }

        return hasKey(door);
    }

    private boolean isFieldBottomSidePassable(Field field) {
        if(field == null) {
            return true;
        }

        if(field.isBottomWall()) {
            return false;
        }

        Door door = field.getBottomDoor();
        if(door == null) {
            return true;
        }

        return hasKey(door);
    }

    private boolean hasKey(Door door) {
        for(GameObject gameObject : collectedObjects)
        {
            if(!(gameObject instanceof Key)) {
                continue;
            }

            Key key = (Key)gameObject;
            if(door.getColor() == key.getKeyColor()) {
                return true;
            }
        }
        return false;
    }

    private void collectObject(Map map) {
        GameObject collectedObject = null;
        for(GameObject gameObject : map.getGameObjects()) {
            if(row == gameObject.getRow() && column == gameObject.getColumn()) {
                collectedObject = gameObject;
                break;
            }
        }

        if(collectedObject == null) {
            return;
        }

        collectedObjects.add(collectedObject);
        map.getGameObjects().remove(collectedObject);
    }

    /*
    public void moveUniversal(Map map, int diffRow, int diffColumn) {
        int nextRow = row + diffRow;
        int nextColumn = column + diffColumn;
        // Falls meine neue Position ware auser dem Spielbrett
        if(nextRow < 0 || nextRow >= map.getNumRows() || nextColumn < 0 || nextColumn >= map.getNumColumns()) {
            return;
        }

        Field myPosition = map.getField(row, column);
        Field nextPosition = map.getField(nextRow, nextColumn);

        if((myPosition == null || myPosition.isTopWall() == false) && (nextPosition == null || nextPosition.isBottomWall() == false)) {
            row--;
        }
    }
    */
}
