package sample;

public class Field {
    private int row;
    private int column;

    //ich gehe nach urzeigesinn durch die variablen,deshalb diese anordnung
    private boolean topWall;
    private boolean rightWall;
    private boolean bottomWall;
    private boolean leftWall;

    private Door topDoor;
    private Door rightDoor;
    private Door bottomDoor;
    private Door leftDoor;

    public Field(int row, int column, boolean topWall, boolean rightWall, boolean bottomWall, boolean leftWall) {
        this.row = row;
        this.column = column;
        this.topWall = topWall;
        this.rightWall = rightWall;
        this.bottomWall = bottomWall;
        this.leftWall = leftWall;
    }

    public void setRightDoor(Door door) {
        rightDoor = door;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isTopWall() {
        return topWall;
    }

    public boolean isRightWall() {
        return rightWall;
    }

    public boolean isBottomWall() {
        return bottomWall;
    }

    public boolean isLeftWall() {
        return leftWall;
    }

    public Door getRightDoor() {
        return rightDoor;
    }

    public Door getTopDoor() {
        return topDoor;
    }

    public Door getBottomDoor() {
        return bottomDoor;
    }

    public Door getLeftDoor() {
        return leftDoor;
    }

    public void setTopDoor(Door Door) {
        topDoor = Door;
    }

    public void setBottomDoor(Door Door) {
        bottomDoor = Door;
    }

    public void setLeftDoor(Door Door) {
        leftDoor = Door;
    }
}
