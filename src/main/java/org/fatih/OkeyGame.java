package org.fatih;

import org.fatih.gametype.GameType;
import org.fatih.gametype.GameTypeClassic;
import org.fatih.model.Player;
import org.fatih.service.GameTable;
import org.fatih.service.TileDeck;

import java.util.ArrayList;
import java.util.List;

public class OkeyGame {
    public static void main(String[] args) {

        GameTable gameTable = new GameTable(new GameTypeClassic(), new TileDeck());
        gameTable.setUpTheGame();

    }


}