package de.carlos.AlgoVisualizer.Icon;

import de.carlos.AlgoVisualizer.GUI.IconHandler;
import de.carlos.AlgoVisualizer.Grid.Grid;
import de.carlos.AlgoVisualizer.Grid.Tile;

import java.awt.*;

public class StartEndIcon {

    private Tile tile;
    private boolean hasUpdate = false;

    private final IconHandler imageIcon;

    public StartEndIcon(IconHandler icon) {
        imageIcon = icon;
    }

    public void update(Tile tile, Grid grid) {
        if(this.tile != tile && hasUpdate && tile.getIcon() == null) {
            this.tile.setIcon(null);
        }
        if(tile.getIcon() == null) {
            this.tile = tile;
            this.tile.setIcon(imageIcon);
            setIconToGrid(grid);
            hasUpdate = true;
        }
    }

    public boolean isSetOnGrid() {
        return hasUpdate;
    }

    public void paintIcon(Graphics g) {
        if (tile != null) {
            int x = this.tile.getXPosition();
            int y = this.tile.getYPosition();
            int size = this.tile.getSize();
            if(hasUpdate && tile.getIcon() != IconHandler.WALL) {
                Image img= imageIcon.getImage(new Dimension(size,size)).getImage();
                g.drawImage(img, x, y,null);
            }
        }
    }

    private void setIconToGrid(Grid grid) {
        if(imageIcon == IconHandler.CROSS) {
            grid.setStartTile(tile);
        } else {
            grid.setEndTile(tile);
        }
    }

}
