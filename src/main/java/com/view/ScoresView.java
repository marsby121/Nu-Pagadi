package com.view;

import com.game.Player;
import com.model.RecordManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ScoresView extends Stage {
    private VBox root;
    private Scene scene;
    private ListView<Player> list;
    private RecordManager recordManager;


    public ScoresView(RecordManager recordManager) {
        this.recordManager = recordManager;
        root = new VBox(5);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(25));
        scene = new Scene(root);
        initComponents();
        setTitle("Best scores: ");
        setScene(scene);
        setResizable(false);
    }

    private void initComponents() {
        list = new ListView<>();
        list.getItems().addAll(recordManager.getReloadAndGetPlayers());
        root.getChildren().add(list);
    }

}
