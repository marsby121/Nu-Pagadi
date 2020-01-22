package com.game;

import javafx.animation.*;
import javafx.scene.image.Image;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.util.Random;

public class Egg extends Entity {

    private EggLine eggLine;
    private DoneRollingCommand doneCommand;
    private Direction direction;

    public Egg(Direction direction, DoneRollingCommand doneCommand) {
        this.doneCommand = doneCommand;
        this.direction = direction;
        setImage(new Image("/egg-right-3.png"));
        eggLine = EggLine.buildLine(direction);
        animate();
    }

    public Egg(DoneRollingCommand doneCommand) {
        this(Direction.getRandom(), doneCommand);
    }

    public void animate() {
        PauseTransition delay = new PauseTransition();
        delay.setDuration(Duration.seconds(Math.random()));

        FadeTransition fadeIn = getFadeTransition(.5,0,1);
        RotateTransition rotateTransition = getRotateTransition();
        PathTransition pathTransition = getPathTransition();
        FadeTransition fadeOut = getFadeTransition(.2, 1, 0);

        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(fadeIn,rotateTransition, pathTransition);

        SequentialTransition sequentialTransition = new SequentialTransition();
        sequentialTransition.getChildren().addAll(delay,parallelTransition, fadeOut);
        sequentialTransition.play();
        sequentialTransition.setOnFinished(e-> doneCommand.rollingDone(direction, this));
    }

    private FadeTransition getFadeTransition(double duration, double fromValue, double toValue) {
        FadeTransition fade = new FadeTransition();
        fade.setNode(this);
        fade.setDuration(Duration.seconds(duration));
        fade.setFromValue(fromValue);
        fade.setToValue(toValue);
        return fade;
    }

    private RotateTransition getRotateTransition() {
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setNode(this);
        rotateTransition.setDuration(Duration.seconds(2));
        Random random = new Random();
        int fromAngle = random.nextInt(360)+1;
        int toAngle = fromAngle + 360;
        if (direction == Direction.RIGHT_UP || direction == Direction.RIGHT_DOWN) {
            fromAngle = -fromAngle;
            toAngle = -toAngle;
        }
        rotateTransition.setFromAngle(fromAngle);
        rotateTransition.setToAngle(toAngle);
        return rotateTransition;
    }

    private PathTransition getPathTransition() {
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(2));
        pathTransition.setNode(this);
        Path path = new Path();
        path.getElements().add(new MoveTo(eggLine.getStartX(), eggLine.getStartY()));
        path.getElements().add(new LineTo(eggLine.getEndX(), eggLine.getEndY()));
        pathTransition.setPath(path);
        return pathTransition;
    }

}
