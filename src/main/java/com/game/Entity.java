package com.game;

import javafx.scene.image.ImageView;

public abstract class Entity extends ImageView {

    public void setProperties(int x, int y) {
        setTranslateX(x);
        setTranslateY(y);
    }

}
