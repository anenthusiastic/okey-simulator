package org.fatih.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Player {
    private String name;
    private List<Tile> hand;
    private long score;

    public Player(String name) {
        this.name = name;
    }

    public void printHand() {
        for (Tile tile : hand) {
            System.out.print(tile.getId() + " ");
        }
        System.out.println();
    }
}
