package com.game;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Wolf extends Entity {

    private static final Direction INIT_DIRECTION = Direction.RIGHT_UP;

    private Map<Direction, Image> images = new HashMap<>();
    private Basket basket;

    public Wolf(Basket basket) {
        this.basket = basket;
        init();
        setDirection(INIT_DIRECTION);

    }

    void init() {
        images.put(Direction.LEFT_UP, new Image("/wolf-left.png"));
        images.put(Direction.LEFT_DOWN, new Image("/wolf-left.png"));
        images.put(Direction.RIGHT_UP, new Image("/wolf-right.png"));
        images.put(Direction.RIGHT_DOWN, new Image("/wolf-right.png"));
    }

    public void setDirection(Direction direction) {
        switch (direction) {
            case LEFT_UP:
            case LEFT_DOWN:
                setProperties(400, 275);
                break;
            case RIGHT_UP:
            case RIGHT_DOWN:
                setProperties(465, 275);
                break;
        }
        setImage(images.get(direction));
        basket.setDirection(direction);
    }
}
