package org.fatih.gametype;

import org.fatih.model.Tile;

import java.util.List;

public interface GameType {
    long evaluateHand(List<Tile> hand, Tile okey);
}
