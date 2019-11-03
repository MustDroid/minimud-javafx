package sample;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private Field[][] fields;
    private List<GameObject> gameObjects = new ArrayList<>();

    public Map(int rows, int columns) {
        fields = new Field[rows][columns];
    }

    public void setField(Field field) {
        fields[field.getRow()][field.getColumn()] = field;
    }

    public Field getField(int row, int column) {
        return fields[row][column];
    }

    public Field[][] getFields() {
        return fields;
    }

    public int getNumRows() {
        return fields.length;
    }

    public int getNumColumns() {
        return fields[0].length;
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }
}
