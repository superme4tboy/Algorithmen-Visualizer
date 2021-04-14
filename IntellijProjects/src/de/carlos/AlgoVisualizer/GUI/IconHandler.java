package de.carlos.AlgoVisualizer.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public enum IconHandler {
    CROSS,
    WALL,
    RED_POINT,
    CIRCLE;

    public ImageIcon getImage(Dimension dimension) {
        if(this == CROSS) {
            try {
                Image crossIcon = ImageIO.read(new File("rsc/cross.png"));
                return rescaleImage(crossIcon, dimension);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Cross Icon can't be load");
            }
        } else if( this == WALL) {
            try {
                Image wallIcon = ImageIO.read(new File("rsc/wall_red.png"));
                return  rescaleImage(wallIcon,dimension);
            } catch(Exception e) {
                e.printStackTrace();
                System.out.println("Wall can't be load");
            }
        } else if( this == CIRCLE) {
            try {
                Image circleIcon = ImageIO.read(new File("rsc/endpoint.png"));
                return rescaleImage(circleIcon, dimension);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Circle can't be load");
            }
        } else if( this == RED_POINT) {
                try {
                    Image red_Point = ImageIO.read(new File("rsc/redPoint.png"));
                    dimension.setSize(dimension);
                    return rescaleImage(red_Point,dimension);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return null;
    }

    private static ImageIcon rescaleImage(Image img, Dimension dimension) {
        int width = dimension.width;
        int height = dimension.height;

        BufferedImage bufferResizedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = bufferResizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(img,0,0,width,height,null);
        g2.dispose();

        return new ImageIcon(bufferResizedImg);
    }
}