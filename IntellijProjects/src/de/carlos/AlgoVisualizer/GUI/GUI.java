package de.carlos.AlgoVisualizer.GUI;

import de.carlos.AlgoVisualizer.Grid.Grid;
import de.carlos.AlgoVisualizer.Listeners.DialogWindowClosingListener;
import de.carlos.AlgoVisualizer.Listeners.OptionPanelActionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * create the GUI with all clickable components
 */
public class GUI {

    private Grid grid;

    /**
     * The constructor of all components for the JFrame and the for the JFrame it self
     */
    public GUI() {
        JFrame mainWindow = mainWindow();
        JPanel contentPanel = getGridPanel();

        mainWindow.add(createOptionPanel(),BorderLayout.PAGE_START);
        mainWindow.add(contentPanel, BorderLayout.CENTER);

        mainWindow.setVisible(true);
    }

    /**
     * TopLayer Frame
     * @return a JFrame modified for this program
     */
    private JFrame mainWindow() {
        JFrame frame = new JFrame("Algorithm Visualizer");

        frame.setResizable(true);
        frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setMaximumSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.setLayout(new BorderLayout());
        frame.addWindowListener(new DialogWindowClosingListener());
        frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                e.getComponent().repaint();
            }
        });

        frame.setExtendedState(Frame.MAXIMIZED_BOTH);

        return frame;
    }

    /**
     * the OptionPanel has 3 Objects that can be placed on the grid. YOu decide which object should be placed.
     * The Wall Object can be placed multiple times and can be removed by clicking on an existing Wall
     * @return A JPanel with 3 clickable Buttons
     */
    private JPanel createOptionPanel() {
        ButtonGroup chooseIcon;
        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new FlowLayout());
        optionPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        chooseIcon = new ButtonGroup();
        Dimension iconSize = new Dimension(100,100);
        ImageIcon crossIcon = IconHandler.CROSS.getImage(iconSize);
        ImageIcon wallIcon = IconHandler.WALL.getImage(iconSize);
        ImageIcon circleIcon = IconHandler.CIRCLE.getImage(iconSize);

        JToggleButton xButton = new JToggleButton("Set Start", crossIcon);
        JToggleButton wallButton = new JToggleButton("Set Wall", wallIcon);
        JToggleButton circleButton = new JToggleButton("Set End", circleIcon);

        xButton.setActionCommand("Cross");
        wallButton.setActionCommand("Wall");
        circleButton.setActionCommand("Circle");

        xButton.addActionListener(new OptionPanelActionListener());
        wallButton.addActionListener(new OptionPanelActionListener());
        circleButton.addActionListener(new OptionPanelActionListener());

        optionPanel.add(xButton);
        optionPanel.add(wallButton);
        optionPanel.add(circleButton);

        chooseIcon.add(xButton);
        chooseIcon.add(wallButton);
        chooseIcon.add(circleButton);

        return optionPanel;
    }

    /**
     * creates a new component Object with a resizable grid for the main Window
     * @return a JPanel with an grid. The grid is resizable and has 10 rows and 20 columns.
     */
    private GridPanel getGridPanel() {
        GridPanel gridPanel = new GridPanel();
        return gridPanel;
    }

}
