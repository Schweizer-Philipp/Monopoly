package monopoly.view.panel;

import monopoly.view.GameGrid;
import monopoly.view.InfoGrid;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    @SuppressWarnings("unused")
    private final ContainerPanel panelContainer;

    private GameGrid gameGrid;
    private InfoGrid infoGrid;

    private GridBagLayout hauptgbLayout;

    public GamePanel(ContainerPanel panelContainer) {
        this.panelContainer = panelContainer;
        initVars();
        initJcomp();

    }


    private void initVars() {
        hauptgbLayout = new GridBagLayout();
        gameGrid = new GameGrid(this);
    }

    private void initJcomp() {
        setLayout(hauptgbLayout);
        setBackground(Color.BLACK);

        addPanelsWithConstraints(gameGrid, 1040, 1040, 0);
    }

    private void addPanelsWithConstraints(JPanel panel, double breite, int hoehe, int spalte) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = breite;
        gbc.weighty = hoehe;
        gbc.gridx = spalte;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(20, 20, 20, 20);

        add(panel, gbc);
    }

    public Point getMaxXandY() {
        Point point = new Point();
        point.x = 1040;
        point.y = 1040;

        return point;
    }

    public void spielstarten(boolean neuesSpiel) {
        infoGrid = new InfoGrid(this, gameGrid, neuesSpiel);
        addPanelsWithConstraints(infoGrid, 795, 1040, 1);
    }

    public GameGrid getgameGrid() {
        return gameGrid;
    }
}
