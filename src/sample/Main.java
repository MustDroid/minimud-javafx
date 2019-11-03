package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.HashMap;

public class Main extends Application {

    private HashMap<KeyCode, Boolean> pressedKeys = new HashMap<>();
    private Player player = new Player(0, 0);
    private Map map;
    private Scene scene;
    private Canvas canvas;
    private GraphicsContext gc;

    public void onKeyPress(KeyCode code) {
        if(code == KeyCode.DOWN) {
            player.moveDown(map);
        } else if (code == KeyCode.RIGHT) {
            player.moveRight(map);
        } else if(code == KeyCode.LEFT) {
            player.moveLeft(map);
        } else if(code == KeyCode.UP) {
            player.moveUp(map);
        }

        renderScene();
    }

    public void renderScene() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        MapRenderer.renderMap(map, player, gc);
    }

    @Override
    public void start(Stage stage) throws Exception {
        canvas = new Canvas(1000, 800);

        // Get the graphics context of the canvas
        gc = canvas.getGraphicsContext2D();

        map = new Map(10, 10);
        map.setField(new Field(0, 0, true, true, false, true));
        map.setField(new Field(1, 0, false, false, false, true));
        map.setField(new Field(2, 0, false, true, false, true));
        map.setField(new Field(3, 0, false, true, false, true));
        map.setField(new Field(4, 0, false, true, false, true));
        map.setField(new Field(5, 0, true, true, false, true));
        map.setField(new Field(6, 0, true, true, false, true));
        map.setField(new Field(7, 0, true, true, false, true));
        map.setField(new Field(8, 0, true, true, false, true));
        map.setField(new Field(9, 0, true, true, false, true));

        map.setField(new Field(1, 1, true, false, true, false));
        map.setField(new Field(1, 2, true, false, true, false));
        map.setField(new Field(1, 3, true, false, false, false));
        map.setField(new Field(1, 4, true, false, true, false));
        map.setField(new Field(1, 5, true, true, false, false));

        map.setField(new Field(2, 5, false, true, false, true));

        map.setField(new Field(3, 5, false, true, true, false));
        map.setField(new Field(3, 4, true, false, true, true));

        map.getGameObjects().add(new Key(2, 0, Color.CHOCOLATE));
        map.getGameObjects().add(new Key(3, 0, Color.PINK));
        map.getGameObjects().add(new Key(4, 0, Color.BLUE));
        map.getGameObjects().add(new Key(1, 5, Color.RED));

        map.getField(1, 2).setRightDoor(new Door(Color.CHOCOLATE));
        map.getField(1,4).setLeftDoor(new Door(Color.PINK));
        map.getField(1,5).setBottomDoor(new Door(Color.BLUE));
        map.getField(3,5).setTopDoor(new Door(Color.RED));

        renderScene();

        // Create the Pane
        Pane root = new Pane();
        // Add the Canvas to the Pane
        root.getChildren().add(canvas);
        // Create the Scene
        scene = new Scene(root);

        scene.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            if (!pressedKeys.containsKey(code) || pressedKeys.get(code) == false) {
                pressedKeys.put(code, true);
                onKeyPress(code);
            }
        });

        scene.setOnKeyReleased(event -> {
            pressedKeys.remove(event.getCode());
        });

        // Add the Scene to the Stage
        stage.setScene(scene);
        // Set the Title of the Stage
        stage.setTitle("Creation of a Canvas");
        // Display the Stage
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
