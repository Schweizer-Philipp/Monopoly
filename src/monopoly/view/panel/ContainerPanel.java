package monopoly.view.panel;

import monopoly.view.MenuePanel;
import monopoly.view.MonopolyFrame;
import monopoly.view.RegelnPanel;

import javax.swing.*;
import java.awt.*;

public class ContainerPanel extends JPanel {


    private static final long serialVersionUID = 1L;

    private final CardLayout cl;

    private final MenuePanel menue;
    private final RegelnPanel regelPanel;
    private final EinstellungsPanel einstellungen;
    private final GamePanel gamePanel;


    public ContainerPanel(MonopolyFrame hauptFenster) {
        // /*5d099f62*/
        gamePanel = new GamePanel(this);
        menue = new MenuePanel(this, gamePanel);
        einstellungen = new EinstellungsPanel(hauptFenster, menue, this);
        regelPanel = new RegelnPanel(this);
        cl = new CardLayout();

        setLayout(cl);
        add(menue, "MenuePanel");
        add(einstellungen, "Einstellungspanel");
        add(gamePanel, "Gamepanel");
        add(regelPanel, "RegelPanel");

    }

    /**
     * Methode um zwischen den JPaneln (Cards) zuwechseln.
     *
     * @param panel Name des Panels (Cards)
     */
    public void switchPanel(String panel) {
        cl.show(this, panel);
    }
}
