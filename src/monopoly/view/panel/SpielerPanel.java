package monopoly.view.panel;

import monopoly.controller.manager.DateiManager;
import monopoly.controller.manager.SpielerManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SpielerPanel extends JPanel {
    private JLabel lblspieler;
    private JLabel lblname;
    private JLabel lblfarbe;
    private JLabel lblicon;
    private JLabel lblgeld;
    private JLabel lblgefaegnis;
    private JLabel lblaktuellePosition;


    private GridLayout gL;

    private Font lblFont;

    private int spielerID;


    public SpielerPanel() {

        initVars();
        setSpielerID(1);
    }

    /**
     * Initializes Variables.
     */

    private void initVars() {

        lblFont = new Font("Monospaced", Font.PLAIN, 25);

        gL = new GridLayout(7, 1);

        lblaktuellePosition = new JLabel();
        lblfarbe = new JLabel();
        lblgefaegnis = new JLabel();
        lblgeld = new JLabel();
        lblicon = new JLabel();
        lblname = new JLabel();
        lblspieler = new JLabel();
    }

    /**
     * Initializes and set up Window Components.
     */
    public void setSpielerID(int spielerID) {
        this.spielerID = spielerID - 1;
        initComps();
    }

    public void initComps() {


        setLayout(gL);
        setOpaque(false);
        removeAll();
        setUpJlabel(lblspieler, "SpielerID:           " + (spielerID + 1));
        setUpJlabel(lblname, "SpielerName:         " + SpielerManager.getSpielerarray()[spielerID].getSpielerName());
        setUpJlabel(lblicon, String.valueOf(1));
        setUpJlabel(lblfarbe, null);
        setUpJlabel(lblgeld, "Geld:                " + SpielerManager.getSpielerarray()[spielerID].getGeld() + "EUR");
        setUpJlabel(lblgefaegnis, "Gefaengnisfrei-Karte: " + SpielerManager.getSpielerarray()[spielerID].getAusDemGefaengnis());
        setUpJlabel(lblaktuellePosition, String.valueOf(SpielerManager.getSpielerarray()[spielerID].getId()));
        revalidate();
        repaint();
    }

    public void setUpJlabel(JLabel lbl, String txt) {

        lbl.setFont(lblFont);

        if (lbl.equals(lblicon)) {
            lbl.setHorizontalTextPosition(SwingConstants.LEFT);
            lbl.setText("SpielerIcon:         ");
            lbl.setIcon(new ImageIcon(SpielerManager.getSpielerarray()[spielerID].getSpielFigur()));
        } else if (lbl.equals(lblfarbe)) {
            String farbe;
            if (SpielerManager.getSpielerarray()[spielerID].getColor().getRGB() == -65536) {
                farbe = "Rot";
            } else if (SpielerManager.getSpielerarray()[spielerID].getColor().getRGB() == -16776961) {
                farbe = "Blau";
            } else if (SpielerManager.getSpielerarray()[spielerID].getColor().getRGB() == -16711936) {
                farbe = "Gruen";
            } else {
                farbe = "Schwarz";
            }
            lbl.setText("Farbe:               " + farbe);

        } else if (lbl.equals(lblaktuellePosition)) {
            ArrayList<String> aktuellePosition = DateiManager.dateiLesen("Spielfeld.txt");
            String strasse = aktuellePosition.get(Integer.valueOf(txt));
            String[] strasse1 = strasse.split("@");
            lbl.setText("Aktuelleposition:    " + strasse1[5]);
        } else {
            lbl.setText(txt);
        }
        add(lbl);

    }
}
