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

public class Game extends Stage {

    public final static int WIDTH = 960;
    public final static int HEIGHT = 640;

    private Pane appRoot = new Pane();
    private Pane gameRoot = new Pane();
    private Player player;

    private int lvl = 1;

    private long deciseconds;

    private boolean running = true;

    private DoneRollingCommand doneRollingCommand;
    private RecordManager recordManager;
    private ViewFactory factory;
    private InputManager inputManager;

    public Game(RecordManager recordManager, ViewFactory factory) {
        this.recordManager = recordManager;
        this.factory = factory;
        initContent();
        Scene scene = new Scene(appRoot);
        setTitle("Wilczek");
        setScene(scene);
        inputManager.initKeyListeners(scene);
        startGameLoop();
    }

    private void startGameLoop() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (running) {
                    update();
                }
            }
        };
        timer.start();
    }

    private void initContent() {
        ImageView backGround = new ImageView("/background.png");
        Basket basket = new Basket();
        Wolf wolf = new Wolf(basket);
        inputManager = new InputManager(wolf, this);
        player = new Player();
        Group group = new Group();
        Score score = new Score();
        doneRollingCommand = new DoneRollingCommand(basket, player, this, score);

        gameRoot.getChildren().add(wolf);
        gameRoot.getChildren().add(basket);
        gameRoot.getChildren().add(group);
        gameRoot.getChildren().add(score);
        appRoot.getChildren().addAll(backGround, gameRoot);
    }

    private void update() {
        spawnEggs();
        inputManager.readInput();
        inputManager.move();
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

    private void levelUp() {
        if (player.getScore() > lvl * 10) {
            System.out.println("Lvl up! ");
            System.out.println(lvl);
            lvl++;
        }
    }

    private void endGame() {
        if (!player.hasLives()) {
            backToMenu();
            readNameAndSave();
        }
    }

    void backToMenu() {
        running = false;
        factory.closeStage(this);
        factory.initializeStage(WindowType.MENU);
    }

    private void readNameAndSave() {
        TextInputDialog window = new TextInputDialog();
        window.setTitle("Game ended!");
        window.setHeaderText("Game ended! You got: " + player.getScore() + " points!");
        window.setContentText("Enter your name:");
        Platform.runLater(window::show);
        window.setOnHidden(e -> savePlayer(window));
    }

    private void savePlayer(TextInputDialog window) {
        String name = window.resultProperty().get();
        if (name.isEmpty()) return;
        player.setName(name);
        recordManager.saveRecord(player);
    }

    public void removeObject(Node node) {
        gameRoot.getChildren().remove(node);
    }
}
