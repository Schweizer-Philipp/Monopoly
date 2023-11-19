package monopoly.view;

import monopoly.controller.manager.ImageManager;
import monopoly.controller.manager.SpielerManager;
import monopoly.controller.manager.StreetManager;
import monopoly.model.RoundedPanel;
import monopoly.model.Spieler;
import monopoly.model.Street;
import monopoly.model.StreetBuyAble;
import monopoly.view.panel.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;


public class GameGrid extends RoundedPanel {
    private static SpielerManager spielerManager;
    private static JLabel lblFreiparken;
    private final GamePanel gamePanel;
    private final OptionFrame optionFrame;
    private StreetManager streetManager;

    public GameGrid(GamePanel gamePanel) {
        optionFrame = new OptionFrame();
        this.gamePanel = gamePanel;
        initVars();
        initJComp();

        KeyStroke stroke = KeyStroke.getKeyStroke("ESCAPE");
        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                optionFrame.getOptionFrame().setVisible(true);
            }
        };

        InputMap map = this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        map.put(stroke, "ESCAPE");
        this.getActionMap().put("ESCAPE", action);
    }

    public static String getfrei() {
        return lblFreiparken.getText();
    }

    public static void setfrei(String geld) {
        lblFreiparken.setText(geld);
    }

    public static SpielerManager getSpielermanager() {
        return spielerManager;
    }

    private void initVars() {
        lblFreiparken = new JLabel("200EUR");
        streetManager = new StreetManager(gamePanel);


    }

    private void initJComp() {
        setBackground(new Color(187, 231, 230));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        lblFreiparken.setHorizontalAlignment(SwingConstants.CENTER);
        lblFreiparken.setOpaque(true);
        lblFreiparken.setBackground(Color.WHITE);
        lblFreiparken.setBounds(56, 105, 50, 20);
        add(lblFreiparken);
        g2d.setColor(Color.WHITE);

        drawFromArrayList(StreetManager.getSpielfeld(), g2d);
        spielerManager.zeichneFiguren(g2d);
        zeichneGekaufterechtecke(g2d);
        zeichneHauserundHotels(g2d);

    }

    private void zeichneHauserundHotels(Graphics2D g2d) {
        int x = 0;
        int y = 0;
        for (Spieler s : SpielerManager.getSpielerarray()) {
            for (StreetBuyAble st : s.getStreet()) {
                if (st.getHaeuserAnzahl() > 0) {
                    for (Street street : StreetManager.getSpielfeld()) {
                        if (street.getId() == st.getId()) {
                            x = street.getInfoRechteck().x;
                            y = street.getInfoRechteck().y;
                        }
                    }
                    if ((st.getId() >= 0 && st.getId() < 10) || (st.getId() >= 30 && st.getId() <= 39)) {
                        if (st.getHaeuserAnzahl() == 5) {
                            if (st.getId() >= 0 && st.getId() < 10) {
                                g2d.drawImage(ImageManager.loadImage("/Icons/Hotel.png"), x + 30, y, null);
                            } else {
                                g2d.drawImage(ImageManager.loadImage("/Icons/Hotel.png"), x, y + 30, null);
                            }
                        } else {
                            if (st.getId() >= 0 && st.getId() < 10) {
                                for (int i = 0; i < st.getHaeuserAnzahl(); i++) {
                                    g2d.drawImage(ImageManager.loadImage("/Icons/Haus.png"), x + (19 * i), y, null);
                                }
                            } else {
                                for (int i = 0; i < st.getHaeuserAnzahl(); i++) {
                                    g2d.drawImage(ImageManager.loadImage("/Icons/Haus.png"), x, y + (19 * i), null);
                                }
                            }

                        }

                    } else {
                        if (st.getHaeuserAnzahl() == 5) {
                            if (st.getId() >= 20 && st.getId() < 30) {
                                g2d.drawImage(ImageManager.loadImage("/Icons/Hotel.png"), x + 30, y + 142, null);
                            } else {
                                g2d.drawImage(ImageManager.loadImage("/Icons/Hotel.png"), x + 142, y + 30, null);
                            }
                        } else {
                            if (st.getId() >= 20 && st.getId() < 30) {
                                for (int i = 0; i < st.getHaeuserAnzahl(); i++) {
                                    g2d.drawImage(ImageManager.loadImage("/Icons/Haus.png"), x + (19 * i), y + 142, null);
                                }
                            } else {
                                for (int i = 0; i < st.getHaeuserAnzahl(); i++) {
                                    g2d.drawImage(ImageManager.loadImage("/Icons/Haus.png"), x + 142, y + (19 * i), null);
                                }
                            }

                        }
                    }
                }
            }
        }
    }

    private void drawSplittetString(String string, Graphics2D g2d, int w, int h, int x, int y) {
        FontMetrics fontMetrix = g2d.getFontMetrics();

        String[] stringHaelften = string.split("(?=strasse)");

        for (int i = 0; i < stringHaelften.length; i++) {
            stringHaelften[i] += (i == 0) ? "-" : "";
            int breite = fontMetrix.stringWidth(stringHaelften[i]);

            g2d.drawString(stringHaelften[i],
                    ((w / 2) + x) - breite / 2,      // X-Coord zentriert.
                    (h / 2) + ((i == 0) ? -8 : 8) + y); // Y-Coord zentriert
        }
    }

    private void drawFromArrayList(ArrayList<Street> strassenListe, Graphics2D g2d) {
        // Transform vor dem drehen.
        AffineTransform old = g2d.getTransform();

        //Das Bild in der Mitte drehen.
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(-45));
        g2d.setTransform(transform);
        g2d.drawImage(ImageManager.loadImage("/Bilder/Startbild.png"), -170, 700, 350, 150, null);

        // Alte Transform laden.
        g2d.setTransform(old);

        for (Street strasse : strassenListe) {
            boolean bildGezeichnet = false;
            // locale Variablen
            Rectangle infoRechteck = strasse.getInfoRechteck();
            Rectangle farbRechteck = strasse.getFarbRechteck();

            int infoRW = infoRechteck.width;
            int infoRH = infoRechteck.height;

            /*
             * Nur wenn die Strasse ein Bild hat, d.h. nicht NULL zurueck
             * gegeben wird soll das Bild gezeichnet werden.
             */
            if (strasse.getBild() != null) {
                // zuzeichnende Bild.
                Image img = strasse.getBild();

                int imgW = img.getWidth(null);
                int imgH = img.getHeight(null);

                int imgOff = (infoRechteck.x == 880) ? 0 : 40;

                int imgX = strasse.getInfoRechteck().x + ((infoRH != 160 && strasse.getId() != 0) ? 40 : 0);
                int imgY = strasse.getInfoRechteck().y + ((infoRW != 160) ? imgOff : 0);

                g2d.drawImage(img, imgX, imgY, imgH, imgW, null);

                bildGezeichnet = true;
            }

            // Schrift - und Rechteckfarbe
            g2d.setColor(Color.BLACK);

            // Schriftbreite / Textlaenge
            FontMetrics fontMetrix = g2d.getFontMetrics();
            int textBreite = fontMetrix.stringWidth(strasse.getStrassennamen());

            // Nur wenn kein Bild gezeichnet wurde, soll der Strassenname
            // ausgegeben werden
            if (!bildGezeichnet) {
                if (textBreite > infoRechteck.width - 20) {
                    drawSplittetString(strasse.getStrassennamen(), g2d,
                            strasse.getInfoRechteck().width,
                            strasse.getInfoRechteck().height,
                            strasse.getInfoRechteck().x,
                            strasse.getInfoRechteck().y);
                } else {
                    g2d.drawString(strasse.getStrassennamen(),
                            (infoRechteck.width / 2 + infoRechteck.x) - textBreite / 2,
                            (infoRechteck.height / 2 + infoRechteck.y));
                }
            }

            // Inforechteck zeichnen
            g2d.draw(infoRechteck);

            // Farbrechteck - nur wenn es existiert.
            if (farbRechteck != null) {
                Color farbe = strasse.getFarbe();
                g2d.setColor(farbe);
                g2d.fillRect((int) farbRechteck.getX() + 1,
                        (int) farbRechteck.getY() + 1,
                        (int) farbRechteck.getWidth() - 1,
                        (int) farbRechteck.getHeight() - 1);
            }
        }
    }

    public void zeichneGekaufterechtecke(Graphics2D g) {

        for (Spieler s : SpielerManager.getSpielerarray()) {
            for (StreetBuyAble st : s.getStreet()) {
                g.setColor(s.getColor());
                g.fill3DRect(st.getFarbrect().x, st.getFarbrect().y, st.getFarbrect().width, st.getFarbrect().height, false);
            }
        }
    }

    public void erhoeheFreiParken(int geld) {
        StringBuilder test = new StringBuilder(lblFreiparken.getText());
        test.deleteCharAt(test.length() - 1);
        int neuGeld = Integer.valueOf(test.toString()) + geld;
        lblFreiparken.setText(neuGeld + "EUR");
    }

    public int clearFreiParken() {
        StringBuilder test = new StringBuilder(lblFreiparken.getText());
        test.deleteCharAt(test.length() - 1);
        int geld = Integer.valueOf(test.toString());
        lblFreiparken.setText("0EUR");
        return geld;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1040, 1040);
    }

    public void erstelleSpieler(boolean spielerneu) {
        spielerManager = new SpielerManager(streetManager, gamePanel, spielerneu);
    }


}
