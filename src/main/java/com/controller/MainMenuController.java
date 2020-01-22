package com.controller;

import com.view.MainMenu;

public class MainMenuController {

    private MainMenu mainMenu;
    private ViewFactory viewFactory;


    public MainMenuController(MainMenu mainMenu, ViewFactory viewFactory) {
        this.mainMenu = mainMenu;
        this.viewFactory = viewFactory;
        init();
    }

    private void init() {
        mainMenu.addStartAction(e -> startAction());
        mainMenu.addScoresAction(e -> scoresAction());
        mainMenu.addExitAction(e -> exitAction());
    }

    private void startAction() {
        viewFactory.initializeStage(WindowType.GAME);
        viewFactory.closeStage(mainMenu);
    }

    private void scoresAction() {
        viewFactory.initializeStage(WindowType.SCORES);
    }

    private void exitAction() {
        System.exit(0);
    }
}
