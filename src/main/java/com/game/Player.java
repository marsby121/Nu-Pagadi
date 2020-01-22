package com.game;

import java.io.Serializable;

public class Player implements Serializable, Comparable<Player> {

    private String name = "";
    private int score = 0;
    private int lives = 4;

    public void addScore() {
        score++;
    }

    public void removeLives() {
        lives--;
    }

    public boolean hasLives() {
        return lives > 0;
    }

    public int getScore() {
        return score;
    }

    public int getLives() {
        return lives;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%s - %d", name, score);
    }

    @Override
    public int compareTo(Player o) {
        return Integer.compare(o.score,score);
    }
}
