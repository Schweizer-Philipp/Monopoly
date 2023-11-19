package monopoly.model;


import monopoly.controller.manager.ImageManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class CloseButton extends JPanel {

    private final int x;
    private final int y;
    private final Image bild;

    public CloseButton(int x, int y, MouseListener mous) {
        this.x = x;
        this.y = y;
        addMouseListener(mous);
        bild = ImageManager.loadImage("/Icons/Rotesx.png");
    }

    protected void paintComponent(Graphics g) {
        //super.paintComponent(g);
        g.drawImage(bild, x, y, 25, 25, null);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(x, y);
    }
}
