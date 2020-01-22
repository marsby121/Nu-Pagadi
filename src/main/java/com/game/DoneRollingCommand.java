package com.game;

public class DoneRollingCommand {

    private Basket basket;
    private Player player;
    private Game game;
    private Score score;

    public DoneRollingCommand(Basket basket, Player player, Game game, Score score) {
        this.basket = basket;
        this.player = player;
        this.game = game;
        this.score = score;
    }

    public void rollingDone(Direction direction, Egg egg) {
        if (basket.getDirection() == direction) {
            caught();
        } else {
            miss();
        }
        game.removeObject(egg);
    }

    private void caught() {
       player.addScore();
       score.setText(player.getScore()+"");

    }

    private void miss() {
        player.removeLives();

    }



}
