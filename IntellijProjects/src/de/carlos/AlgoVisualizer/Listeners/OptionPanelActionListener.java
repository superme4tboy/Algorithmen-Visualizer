package de.carlos.AlgoVisualizer.Listeners;

import de.carlos.AlgoVisualizer.GUI.IconHandler;
import de.carlos.AlgoVisualizer.GUI.GridPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

public class OptionPanelActionListener implements ActionListener {

    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String pressedButton = e.getActionCommand();
        if(pressedButton.equals("Cross")) {
            GridPanel.setIcon(IconHandler.CROSS);
        } else if(pressedButton.equals("Wall")) {
            GridPanel.setIcon(IconHandler.WALL);
        } else if(pressedButton.equals("Circle")) {
            GridPanel.setIcon(IconHandler.CIRCLE);
        }
    }
}