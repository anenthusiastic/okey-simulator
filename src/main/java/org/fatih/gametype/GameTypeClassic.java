package org.fatih.gametype;

import org.fatih.model.Tile;

import java.util.List;


import static org.fatih.util.HandEvaluationUtil.calculatePairScore;
import static org.fatih.util.HandEvaluationUtil.calculateSeriesScore;


public class GameTypeClassic implements GameType {

    @Override
    public long evaluateHand(List<Tile> hand, Tile okey) {
        long initialHandSize = hand.size();
        hand.removeIf(tile -> tile.equals(okey));
        long okeyCount = initialHandSize - hand.size();

        long pairScore = calculatePairScore(hand, okeyCount);
        long seriesScore = calculateSeriesScore(hand, okeyCount, okey);
        return Math.max(pairScore, seriesScore);
    }


}
