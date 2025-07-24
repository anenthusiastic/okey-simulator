package org.fatih.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class Serie {
    private List<Tile> tiles;
}
