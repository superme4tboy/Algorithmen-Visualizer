package de.carlos.AlgoVisualizer.Algorithmen;

import de.carlos.AlgoVisualizer.Grid.Edge;
import de.carlos.AlgoVisualizer.Grid.Grid;
import de.carlos.AlgoVisualizer.Grid.Tile;

import java.util.*;

public class Dijkstra {
    private final Grid grid;
    LinkedList<Tile>shortestPath = new LinkedList<>();

    public Dijkstra(Grid grid) {
        this.grid = grid;

        ArrayList<Edge>currentEdges;
        PriorityQueue<Tile> queue = new PriorityQueue<>();
        Tile endTile = grid.getEndTile();
        Tile[][] tiles = grid.getAllTiles();

        Arrays.stream(tiles).flatMap(x -> Arrays.stream(x)).forEach(tile -> tile.setDistance(Optional.of(Integer.MAX_VALUE)));
        Tile startTile = grid.getStartTile();
        startTile.setDistance(Optional.of(0));
        queue.add(startTile);

        if(!queue.isEmpty()) {
            if(queue.peek().getAllOutgoingEdges() != null) {
                while(!queue.isEmpty()) {
                    Tile currentTile = queue.poll();
                    currentEdges = currentTile.getAllOutgoingEdges();
                    for(Edge edge: currentEdges) {
                        Tile source = edge.getStartTile();
                        Tile destination = edge.getDestination();
                        if((source.getDistance() + edge.getDistance()) <= destination.getDistance()) {
                            if((source.getDistance() + edge.getDistance()) == destination.getDistance()) {
                                destination.setDistance(Optional.of(source.getDistance() + edge.getDistance()));
                            } else{
                                destination.setDistance(Optional.of(source.getDistance() + edge.getDistance()));
                                destination.setPreviousNode(source);
                            }
                            if(!destination.getVisitedStatus()) {
                                queue.add(destination);
                            }
                        }
                    }
                    currentTile.setVisitStatus(true);
                }

                Tile currentTile = endTile.getPreviousNode();
                while(currentTile != null) {
                    shortestPath.add(currentTile);
                    currentTile = currentTile.getPreviousNode();
                }

                Arrays.stream(tiles).parallel().flatMap(x -> Arrays.stream(x).parallel()).forEach(tile -> {
                    tile.setVisitStatus(false);
                    tile.setDistance(Optional.empty());
                    tile.setPreviousNode(null);
                });
            }
        }
    }

    public LinkedList<Tile> getShortestPath() {
        return shortestPath;
    }

}
