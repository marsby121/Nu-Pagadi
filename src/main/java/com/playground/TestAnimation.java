package com.playground;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TestAnimation extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group group = new Group();

        Rectangle rectangle = new Rectangle(50,50,50,50);
        ImageView imageView = new ImageView("/egg-left-1.png");
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setNode(imageView);
        fadeTransition.setDuration(Duration.seconds(5));
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.1);
        fadeTransition.play();

        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(6));
        pathTransition.setNode(rectangle);
        Path path = new Path();
        path.getElements().add(new MoveTo(0, 0));
        path.getElements().add(new LineTo(250, 250));
        pathTransition.setPath(path);
        pathTransition.setCycleCount(Animation.INDEFINITE);
        pathTransition.setAutoReverse(true);
        group.getChildren().add(imageView);
        group.getChildren().add(rectangle);
        pathTransition.play();
        Scene scene = new Scene(group, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
