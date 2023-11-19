package monopoly.controller.manager;

import monopoly.view.GameGrid;
import monopoly.view.InfoGrid;
import monopoly.view.OptionFrame;
import monopoly.view.panel.ButtonPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class OptionFrameManager implements ActionListener, MouseListener {
    private final OptionFrame optionFrame;

    public OptionFrameManager(OptionFrame optionFrame) {
        this.optionFrame = optionFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("exit")) {
            optionFrame.getOptionFrame().setVisible(false);
        } else if (e.getActionCommand().equals("Verlassen ohne Speichern")) {
            int a = JOptionPane.showConfirmDialog(null, "Sind sie sicher das Sie ohne Speichern Verlassen wollen", "Verlassen", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (a == 0)
                System.exit(0);
        } else if (e.getActionCommand().equals("Speichern und Verlassen")) {
            String farbe;
            int anzahlSpieler = SpielerManager.getSpielerarray().length;

            for (int i = 1; i <= anzahlSpieler; i++) {
                ArrayList<String> spielerDaten = new ArrayList<>();
                if (SpielerManager.getSpielerarray()[i - 1].getColor().getRGB() == -65536) {
                    farbe = "Rot";
                } else if (SpielerManager.getSpielerarray()[i - 1].getColor().getRGB() == -16776961) {
                    farbe = "Blau";
                } else if (SpielerManager.getSpielerarray()[i - 1].getColor().getRGB() == -16711936) {
                    farbe = "Gruen";
                } else {
                    farbe = "Schwarz";
                }
                spielerDaten.add(SpielerManager.getSpielerarray()[i - 1].getSpielerName());
                spielerDaten.add(String.valueOf(SpielerManager.getSpielerarray()[i - 1].getId()));
                spielerDaten.add(String.valueOf(SpielerManager.getSpielerarray()[i - 1].getGeld()));
                spielerDaten.add(String.valueOf(SpielerManager.getSpielerarray()[i - 1].getAusDemGefaengnis()));
                spielerDaten.add(String.valueOf(SpielerManager.getSpielerarray()[i - 1].getSpielerID()));
                spielerDaten.add(farbe);
                spielerDaten.add(String.valueOf(SpielerManager.getSpielerarray()[i - 1].getPoint().x));
                spielerDaten.add(String.valueOf(SpielerManager.getSpielerarray()[i - 1].getPoint().y));
                spielerDaten.add(String.valueOf(SpielerManager.getSpielerarray()[i - 1].getGefaengnisStatus()));
                spielerDaten.add(String.valueOf(SpielerManager.getSpielerarray()[i - 1].getPaschZaehler()));
                spielerDaten.add(String.valueOf(SpielerManager.getSpielerarray()[i - 1].getRundeGefaengis()));
                spielerDaten.add(SpielerManager.getSpielerarray()[i - 1].getPasswort());

                DateiManager.spielerSpeichern(spielerDaten, SpielerManager.getSpielerarray()[i - 1].getStreet(), "Spieler" + i + ".txt");


            }
            ArrayList<String> einstellungen = new ArrayList<>();
            einstellungen.add(String.valueOf(DateiManager.getWuerfelTaste()));
            einstellungen.add(String.valueOf(GameManager.getaktg()));
            einstellungen.add(String.valueOf(GameManager.getakte()));
            einstellungen.add(String.valueOf(GameManager.getakt()));
            einstellungen.add(GameGrid.getfrei());
            einstellungen.add(ButtonPanel.getwuerfel());
            einstellungen.add(ButtonPanel.getstrasse());
            einstellungen.add(ButtonPanel.gethaus());
            einstellungen.add(String.valueOf(GameManager.getaktid()));

            if (InfoGrid.getaktpanel() == null) {
                einstellungen.add("EckfelderPanel");
            } else {
                einstellungen.add(InfoGrid.getaktpanel());
            }
            DateiManager.einstellungenSpeichernspiel(einstellungen);
            DateiManager.saveavaleblestreets(GameManager.getavalestreet());
            JOptionPane.showMessageDialog(null, "Ihre Einstellungen wurden erfolgreich gespeichert");
            System.exit(0);

        }

    }

    public boolean isBetween(float x1, float y1, float x2, float y2, float objeX, float objeY) {
        if ((x1 <= objeX && x2 >= objeX) || (x1 >= objeX && x2 <= objeX)) {
            return (y1 <= objeY && y2 >= objeY) || (y2 <= objeY && y1 >= objeY);
        } else {
            return false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (isBetween(550, 25, 575, 50, e.getX(), e.getY())) {
            optionFrame.getOptionFrame().setVisible(false);
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}
