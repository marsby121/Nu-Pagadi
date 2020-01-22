package com.game;

import com.controller.ViewFactory;
import com.controller.WindowType;
import com.model.RecordManager;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.*;

enum SimpleDirection {
    LEFT, RIGHT, UP, DOWN
}

public class Game extends Stage {

    public final static int WIDTH = 960;
    public final static int HEIGHT = 640;

    private Pane appRoot = new Pane();
    private Pane gameRoot = new Pane();
    private Wolf wolf;
    private Player player;

    private int lvl = 1;
    private AnimationTimer timer;

    private Map<KeyCode, Boolean> keys = new HashMap<>();
    private SimpleDirection yAxisDirection;
    private SimpleDirection xAxisDirection;

    private long deciseconds;

    private boolean running = true;

    private DoneRollingCommand doneRollingCommand;
    private RecordManager recordManager;
    private ViewFactory factory;


    public Game(RecordManager recordManager, ViewFactory factory) {
        this.recordManager = recordManager;
        this.factory = factory;
        initContent();
        Scene scene = new Scene(appRoot);
        setTitle("Wilczek");
        setScene(scene);
        initKeyListeners(scene);
        startGameLoop();
        //dodanie sterowania z klawiatury


    }

    private void startGameLoop() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (running) {
                    update();
                }
            }
        };
        timer.start();
    }

    private void initKeyListeners(Scene scene) {
        scene.setOnKeyPressed(e -> keys.put(e.getCode(), true));
        scene.setOnKeyReleased(e -> keys.put(e.getCode(), false));
    }


    private void initContent() {
        ImageView backGround = new ImageView("/background.png");
        Basket basket = new Basket();
        Wolf wolf = new Wolf(basket);
        this.wolf = wolf;
        player = new Player();
        Group group = new Group();
        Score score = new Score();
        doneRollingCommand = new DoneRollingCommand(basket, player, this,score);

        gameRoot.getChildren().add(wolf);
        gameRoot.getChildren().add(basket);
        gameRoot.getChildren().add(group);
        gameRoot.getChildren().add(score);
        appRoot.getChildren().addAll(backGround, gameRoot);
    }


    private void update() {
        spawnEggs();
        readInput();
        move();
        endGame();
        levelUp();
    }

    private void spawnEggs() {

        long newDeci = System.currentTimeMillis() / 100;
        if (newDeci == deciseconds) {
            return;
        }
        deciseconds = newDeci;
        if (deciseconds % (15 - lvl) == 0) {
            gameRoot.getChildren().add(new Egg(doneRollingCommand));
        }
    }

    private void readInput() {
        if (isPressed(KeyCode.W) || isPressed(KeyCode.UP)) {
            yAxisDirection = SimpleDirection.UP;
        }
        if (isPressed(KeyCode.S) || isPressed(KeyCode.DOWN)) {
            yAxisDirection = SimpleDirection.DOWN;
        }
        if (isPressed(KeyCode.A) || isPressed(KeyCode.LEFT)) {
            xAxisDirection = SimpleDirection.LEFT;
        }
        if (isPressed(KeyCode.D) || isPressed(KeyCode.RIGHT)) {
            xAxisDirection = SimpleDirection.RIGHT;
        }
        if (isPressed(KeyCode.CONTROL) && isPressed(KeyCode.SHIFT) && isPressed(KeyCode.Q)) {
            backToMenu();
        }
    }

    private void levelUp() {
        if (player.getScore() > lvl * 10) {
            System.out.println("Lvl up! ");
            System.out.println(lvl);
            lvl++;
        }
    }

    private void move() {
        if (yAxisDirection == SimpleDirection.UP) {
            if (xAxisDirection == SimpleDirection.LEFT) {
                wolf.setDirection(Direction.LEFT_UP);
            } else {
                wolf.setDirection(Direction.RIGHT_UP);
            }
        } else {
            if (xAxisDirection == SimpleDirection.LEFT) {
                wolf.setDirection(Direction.LEFT_DOWN);
            } else {
                wolf.setDirection(Direction.RIGHT_DOWN);
            }
        }
    }

    private void endGame() {
        if (!player.hasLives()) {
            backToMenu();
            readNameAndSave();
        }
    }

    private void backToMenu() {
        running = false;
        factory.closeStage(this);
        factory.initializeStage(WindowType.MENU);
    }

    private void readNameAndSave() {
        TextInputDialog window = new TextInputDialog();
        window.setTitle("Game ended!");
        window.setHeaderText("Game ended! You got: " + player.getScore() + " points!");
        window.setContentText("Enter your name:");
        Platform.runLater(() -> window.show());
        window.setOnHidden(e -> savePlayer(window));

    }

    private void savePlayer(TextInputDialog window) {
        String name = window.resultProperty().get();
        if (name.isEmpty()) return;
        player.setName(name);
        recordManager.saveRecord(player);
    }



    private boolean isPressed(KeyCode code) {
        return keys.getOrDefault(code, false);
    }


    public void removeObject(Node node) {
        gameRoot.getChildren().remove(node);
    }
}
