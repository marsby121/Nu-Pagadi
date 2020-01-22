package com;

import com.controller.ViewFactory;
import javafx.application.Application;
import javafx.stage.Stage;

import static com.controller.WindowType.MENU;

public class Launcher extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        ViewFactory viewFactory = new ViewFactory();
        viewFactory.initializeStage(MENU);
    }
}
