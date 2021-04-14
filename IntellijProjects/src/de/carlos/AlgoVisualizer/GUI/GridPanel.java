package de.carlos.AlgoVisualizer.GUI;

import de.carlos.AlgoVisualizer.Algorithmen.Dijkstra;
import de.carlos.AlgoVisualizer.Grid.Edge;
import de.carlos.AlgoVisualizer.Grid.Grid;
import de.carlos.AlgoVisualizer.Grid.Tile;
import de.carlos.AlgoVisualizer.Icon.StartEndIcon;
import de.carlos.AlgoVisualizer.Icon.Wall;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

public class GridPanel extends JPanel {

    private final Grid grid = new Grid(10,20);
    StartEndIcon cross = new StartEndIcon(IconHandler.CROSS);
    StartEndIcon circle = new StartEndIcon(IconHandler.CIRCLE);
    Wall wall = new Wall();
    private boolean alreadyInitialized = false;
    private final Tile[][] tiles = new Tile[10][20];
    private Optional<Tile> tile;
    private int size;

    private static Optional<IconHandler>currentIconSelected = Optional.empty();

    /**
     * the constructor of GridPanel. Initialize a MouseListener which track the position of the mouse when the user click.
     * The mouse position is converted instantly in the Position of an specific tile of the grid.
     */
    public GridPanel() {
        super();

        tile = Optional.empty();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mouseClicked(e);
                tile = Arrays.stream(tiles).parallel().flatMap(Arrays::stream)
                        .filter(node -> node.inRangeOfThisTile(e.getX(), e.getY())).findFirst();

                repaint();
            }

        });
    }

    /**
     * paint all components of the GridPanel.
     * More specific: A Grid and all visible components of the Dijkstra Algorithm
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        paintGrid(g);
        paintOnGrid(g);
        if(!alreadyInitialized) {
            createAllEdges();
        }
        alreadyInitialized = Arrays.stream(tiles).allMatch(Objects::nonNull);
        if (isStartEndSet()) {
            paintDijkstra(g);
        }
        g2d.dispose();
    }

    /**
     * paint a 10*20 Grid specified by the window size.
     * creates for each square on the grid one Tile with the exact position of this square.
     * @param g
     */
    private void paintGrid(Graphics g) {
        size = Math.min(getWidth() - 4, getHeight() - 10) / 10;

        int y = (getHeight() - (size * 10)) / 2;
        for (int horiz = 0; horiz < 10; horiz++) {
            int x = (getWidth() - (size * 20)) / 2;
            for (int vert = 0; vert < 20; vert++) {
                g.drawRect(x, y, size, size);
                if (alreadyInitialized) {
                    Tile currentTile = tiles[horiz][vert];
                    currentTile.updateNode(x, y, size);
                } else {
                    tiles[horiz][vert] = new Tile(x, y, size);
                }
                x += size;
            }
            y += size;
        }
        grid.updateGrid(tiles);
    }

    /**
     * creates for each Tile all edges to all nearby Tiles.
     */
    private void createAllEdges() {
        for (int row = 0; row < 10; row++) {
            for (int column = 0; column < 20; column++) {
                ArrayList<Edge> currentTileEdges = new ArrayList<>(4);
                if (row + 1 < 10)
                    currentTileEdges.add(new Edge(tiles[row][column],tiles[row + 1][column]));
                if (row - 1 >= 0)
                    currentTileEdges.add(new Edge(tiles[row][column],tiles[row - 1][column]));
                if (column + 1 < 20)
                    currentTileEdges.add(new Edge(tiles[row][column],tiles[row][column + 1]));
                if (column - 1 >= 0)
                    currentTileEdges.add(new Edge(tiles[row][column],tiles[row][column - 1]));
                tiles[row][column].addEdge(currentTileEdges);
            }
        }
        grid.updateGrid(tiles);
    }

    /**
     * paint all elements that was set on the Grid and check if there was a new selection on the Grid
     * with one of the three elements from the OptionPanel.
     * @param g
     */
    private void paintOnGrid(Graphics g) {

        if (currentIconSelected.isPresent() && tile.isPresent()) {

            if (currentIconSelected.get() == IconHandler.CROSS) {
                cross.update(tile.get(), grid);
                grid.setStartTile(tile.get());
                grid.updateGrid(tiles);
            }
            if (currentIconSelected.get() == IconHandler.CIRCLE) {
                circle.update(tile.get(), grid);
                grid.setEndTile(tile.get());
                grid.updateGrid(tiles);
            }
            if (currentIconSelected.get() == IconHandler.WALL) {
                wall.update(tile.get());
                grid.updateGrid(tiles);
                grid.updateGrid(tiles);
            }
        }

        cross.paintIcon(g);
        circle.paintIcon(g);
        wall.paint(g);
    }

    /**
     * paint the Dijkstra algorithm with red points on the grid. From the Start position to the End position
     * @param g
     */
    private void paintDijkstra(Graphics g) {
        Dijkstra dijkstra = new Dijkstra(grid);
        LinkedList<Tile> shortestPath = dijkstra.getShortestPath();
        if (!shortestPath.isEmpty()) {
            shortestPath.remove(shortestPath.size()-1);
            Dimension dim = new Dimension(size,size);
            Image dijkstraIcon = IconHandler.RED_POINT.getImage(dim).getImage();
            shortestPath.parallelStream().forEach(x -> g.drawImage(dijkstraIcon, x.getXPosition(), x.getYPosition(), null));
        }
    }

    /**
     * checks if the End and Start Position is set on the Grid.
     * @return when it returns true the Dijkstra can be calculated
     */
    public boolean isStartEndSet() {
        return cross.isSetOnGrid() && circle.isSetOnGrid();
    }

    public static void setIcon(IconHandler icon) {
        currentIconSelected = Optional.of(icon);
    }

}

