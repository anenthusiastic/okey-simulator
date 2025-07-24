package org.fatih.service;

import org.fatih.model.Tile;

import java.util.Random;
import java.util.Stack;


public class TileDeck {

    private final Stack<Tile> tiles = new Stack<>();

    public TileDeck() {
        createTileDeck();
    }

    private void createTileDeck() {
        for (int i = 0; i < 53; i++) {
            tiles.add(new Tile(i));
            tiles.add(new Tile(i));
        }
        System.out.println("Taşlar oluşturuldu.");
        printDeck();
    }


    public void shuffleTileDeck() {
        for (int i = tiles.size() - 1; i > 0; i--) {
            Tile lastTile = tiles.get(i);
            int leftIndex = new Random().nextInt(i + 1);
            tiles.set(i, tiles.get(leftIndex));
            tiles.set(leftIndex, lastTile);
        }
        System.out.println("Taşlar karıştırıldı.");
        printDeck();
    }

    public Tile pickTile() {
        if (tiles.isEmpty()) {
            throw new IllegalStateException("Deste boş, daha fazla taş çekilemez.");
        }
        return tiles.pop();
    }

    public void putTileToHeadOfDeck(Tile tile) {
        tiles.set(0, tile);
    }

    public void printDeck() {
        for (Tile tile : tiles) {
            System.out.print(tile.getId() + " ");
        }
        System.out.println();
    }

}
