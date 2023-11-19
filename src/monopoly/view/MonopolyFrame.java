package monopoly.view;


import monopoly.controller.manager.ImageManager;
import monopoly.view.panel.ContainerPanel;

import javax.swing.*;


public class MonopolyFrame {
    private final JFrame hauptfenster;
    private final ContainerPanel con;

    public MonopolyFrame() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                 | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }


        con = new ContainerPanel(this);

        hauptfenster = new JFrame("Monopoly");
        hauptfenster.setIconImage(ImageManager.loadImage("/Bilder/Startbild.png"));
        hauptfenster.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        hauptfenster.setExtendedState(JFrame.MAXIMIZED_BOTH);
        hauptfenster.setUndecorated(true);
        hauptfenster.setVisible(true);
        hauptfenster.add(con);
    }


    public ContainerPanel getCon() {
        return con;
    }

    public JFrame getJframe() {
        return hauptfenster;
    }

}
