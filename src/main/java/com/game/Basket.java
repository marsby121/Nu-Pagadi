package com.game;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Basket extends Entity {

    private Map<Direction, Image> images = new HashMap<>();
    private static final Direction INIT_DIRECTION = Direction.RIGHT_DOWN;
    private Direction direction;

    public Basket() {
        init();
        setDirection(INIT_DIRECTION);
    }

    public void setDirection(Direction direction){
        switch (direction) {
            case LEFT_UP:
                setProperties(355,290);
                break;
            case LEFT_DOWN:
                setProperties(345,360);
                break;
            case RIGHT_UP:
                setProperties(540,293);
                break;
            case RIGHT_DOWN:
                setProperties(535,363);
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
