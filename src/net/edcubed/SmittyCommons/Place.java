package net.edcubed.SmittyCommons;

import java.io.Serializable;

public class Place implements Serializable{
    int x;
    int y;
    TerrainType terrainType;
    TerrainKind terrainKind;
    public Place(int xCoord, int yCoord, TerrainType t, TerrainKind k) {
        x = xCoord;
        y = yCoord;
        terrainType = t;
        terrainKind = k;
    }
    public enum TerrainType {
        WOOD, STONE, IRON
    }
    public enum TerrainKind {
        ARTIFICIAL, NATURAL
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public TerrainType getType(){
        return terrainType;
    }
    public TerrainKind getKind() {
        return terrainKind;
    }
}
