package com.game;

public  class EggLine {

    private final int startX;
    private final int startY;
    private final int endX;
    private final int endY;

    private EggLine(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    public static EggLine buildLine(Direction direction) {
        switch (direction) {
            case LEFT_UP:
                return new EggLine(280,253,360,300);
            case LEFT_DOWN:
                return new EggLine(280,327,360,380);
            case RIGHT_UP:
                return new EggLine(Game.WIDTH-270,253, Game.WIDTH-360,302);
            case RIGHT_DOWN:
                return new EggLine(Game.WIDTH-270,330, Game.WIDTH-360,380);

        }
        return null;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }
}




