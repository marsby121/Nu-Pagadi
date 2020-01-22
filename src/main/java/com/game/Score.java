package com.game;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Score extends Text {


    public Score() {
        setText("0");
        setTranslateX(350);
        setTranslateY(225);

        setFont(Font.font("Verdana", FontWeight.BOLD, 50));
        setFill(Color.DARKGREEN.darker());
        setOpacity(.5);

    }
    
    
    
    
}
