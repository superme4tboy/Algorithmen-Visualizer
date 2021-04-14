package de.carlos.AlgoVisualizer.Grid;

/**
 * realize the Edges of One Tile
 */
public class Edge {
    private final Tile startTile;
    private final Tile endTile;
    private final int distance = 1;

    /**
     * creates one Edge for a Tile
     * @param startTile where the Edge start
     * @param endTile wher it ends
     */
    public Edge(final Tile startTile, final Tile endTile) {
        this.startTile = startTile;
        this.endTile = endTile;
    }

    /**
     * get the predefined Distance
     * @return
     */
    public int getDistance() {
        return distance;
    }

    /**
     * get from the Edge the start Tile
     * @return the starTile
     */
    public Tile getStartTile() {
        return startTile;
    }

    /**
     * get from the Edge the destination Tile
     * @return the destination
     */
    public Tile getDestination() {
        return endTile;
    }

}
