package com.model;

import com.game.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecordManager {

    private static final String ARCHIVE_FILENAME = "archive.bin";
    private List<Player> ranking;

    public RecordManager() {
        ranking = new ArrayList<>();
        loadRanking();
    }

    private void loadRanking() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVE_FILENAME));
            ranking = (List<Player>) ois.readObject();
            ois.close();
            System.out.println(ranking);
        } catch (Exception e) {

        }
    }

    private void saveRanking() {
        try {
           FileOutputStream fos = new FileOutputStream(ARCHIVE_FILENAME);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(ranking);
            oos.flush();
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveRecord(Player player) {
        ranking.add(player);
        saveRanking();
    }

    public List<Player> getReloadAndGetPlayers() {
        loadRanking();
        Collections.sort(ranking);
        return ranking;
    }
}
