module Ruskie {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires gson;

    opens com.controller;
    opens com.view;
    opens com.game;
    opens com;

}