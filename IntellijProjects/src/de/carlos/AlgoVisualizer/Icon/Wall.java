package de.carlos.AlgoVisualizer.Icon;

import de.carlos.AlgoVisualizer.GUI.IconHandler;
import de.carlos.AlgoVisualizer.Grid.Tile;

import java.awt.*;
import java.util.LinkedList;
import java.util.Optional;

public class Wall {
    private boolean hasUpdate = false;
    private Image wallImage;
    private final LinkedList<Tile>walls = new LinkedList<>();

    public void paint(Graphics g) {
        if(hasUpdate) {
            int size = walls.getFirst().getSize();
            wallImage = IconHandler.WALL.getImage(new Dimension(size,size)).getImage();
            for(Tile currentTile: walls) {
                g.drawImage(wallImage, currentTile.getXPosition(), currentTile.getYPosition(), null);
            }
        }
    }

    public void update(Tile tile) {
        Optional<Tile>doubleClickedWall = walls.parallelStream().filter(currentTile -> currentTile == tile).findFirst();
        if(doubleClickedWall.isPresent()) {
            walls.remove(doubleClickedWall.get());
            doubleClickedWall.get().setIcon(null);
            if(walls.isEmpty()) {
                hasUpdate = false;
            }
        } else if(tile.getIcon() == null) {
            hasUpdate = true;
            walls.add(tile);
            tile.setIcon(IconHandler.WALL);
        }

    }
}