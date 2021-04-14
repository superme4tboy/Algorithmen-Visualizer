package de.carlos.AlgoVisualizer.Grid;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Grid {
    private Tile[][] tiles;
    private Tile startTile;
    private Tile endTile;

    /**
     * creates the Grid without any content.
     * Predefine the size of the Grid
     * @param horizontaleTileSize
     * @param vertikalTileSize
     */
    public Grid(int horizontaleTileSize, int vertikalTileSize) {
        tiles = new Tile[horizontaleTileSize][vertikalTileSize];
    }

    public void updateGrid(Tile[][] tiles) {
        this.tiles = tiles;
    }

    public Tile getStartTile() {
        return startTile;
    }

    public void setStartTile(Tile startTile) {
        this.startTile = startTile;
    }

    public Tile getEndTile() {
        return endTile;
    }

    public void setEndTile(Tile endTile) {
        this.endTile = endTile;
    }

    /**
     * @param horizontalPos represent the horizontal Position of the Tile
     * @param verticalPos represent the vertikal Position of the Tile
     * @return the Tile on this Position
     */
    public Tile getTile(int horizontalPos, int verticalPos) {

        return tiles[horizontalPos][verticalPos];
    }

    public Tile[][] getAllTiles() {
        return tiles;
    }
}
