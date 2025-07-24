package org.fatih.model;

import lombok.Getter;
import lombok.Setter;
import org.fatih.enumz.TileColor;

@Setter
@Getter
public class Tile {
    private int id;

    private TileColor color;

    private int number;

    public Tile(int id) {
        this.id = id;
        TileColor[] tileColors = TileColor.values();
        int colorIndex = id / 13;
        this.color = tileColors[colorIndex];
        this.number = (id % 13) + 1;
    }

    public boolean isFalseOkey(){
        return this.id == 52;
    }

    @Override
    public String toString() {
        return "id : " + this.id + ", taş: " + this.color.name() + " " + this.number;
    }
}
