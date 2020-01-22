package com.game;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import java.util.HashMap;
import java.util.Map;

public class InputManager {

    private Map<KeyCode, Boolean> keys = new HashMap<>();
    private SimpleDirection yAxisDirection;
    private SimpleDirection xAxisDirection;
    private Wolf wolf;
    private Game game;

    public InputManager(Wolf wolf, Game game) {
        this.wolf = wolf;
        this.game = game;
    }

    void initKeyListeners(Scene scene) {
        scene.setOnKeyPressed(e -> keys.put(e.getCode(), true));
        scene.setOnKeyReleased(e -> keys.put(e.getCode(), false));
    }

    void readInput() {
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
            game.backToMenu();
        }
    }

    void move() {
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

    private boolean isPressed(KeyCode code) {
        return keys.getOrDefault(code, false);
    }

}
