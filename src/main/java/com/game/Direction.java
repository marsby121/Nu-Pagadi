package com.game;

import java.util.Random;

public enum  Direction {
    LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN;

    public static Direction getRandom() {
        return values()[new Random().nextInt(values().length)];
    }

}
