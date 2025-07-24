package org.fatih.util;

import org.fatih.enumz.TileColor;
import org.fatih.model.Serie;
import org.fatih.model.Tile;

import java.util.*;
import java.util.stream.Collectors;

public class HandEvaluationUtil {

    public static long calculateSeriesScore(List<Tile> hand, long okeyCount, Tile okey) {  //per'leri hesapla
        adjustFalseOkeys(hand, okey);
        Map<TileColor, List<Tile>> colorGroups = hand.stream()
                .collect(Collectors.groupingBy(Tile::getColor));

        Map<TileColor, List<Serie>> numberSeriesList = new HashMap<>();
        for (TileColor color : colorGroups.keySet()) {
            numberSeriesList.put(color, new ArrayList<>());
            List<Tile> group = colorGroups.get(color);
            List<Tile> sorted = group.stream().sorted().toList();
            int count = 1;
            List<Tile> serieTile = new ArrayList<>();
            for (int i = 1; i < sorted.size(); i++) {
                if (sorted.get(i).getNumber() == sorted.get(i - 1).getNumber() + 1) {
                    count++;
                    if (count == 3) {
                        serieTile = new ArrayList<>();
                        serieTile.add(sorted.get(i-2));
                        serieTile.add(sorted.get(i-1));
                        serieTile.add(sorted.get(i));

                        group.remove(sorted.get(i-2));
                        group.remove(sorted.get(i-1));
                        group.remove(sorted.get(i));

                        Serie numberSerie = new Serie(serieTile);

                        numberSeriesList.get(color).add(numberSerie);
                    } else if (count > 3 ) {
                        serieTile.add(sorted.get(i));
                        group.remove(sorted.get(i));
                    }
                } else {
                    count = 1;
                }
            }
        }

    }

    private static void adjustFalseOkeys(List<Tile> hand, Tile okey) {
        for (Tile tile : hand) {
            if(tile.isFalseOkey()) {
                tile.setColor(okey.getColor());
                tile.setNumber(okey.getNumber());
            }
        }
    }

    public static long calculatePairScore(List<Tile> hand, long okeyCount) { //çiftleri hesapla
        Map<Integer, Long> freqMap = hand.stream()
                .collect(Collectors.groupingBy(Tile::getId, Collectors.counting()));

        long pairCount = freqMap.values().stream().filter(v -> v == 2).count();
        long remainedTile = hand.size() - pairCount;

        long pairWithOkey = getPairWithOkey(okeyCount, remainedTile);
        return pairCount + pairWithOkey;
    }

    private static long getPairWithOkey(long okeyCount, long remainedTile) {
        long pairWithOkey = 0;
        if (remainedTile == 0) {
            if (okeyCount == 2) {
                pairWithOkey = 1;
            }
        } else {
            pairWithOkey = Math.min(remainedTile, okeyCount);  //okeyi kullanarak yapılan pair sayısı
        }
        return pairWithOkey;
    }
}
