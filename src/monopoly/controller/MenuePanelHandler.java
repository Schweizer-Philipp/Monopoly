package monopoly.controller;


import monopoly.view.MenuePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuePanelHandler implements ActionListener, MouseListener {

    // 7F8A98D
    private final MenuePanel menuePanel;


    public MenuePanelHandler(MenuePanel menuePanel /*5d099f62*/) {
        // 7F8A98D        /*5d099f62*/

        this.menuePanel = menuePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        menuePanel.createHover((JButton) e.getSource(), "entered");
    }

    @Override
    public void mouseExited(MouseEvent e) {

        menuePanel.createHover((JButton) e.getSource(), "exit");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        menuePanel.pressedButton((JButton) e.getSource());
    }

}
