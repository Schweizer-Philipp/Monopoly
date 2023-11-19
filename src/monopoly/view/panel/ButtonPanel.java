package monopoly.view.panel;

import monopoly.controller.manager.DateiManager;
import monopoly.controller.manager.GameManager;
import monopoly.controller.manager.SpielerManager;
import monopoly.view.GameGrid;
import monopoly.view.InfoGrid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel implements ActionListener {

    private static boolean wuerfeln = false;
    private static boolean Hausmoeglichzubauen = false;
    private static boolean strassemoeglichzukaufen = false;
    private final String[] spielerOptionen = {"Wuerfeln", "Haus/Hotel bauen", "Kaufen", "Zugende"};
    private final GameManager gameManager;
    private final GamePanel gamePanel;
    private Font lblFont;
    private GridLayout gLayout;
    private int andere = 70;


    public ButtonPanel(boolean neuesSpiel, GameGrid gameGrid, InfoGrid infoGrid, SpielerPanel spielerPanel, StrassenSpielerPanel strassenSpielerPanel, StrassenPanel strassenPanel,
                       WerkePanel werkePanel, BanhofPanel bahnhofPanel,
                       EckFelderPanel eckFelderPanel, GemeinschaftsFeldPanel gPanel, EreignisFeldPanel ePanel,
                       SteuernPanel steuernPanel, GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        gameManager = new GameManager(neuesSpiel, gameGrid, this, infoGrid, spielerPanel, strassenSpielerPanel, strassenPanel, werkePanel, bahnhofPanel, eckFelderPanel, gPanel, ePanel, steuernPanel);
        initVars();
        initComps();
        spielerOptionen(neuesSpiel);
        if (neuesSpiel) {
            WuerfelAction wuerfelAction = new WuerfelAction();
            gamePanel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(Integer.valueOf(DateiManager.getEinstellungen(0)), 0), "wuerfeln");
            gamePanel.getActionMap().put("wuerfeln", wuerfelAction);
        } else {
            WuerfelAction wuerfelAction = new WuerfelAction();
            gamePanel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(DateiManager.getWuerfelTaste(), 0), "wuerfeln");
            gamePanel.getActionMap().put("wuerfeln", wuerfelAction);
        }


    }

    public static String getwuerfel() {
        return String.valueOf(wuerfeln);
    }

    public static String gethaus() {
        return String.valueOf(Hausmoeglichzubauen);
    }

    public static String getstrasse() {
        return String.valueOf(strassemoeglichzukaufen);
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    private void initVars() {

        lblFont = new Font("Monospaced", Font.PLAIN, 20);
        gLayout = new GridLayout(4, 1);

    }

    private void initComps() {
        setOpaque(false);
        setLayout(gLayout);

    }

    public void spielerOptionen(boolean neuesSpiel) {
        if (neuesSpiel) {
            wuerfeln = Boolean.valueOf(DateiManager.getEinstellungen(5));
            strassemoeglichzukaufen = Boolean.valueOf(DateiManager.getEinstellungen(6));
            Hausmoeglichzubauen = Boolean.valueOf(DateiManager.getEinstellungen(7));

            removeAll();
            setFocusable(true);
            for (int i = 0; i < 4; i++) {
                JButton btn = new JButton(spielerOptionen[i]);
                btn.setFont(lblFont);
                if ((i == 0 && wuerfeln) || i == 3 && !wuerfeln || i == 1 && !Hausmoeglichzubauen || i == 2 && !strassemoeglichzukaufen) {
                    btn.setEnabled(false);
                }
                btn.setFocusable(false);
                btn.addActionListener(this);
                btn.setActionCommand(spielerOptionen[i]);
                add(btn);
            }
            revalidate();
        } else {
            removeAll();
            setFocusable(true);
            for (int i = 0; i < 4; i++) {
                JButton btn = new JButton(spielerOptionen[i]);
                btn.setFont(lblFont);
                if ((i == 0 && wuerfeln) || i == 3 && !wuerfeln || i == 1 && !Hausmoeglichzubauen || i == 2 && !strassemoeglichzukaufen) {
                    btn.setEnabled(false);
                }
                btn.setFocusable(false);
                btn.addActionListener(this);
                btn.setActionCommand(spielerOptionen[i]);
                add(btn);
            }
            revalidate();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Haus/Hotel bauen")) {
            gameManager.hauserKaufen();
            gameManager.setSpielerPanel();
            gamePanel.revalidate();
            gamePanel.repaint();
        }

        if (e.getActionCommand().equals("Zugende")) {
            wuerfeln = !wuerfeln;
            gameManager.setAktSpieler();
            gameManager.setSpielerPanel();
            gameManager.setSpielerStrassenPanel();
            gameManager.setInfoGridUp(false);
            Hausmoeglichzubauen = false;
            strassemoeglichzukaufen = false;
            gamePanel.revalidate();
            gamePanel.repaint();
            spielerOptionen(false);
        }

        if (e.getActionCommand().equals("Wuerfeln")) {
            wuerfelPerTaste();
        }

        if (e.getActionCommand().equals("Kaufen")) {
            if (gameManager.addStreetBuyAble(andere)) {
                gameManager.setSpielerStrassenPanel();
                gameManager.setSpielerPanel();
                strassemoeglichzukaufen = !strassemoeglichzukaufen;
                spielerOptionen(false);
                gamePanel.repaint();
                gamePanel.revalidate();
                if (andere != 70) {
                    andere = 70;
                }
            } else {

            }

        }
    }

    public void wuerfelPerTaste() {
        if (gameManager.checkGefaengis()) {
            wuerfeln = !gameManager.wuerfeln();
            gameManager.setSpielerPanel();
            gameManager.setSpielerStrassenPanel();
            boolean[] button = gameManager.setInfoGridUp(true);
            if (SpielerManager.getSpielerarray()[GameManager.getakt() - 1].getGefaengnisStatus()) {
                Hausmoeglichzubauen = false;
                strassemoeglichzukaufen = false;
            } else {
                Hausmoeglichzubauen = button[0];
                strassemoeglichzukaufen = button[1];
            }

            spielerOptionen(false);
        } else {
            wuerfeln = !gameManager.wuerfeln();
            gameManager.gefaengis();
            gameManager.setSpielerPanel();
            gameManager.setSpielerStrassenPanel();
            boolean[] button = gameManager.setInfoGridUp(true);
            Hausmoeglichzubauen = button[0];
            strassemoeglichzukaufen = button[1];
            spielerOptionen(false);
        }
    }

    public void setAndere(int andere) {
        this.andere = andere;
    }

    public GameManager getgameManager() {
        return gameManager;
    }

    private class WuerfelAction extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!wuerfeln) wuerfelPerTaste();
        }

    }
}
