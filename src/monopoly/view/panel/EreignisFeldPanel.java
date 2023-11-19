package monopoly.view.panel;

import monopoly.controller.manager.DateiManager;
import monopoly.controller.manager.GameManager;
import monopoly.controller.manager.ImageManager;
import monopoly.controller.manager.SpielerManager;
import monopoly.model.StreetBuyAble;
import monopoly.view.GameGrid;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.util.ArrayList;

public class EreignisFeldPanel extends JPanel {


    private ArrayList<String> gFelder;

    private JLabel lblname;
    private JLabel lblbild;
    private JTextPane txtftxt;
    private JLabel lblUeberschrift;

    private GridLayout gl;

    public EreignisFeldPanel() {
        initVars();
        initComps();
    }


    private void initVars() {
        gl = new GridLayout(4, 1);
        gFelder = DateiManager.dateiLesen("Ereigniskarten.txt");
        lblbild = new JLabel();
        lblname = new JLabel();
        txtftxt = new JTextPane();
        lblUeberschrift = new JLabel();

    }


    private void initComps() {
        setOpaque(false);
        setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(HEIGHT), null));
        setLayout(gl);

    }


    public int setUpEreignisfeld(String nummer, boolean status, int spieler, GameManager gameManager, ButtonPanel buttonPanel) {
        removeAll();
        for (String s : gFelder) {
            String[] test = s.split("@");
            if (test[0].equals(nummer)) {
                setUpJlabel(lblname, "Ereignisfeld");
                setUpJlabel(lblbild, "EreigFeld.png");
                setUpJlabel(lblUeberschrift, test[1]);
                setUpJlabel(txtftxt, test[2]);
                if (status) {

                    JOptionPane.showMessageDialog(null, test[1], "Ereignisfeld", JOptionPane.INFORMATION_MESSAGE);
                    if (test[3].equals("karte")) {
                        SpielerManager.getSpielerarray()[spieler - 1].setAusDemGefaengnis(true);
                        gameManager.setSpielerPanel();
                        gameManager.setSpielerStrassenPanel();
                        return 80;
                    } else if (test[3].equals("gefaengnis")) {
                        if (SpielerManager.getSpielerarray()[spieler - 1].getAusDemGefaengnis()) {
                            SpielerManager.getSpielerarray()[spieler - 1].setAusDemGefaengnis(false);
                            SpielerManager.getSpielerarray()[spieler - 1].setId(10);
                            GameGrid.getSpielermanager().changePositionofplayer(spieler, 10);
                            gameManager.setInfoGridUp(false);
                            gameManager.setSpielerPanel();
                            gameManager.setSpielerStrassenPanel();
                            return 80;
                        } else {
                            SpielerManager.getSpielerarray()[spieler - 1].setGefaengnisStatus(true);
                            SpielerManager.getSpielerarray()[spieler - 1].setId(10);
                            GameGrid.getSpielermanager().changePositionofplayer(spieler, 10);
                            gameManager.setInfoGridUp(false);
                            gameManager.setSpielerPanel();
                            gameManager.setSpielerStrassenPanel();
                            return 80;
                        }

                    } else if (test[3].equals("Los")) {
                        SpielerManager.getSpielerarray()[spieler - 1].setGeld(SpielerManager.getSpielerarray()[spieler - 1].getGeld() + 200);
                        SpielerManager.getSpielerarray()[spieler - 1].setId(0);
                        GameGrid.getSpielermanager().changePositionofplayer(spieler, 0);
                        gameManager.setInfoGridUp(false);
                        gameManager.setSpielerPanel();
                        gameManager.setSpielerStrassenPanel();
                        return 80;
                    } else if (test[3].equals("zahlen")) {
                        int betrag = 0;
                        for (StreetBuyAble street : SpielerManager.getSpielerarray()[spieler - 1].getStreet()) {
                            if (street.getHaeuserAnzahl() == 5) {
                                betrag = betrag + 100;
                            } else {
                                betrag = betrag + street.getHaeuserAnzahl() * 25;
                            }
                        }
                        SpielerManager.getSpielerarray()[spieler - 1].setGeld(SpielerManager.getSpielerarray()[spieler - 1].getGeld() - betrag);
                        gameManager.setSpielerPanel();
                        gameManager.setSpielerStrassenPanel();
                        JOptionPane.showMessageDialog(null, "Sie haben " + betrag + " geld verloren", "zahlen", JOptionPane.INFORMATION_MESSAGE);


                        if (SpielerManager.getSpielerarray()[spieler - 1].getGeld() <= 0) {
                            gameManager.spielerPleite(spieler);
                        }
                        return 80;
                    } else if (test[3].equals("allee")) {
                        buttonPanel.setAndere(39);
                        GameGrid.getSpielermanager().changePositionofplayer(spieler, 39);
                        SpielerManager.getSpielerarray()[spieler - 1].setId(39);
                        gameManager.setInfoGridUp(false);
                        gameManager.setSpielerPanel();
                        gameManager.setSpielerStrassenPanel();
                        return 39;
                    } else if (test[3].equals("see")) {
                        if (SpielerManager.getSpielerarray()[spieler - 1].getId() > 11) {
                            buttonPanel.setAndere(11);
                            GameGrid.getSpielermanager().changePositionofplayer(spieler, 11);
                            SpielerManager.getSpielerarray()[spieler - 1].setId(11);
                            SpielerManager.getSpielerarray()[spieler - 1].setGeld(SpielerManager.getSpielerarray()[spieler - 1].getGeld() + 200);
                            gameManager.setInfoGridUp(false);
                            gameManager.setSpielerPanel();
                            gameManager.setSpielerStrassenPanel();
                            return 11;

                        } else {
                            buttonPanel.setAndere(11);
                            GameGrid.getSpielermanager().changePositionofplayer(spieler, 11);
                            SpielerManager.getSpielerarray()[spieler - 1].setId(11);
                            gameManager.setInfoGridUp(false);
                            gameManager.setSpielerPanel();
                            gameManager.setSpielerStrassenPanel();
                            return 11;
                        }


                    } else if (test[3].equals("bahnhof")) {
                        if (SpielerManager.getSpielerarray()[spieler - 1].getId() > 5) {
                            buttonPanel.setAndere(5);
                            GameGrid.getSpielermanager().changePositionofplayer(spieler, 5);
                            SpielerManager.getSpielerarray()[spieler - 1].setId(5);
                            SpielerManager.getSpielerarray()[spieler - 1].setGeld(SpielerManager.getSpielerarray()[spieler - 1].getGeld() + 200);
                            gameManager.setInfoGridUp(false);
                            gameManager.setSpielerPanel();
                            gameManager.setSpielerStrassenPanel();
                            return 5;
                        } else {
                            buttonPanel.setAndere(5);
                            SpielerManager.getSpielerarray()[spieler - 1].setId(5);
                            GameGrid.getSpielermanager().changePositionofplayer(spieler, 5);
                            gameManager.setInfoGridUp(false);
                            gameManager.setSpielerPanel();
                            gameManager.setSpielerStrassenPanel();
                            return 5;
                        }


                    } else if (test[3].equals("opern")) {
                        if (SpielerManager.getSpielerarray()[spieler - 1].getId() > 24) {
                            buttonPanel.setAndere(24);
                            GameGrid.getSpielermanager().changePositionofplayer(spieler, 24);
                            SpielerManager.getSpielerarray()[spieler - 1].setId(24);
                            SpielerManager.getSpielerarray()[spieler - 1].setGeld(SpielerManager.getSpielerarray()[spieler - 1].getGeld() + 200);
                            gameManager.setInfoGridUp(false);
                            gameManager.setSpielerPanel();
                            gameManager.setSpielerStrassenPanel();
                            return 24;
                        } else {
                            buttonPanel.setAndere(24);
                            SpielerManager.getSpielerarray()[spieler - 1].setId(24);
                            GameGrid.getSpielermanager().changePositionofplayer(spieler, 24);
                            gameManager.setInfoGridUp(false);
                            gameManager.setSpielerPanel();
                            gameManager.setSpielerStrassenPanel();
                            return 24;
                        }


                    } else {
                        SpielerManager.getSpielerarray()[spieler - 1].setGeld(SpielerManager.getSpielerarray()[spieler - 1].getGeld() + Integer.valueOf(test[3]));
                        gameManager.setSpielerPanel();
                        gameManager.setSpielerStrassenPanel();
                        JOptionPane.showMessageDialog(null, "Sie haben " + test[3] + "EUR gemacht", "zahlen/bekommen", JOptionPane.INFORMATION_MESSAGE);
                        if (SpielerManager.getSpielerarray()[spieler - 1].getGeld() <= 0) {
                            gameManager.spielerPleite(spieler);
                        }
                    }

                }
            }
        }
        return 80;
    }

    private void setUpJlabel(JComponent comp, String txt) {


        if (comp == txtftxt) {
            Color farbe = new Color(187, 231, 230);

            /*
             * (1) Warum :
             *     Wir ueberschreiben das default LAF durch das Nimbus Ding.
             *     Dadurch werden ALLE standartwerte des Nimbus LAF genommen
             *     und die der Komponenten werden tw. ueberschrieben. (tw = teilweise)
             *     Daher muss man ueber die UIDefaults die Standartwerte des Nimbus LAF aendern.
             *     Hauptgrund: Java SWING is scheisse .. zmd was LAF angeht. JavaFX --> beschte--> CSS for the win
             *
             * (2) Wie:
             *     Die ganzen Werte stehen in der UIDefaults - Klasse.
             *     Diese Werte kann man ueber die Attribute veraendern. Diese Attribute kann man ueber die SimpleAttributeSet Klasse
             *     aendern. Wieso, weshalb, warum sollte erstmal egal sein - is halt so.
             *     Dort ruft man dann neben setFoo() auf und aendert den mist.
             *     (foo / foobar / bar --> Abkuerzung in der Programmierung fuer irgendein scheiss sei es Varibale, methode o.ae)
             *     (gewoehnt euch dran - nehm icj bei Beispielen oefter - > siehe JavaForum :P )
             *     FOO = First Object Orientated.
             *     SO jetzt wirds n bissl abstrakter:
             *     Jede Eigenschaft hat einen bestimmten Namen (sihe Link den ich geschickt habe)
             *     so ist TextPane[Enabled].backgroundPainter eben das Attribute fuer den BG.
             *     Ueber diese String-Konstante kann man das Ding ansprechen und der Methode put() als Param uebergeben.
             *     Was anderes sagst du ihm ja auch net
             *     Man muss net alles wissen :) und tbh mich juckt das auch net :D
             *     Wenn man es weiss is es easy :) und wenn nicht --> gidf.de :)
             *     Ja .. muteable heisst immer veraenderlihc.. Strings sind btw immuteable also nicht veraenderlich :)
             *     Willst du die Scrollbar noch anders machne??
             *
             *
             */

            SimpleAttributeSet attribs = new SimpleAttributeSet();
            StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
            StyleConstants.setFontFamily(attribs, "Monospaced");
            StyleConstants.setFontSize(attribs, 20);
            StyleConstants.setForeground(attribs, Color.BLACK);
            UIDefaults defaults = new UIDefaults();

            //defaults.put("TextPane.font", res);
            defaults.put("TextPane[Enabled].backgroundPainter", farbe);

            comp.putClientProperty("Nimbus.Overrides", defaults);
            comp.setBackground(farbe);
            ((JTextPane) comp).setText(txt);
            ((JTextPane) comp).setEditable(false);
            comp.setFocusable(false);
            ((JTextPane) comp).setParagraphAttributes(attribs, true);


            JScrollPane js = new JScrollPane(comp);
            js.getVerticalScrollBar().setPreferredSize(new Dimension(5, 0));
            js.getVerticalScrollBar().setUI(new BasicScrollBarUI() {

                @Override
                protected void configureScrollBarColors() {
                    this.thumbColor = Color.DARK_GRAY;
                    this.thumbHighlightColor = Color.DARK_GRAY;
                    this.trackColor = farbe;
                    this.trackHighlightColor = farbe;
                }

                @Override
                protected JButton createDecreaseButton(int orientation) {
                    return createZeroButton();
                }

                @Override
                protected JButton createIncreaseButton(int orientation) {
                    return createZeroButton();
                }

                private JButton createZeroButton() {
                    JButton btn = new JButton();
                    btn.setPreferredSize(new Dimension(0, 0));
                    btn.setMinimumSize(new Dimension(0, 0));
                    btn.setMaximumSize(new Dimension(0, 0));
                    return btn;
                }
            });

            add(js);
        } else if (comp == lblbild) {
            ((JLabel) comp).setIcon(ImageManager.loadIcon(txt));
            ((JLabel) comp).setHorizontalAlignment(SwingConstants.CENTER);
            add(comp);
        } else if (comp instanceof JLabel) {

            ((JLabel) comp).setText(txt);
            ((JLabel) comp).setHorizontalAlignment(SwingConstants.CENTER);
            comp.setFont(new Font("Monospaced", Font.PLAIN, 20));
            add(comp);

        }
    }

}
