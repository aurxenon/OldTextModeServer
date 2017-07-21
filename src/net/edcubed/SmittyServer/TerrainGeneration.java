package net.edcubed.SmittyServer;

import net.edcubed.SmittyCommons.Place;

import java.util.ArrayList;

public class TerrainGeneration {
    private int worldSizeX = 1024;
    private int worldSizeY = 1024;
    private ExtraUtils utils = new ExtraUtils();
    private ArrayList<Place> placeList = new ArrayList<Place>();

    public TerrainGeneration(int xSize, int ySize) {
        worldSizeX = xSize;
        worldSizeY = ySize;
    }
    public void GenerateStyle1() {
        System.out.println("Generating world using Style #1");
        for (int x = 0; x < worldSizeX; x++) {
            for (int y = 0; y < worldSizeY; y++) {
                int rand = utils.randInt(0,100);
                if (rand >= 0 && rand < 1) {
                    placeList.add(new Place(x,y, Place.TerrainType.WOOD, Place.TerrainKind.NATURAL));
                }
                if (rand >= 1 && rand < 2) {
                    placeList.add(new Place(x,y, Place.TerrainType.STONE, Place.TerrainKind.NATURAL));
                }
                if (rand >= 2 && rand < 3) {
                    placeList.add(new Place(x,y, Place.TerrainType.IRON, Place.TerrainKind.NATURAL));
                }
            }
        }
    }
    public ArrayList<Place> getWorld() {
        return placeList;
    }
}
