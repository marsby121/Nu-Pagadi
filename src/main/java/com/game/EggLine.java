package com.game;

//todo lombok
public  class EggLine {

   private static final int LEFT_UP_START_X = 280;
   private static final int LEFT_UP_START_Y = 253;
   private static final int LEFT_DOWN_START_X = 280;
   private static final int LEFT_DOWN_START_Y = 327;
   private static final int RIGHT_UP_START_X = Game.WIDTH-270;
   private static final int RIGHT_UP_START_Y = 253;
   private static final int RIGHT_DOWN_START_X = Game.WIDTH-270;
   private static final int RIGHT_DOWN_START_Y = 330;

   private static final int LEFT_UP_END_X = 360;
   private static final int LEFT_UP_END_Y = 300;
   private static final int LEFT_DOWN_END_X = 360;
   private static final int LEFT_DOWN_END_Y = 380;
   private static final int RIGHT_UP_END_X = Game.WIDTH-360;
   private static final int RIGHT_UP_END_Y = 302;
   private static final int RIGHT_DOWN_END_X = Game.WIDTH-360;
   private static final int RIGHT_DOWN_END_Y = 380;

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
                return new EggLine(LEFT_UP_START_X, LEFT_UP_START_Y, LEFT_UP_END_X, LEFT_UP_END_Y);
            case LEFT_DOWN:
                return new EggLine(LEFT_DOWN_START_X, LEFT_DOWN_START_Y, LEFT_DOWN_END_X, LEFT_DOWN_END_Y);
            case RIGHT_UP:
                return new EggLine(RIGHT_UP_START_X, RIGHT_UP_START_Y, RIGHT_UP_END_X, RIGHT_UP_END_Y);
            case RIGHT_DOWN:
                return new EggLine(RIGHT_DOWN_START_X, RIGHT_DOWN_START_Y, RIGHT_DOWN_END_X, RIGHT_DOWN_END_Y);

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




