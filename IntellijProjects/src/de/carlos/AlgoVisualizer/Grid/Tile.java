package de.carlos.AlgoVisualizer.Grid;

import de.carlos.AlgoVisualizer.GUI.IconHandler;

import java.time.temporal.ValueRange;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Tile implements Comparable<Tile>{
    private int x;
    private int y;
    private int size;
    private ArrayList<Edge> initEdges;
    private ArrayList<Edge> currentEdges;
    private final ArrayList<Edge>emptyList = new ArrayList<>(4);
    private IconHandler icon;

    private boolean isBlocked = false;
    private boolean isVisited = false;
    private Optional<Integer> distance;
    private Tile previousNode;

    public Tile(final int x, final int y, final int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public void updateNode(final int x,final int y, final int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public void setIcon(IconHandler icon) {
        setBlocked(icon == IconHandler.WALL);
        this.icon = icon;
    }

    public IconHandler getIcon() {
        return icon;
    }

    public void addEdge(Edge edge) {
        currentEdges.add(edge);
    }
    public void addEdge(ArrayList<Edge> initEdges) {
        this.initEdges = initEdges;
        currentEdges = initEdges;
    }

    public void removeEdge(Edge edge) {
        currentEdges.remove(edge);
    }

    public ArrayList<Edge> getAllOutgoingEdges() {
        return  currentEdges;
    }

    private void setBlocked(Boolean bool) {
            isBlocked = bool;
            List<Tile> nearbyTiles = initEdges.stream().map(edge -> edge.getDestination()).collect(Collectors.toList());
            if(isBlocked) {
                currentEdges = emptyList;
                for(Tile tile: nearbyTiles) {
                    Optional<Edge> removeThisEdge = tile.getAllOutgoingEdges().stream().
                            filter(edge -> edge.getDestination() == this).findFirst();
                    if(removeThisEdge.isPresent()) {
                        tile.removeEdge(removeThisEdge.get());
                    }
                }
            } else {
                currentEdges = initEdges;
                for(Tile tile: nearbyTiles) {
                    if(!tile.isBlocked) {
                        tile.addEdge(new Edge(tile, this));
                    }
                }
            }
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public boolean getVisitedStatus() {
        return isVisited;
    }

    public void setVisitStatus(boolean visitStatus) {
        isVisited = visitStatus;
    }

    public boolean inRangeOfThisTile(int x, int y) {
        ValueRange rangeOfXAxis = ValueRange.of(this.x, this.x+size);
        ValueRange rangeOfYAxis = ValueRange.of(this.y, this.y+size);
        return rangeOfXAxis.isValidIntValue(x) && rangeOfYAxis.isValidIntValue(y);
    }

    public int getXPosition() {
        return x;
    }

    public int getYPosition() {
        return y;
    }

    public int getSize()  {
        return size;
    }

    public int getDistance() {
        return distance.get();
    }

    public void setDistance(Optional<Integer> distance) {
        this.distance = distance;
    }

    public Tile getPreviousNode() {
        return previousNode;
    }

    public void setPreviousNode(Tile previousNode) {
        this.previousNode = previousNode;
    }

    @Override
    public int compareTo(Tile o) {
        return Integer.compare(this.getDistance(), o.getDistance());
    }
}
