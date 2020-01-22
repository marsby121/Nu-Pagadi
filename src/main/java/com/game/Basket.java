package com.game;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Basket extends Entity {

    final int BASKET_POSITION_LEFT_UP_X = 355;
    final int BASKET_POSITION_LEFT_UP_Y = 290;

    final int BASKET_POSITION_LEFT_DOWN_X = 345;
    final int BASKET_POSITION_LEFT_DOWN_Y = 360;

    final int BASKET_POSITION_RIGHT_UP_X = 540;
    final int BASKET_POSITION_RIGHT_UP_Y = 293;

    final int BASKET_POSITION_RIGHT_DOWN_X = 535;
    final int BASKET_POSITION_RIGHT_DOWN_Y = 363;

    private Map<Direction, Image> images = new HashMap<>();
    private static final Direction INIT_DIRECTION = Direction.RIGHT_DOWN;
    private Direction direction;

    public Basket() {
        init();
        setDirection(INIT_DIRECTION);
    }

    public void setDirection(Direction direction) {
        switch (direction) {
            case LEFT_UP:
                setProperties(BASKET_POSITION_LEFT_UP_X, BASKET_POSITION_LEFT_UP_Y);
                break;
            case LEFT_DOWN:
                setProperties(BASKET_POSITION_LEFT_DOWN_X, BASKET_POSITION_LEFT_DOWN_Y);
                break;
            case RIGHT_UP:
                setProperties(BASKET_POSITION_RIGHT_UP_X, BASKET_POSITION_RIGHT_UP_Y);
                break;
            case RIGHT_DOWN:
                setProperties(BASKET_POSITION_RIGHT_DOWN_X, BASKET_POSITION_RIGHT_DOWN_Y);
                break;
        }
        setImage(images.get(direction));
        this.direction = direction;
    }

    void init() {
        images.put(Direction.LEFT_UP, new Image("/basket-left-up.png"));
        images.put(Direction.LEFT_DOWN, new Image("/basket-left-down.png"));
        images.put(Direction.RIGHT_UP, new Image("/basket-right-up.png"));
        images.put(Direction.RIGHT_DOWN, new Image("/basket-right-down.png"));
    }

    public Direction getDirection() {
        return direction;
    }
}
