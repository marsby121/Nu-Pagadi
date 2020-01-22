package com.game;

import javafx.scene.image.ImageView;

public abstract class Entity extends ImageView {




    public void setProperties(int x, int y, int w, int h) {
        setTranslateX(x);
        setTranslateX(y);
        setFitWidth(w);
        setFitWidth(h);
    }

    public void setProperties(int x, int y) {
        setTranslateX(x);
        setTranslateY(y);

    }

}
