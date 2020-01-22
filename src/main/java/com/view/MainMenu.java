package com.view;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu extends Stage {

    private VBox root;
    private Scene scene;

    private Button start;
    private Button scores;
    private Button exit;

    public MainMenu() {
        root = new VBox(5);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(25));
        scene = new Scene(root);
        initComponents();
        setScene(scene);
        setResizable(false);
    }

    private void initComponents() {

        start = new Button("Start");
        scores = new Button("Scores");
        exit = new Button("Exit");

        start.setMaxWidth(200);
        scores.setMaxWidth(200);
        exit.setMaxWidth(200);

        root.getChildren().addAll(start, scores, exit);
    }

    public void addStartAction(EventHandler eventHandler) {
        start.setOnAction(eventHandler);
    }

    public void addScoresAction(EventHandler eventHandler) {
        scores.setOnAction(eventHandler);
    }

    public void addExitAction(EventHandler eventHandler) {
        exit.setOnAction(eventHandler);
    }
}
