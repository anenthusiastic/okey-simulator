package org.fatih.service;

import lombok.Getter;
import lombok.Setter;
import org.fatih.gametype.GameType;
import org.fatih.model.Player;
import org.fatih.model.Tile;

import java.util.*;

@Getter
@Setter
public class GameTable {
    private final GameType gametype;
    private final List<Player> players;
    private final TileDeck deck;
    private Tile okey;

    public GameTable(GameType gametype, TileDeck deck) {
        this.gametype = gametype;
        this.deck = deck;
        this.players = new ArrayList<>();
    }

    public void setUpTheGame() {
        deck.shuffleTileDeck();
        determineOkeyTile();
        createsPLayers(4);
        assignTilesToPlayers();
    }

    public List<Player> getTheBestHand() {
        players.forEach(player -> player.setScore(gametype.evaluateHand(player.getHand(), okey)));
        return players.stream().filter(player -> player.getScore() == players.stream().mapToLong(Player::getScore).max().getAsLong()).toList();
    }

    private void createsPLayers(int oyuncuSayisi) {
        for (int i = 1; i <= oyuncuSayisi; i++) {
            players.add(new Player("Oyuncu " + i));
        }
    }

    private void determineOkeyTile() {
        Tile gosterge = deck.pickTile();
        while (gosterge.isFalseOkey()) { //sahte okey gösterge olamaz.
            deck.putTileToHeadOfDeck(gosterge);
            gosterge =  deck.pickTile();
        }
        System.out.println("gösterge : " + gosterge.getId());
        if (gosterge.getNumber() == 13) { // göstergenin 13 olması durumunda 1 e dön
            setOkey(new Tile(gosterge.getId() - 12));
        } else {
            setOkey(new Tile(gosterge.getId() + 1));
        }
        System.out.println("okey : " + getOkey());
    }

    private void assignTilesToPlayers() {
        for (Player player : getPlayers()) {
            List<Tile> hand = new ArrayList<>();
            for (int i = 0; i < 14; i++) {
                hand.add(deck.pickTile());
            }
            player.setHand(hand);
            System.out.println(player.getName() + " oyuncusunun taşları : ");
            player.printHand();
        }

        int starterPLayerIndex = new Random().nextInt(players.size());
        Player starterPLayer = players.get(starterPLayerIndex);
        System.out.println("Başlangıç oyuncusu : " + starterPLayer.getName());
        starterPLayer.getHand().add(deck.pickTile()); //başlangıç oyuncusunun 15 taşı olması için bi taş ekle

        System.out.println("Taşlar oyunculara dağıtıldı. Taşların son hali : ");
        deck.printDeck();

        for (Player player : getPlayers()) {
            if (player.equals(starterPLayer)) {
                System.out.println("Başlangıç oyuncusunun taşları : ");
            } else {
                System.out.println(player.getName() + " oyuncusunun taşları : ");
            }
            player.printHand();
        }
    }




}
