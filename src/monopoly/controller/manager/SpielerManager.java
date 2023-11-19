package monopoly.controller.manager;

import monopoly.model.Spieler;
import monopoly.model.Strassentyp;
import monopoly.model.StreetBuyAble;
import monopoly.view.panel.GamePanel;

import java.awt.*;
import java.util.ArrayList;

public class SpielerManager {
    private static Spieler[] spielerArray;
    @SuppressWarnings("unused")
    private final StreetManager streetManager;
    private final GamePanel gamePanel;
    private int anzahlSpieler;
    private ArrayList<String> einstellungen;
    private Color farbe;

    public SpielerManager(StreetManager streetManager, GamePanel gamePanel, boolean erstellespielerneu) {
        this.streetManager = streetManager;
        this.gamePanel = gamePanel;
        initVars(erstellespielerneu);
        wuerfeln();
    }

    public static String[] wuerfeln() {
        String[] wuerfeln = new String[4];
        String pasch = "false";

        int wuerfel1 = (int) (Math.random() * 6) + 1;
        int wuerfel2 = (int) (Math.random() * 6) + 1;

        if (wuerfel1 == wuerfel2) {
            pasch = "true";
        }

        int ergebnis = wuerfel1 + wuerfel2;

        wuerfeln[0] = pasch;
        wuerfeln[1] = String.valueOf(ergebnis);
        wuerfeln[2] = String.valueOf(wuerfel1);
        wuerfeln[3] = String.valueOf(wuerfel2);

        return wuerfeln;
    }

    public static Spieler[] getSpielerarray() {
        return spielerArray;
    }

    public static void setSpielerarray(Spieler[] array) {
        spielerArray = array;
    }

    public static Color getfarbe(int id) {
        if (id == 1 || id == 3) {
            return Color.decode("#8B5A2B");
        } else if (id == 6 || id == 8 || id == 9) {
            return Color.decode("#80CCFF");
        } else if (id == 11 || id == 13 || id == 14) {
            return Color.decode("#CC44CC");
        } else if (id == 16 || id == 18 || id == 19) {
            return Color.decode("#FF8000");
        } else if (id == 21 || id == 23 || id == 24) {
            return Color.decode("#FF0000");
        } else if (id == 26 || id == 27 || id == 29) {
            return Color.decode("#FFFF00");
        } else if (id == 31 || id == 32 || id == 34) {
            return Color.decode("#338033");
        } else if (id == 37 || id == 39) {
            return Color.decode("#2020CC");
        } else {
            return Color.WHITE;
        }
    }

    /**
     * Initializes Variables.
     */
    public void initVars(boolean spielerneu) {
        if (spielerneu) {
            anzahlSpieler = DateiManager.zeilenLesen("Einstellungen.txt") - 1;
            einstellungen = DateiManager.dateiLesen("Einstellungen.txt");

            if (anzahlSpieler > 0) {

                spielerArray = new Spieler[anzahlSpieler];
                for (int i = 0; i < anzahlSpieler; i++) {
                    int platzFuerEinBild = (StreetManager.getSpielfeld().get(0).getInfoRechteck().height / anzahlSpieler - 30) / 2;


                    int abstandzaehler = i + 1;

                    int x = StreetManager.getSpielfeld().get(0).getInfoRechteck().x + (StreetManager.getSpielfeld().get(0).getInfoRechteck().width - 30) / 2;
                    int y = StreetManager.getSpielfeld().get(0).getInfoRechteck().y + (platzFuerEinBild * abstandzaehler) + (30 * i);

                    if (i == 0) {
                        farbe = Color.RED;
                    } else if (i == 1) {
                        farbe = Color.BLUE;
                    } else if (i == 2) {
                        farbe = Color.GREEN;
                    } else {
                        farbe = Color.BLACK;
                    }

                    Spieler spieler = new Spieler(0, 1500, new Point(x, y), false, einstellungen.get(i + 1), farbe, i + 1);
                    spielerArray[i] = spieler;

                }
            }
        } else {
            spielerArray = new Spieler[DateiManager.getspieleranzahl()];
            for (int i = 1; i <= DateiManager.getspieleranzahl(); i++) {
                ArrayList<String> spielerdata = DateiManager.getspielerdaten("Spieler" + i + ".txt");
                ArrayList<StreetBuyAble> street = new ArrayList<>();

                for (int a = 0; a < spielerdata.size(); a++) {
                    if (a == 0) {
                        Color farbe;
                        String test = spielerdata.get(a);
                        String[] test1 = test.split("@");
                        if (test1[5].equals("Rot")) {
                            farbe = Color.RED;
                        } else if (test1[5].equals("Gruen")) {
                            farbe = Color.GREEN;
                        } else if (test1[5].equals("Blau")) {
                            farbe = Color.BLUE;
                        } else {
                            farbe = Color.BLACK;
                        }

                        Spieler spieler = new Spieler(Integer.valueOf(test1[1]), Integer.valueOf(test1[2]),
                                new Point(Integer.valueOf(test1[6]), Integer.valueOf(test1[7])), Boolean.valueOf(test1[3]),
                                test1[0], farbe, Integer.valueOf(test1[4]));
                        spieler.setPaschZaehler(Integer.valueOf(test1[9]));
                        spieler.setGefaengnisStatus(Boolean.valueOf(test1[8]));
                        spieler.setRundeGefaengis(Integer.valueOf(test1[10]));
                        spieler.setstrasse(street);
                        spieler.setSpielerID(i);
                        spieler.setPasswort(test1[11]);
                        spielerArray[i - 1] = spieler;
                    } else {

                        String test = spielerdata.get(a);
                        String[] test1 = test.split("@");
                        Color farbe1 = getfarbe(Integer.valueOf(test1[0]));

                        StreetBuyAble sreet = new StreetBuyAble(Integer.valueOf(test1[0]), Integer.valueOf(test1[1]),
                                Integer.valueOf(test1[2]), Integer.valueOf(test1[3]), Integer.valueOf(test1[4]),
                                Integer.valueOf(test1[5]), Integer.valueOf(test1[6]), test1[7], Strassentyp.getStrassentyp(Integer.valueOf(test1[8])),
                                Integer.valueOf(test1[9]), Integer.valueOf(test1[10]), new Rectangle(Integer.valueOf(test1[11]),
                                Integer.valueOf(test1[12]), Integer.valueOf(test1[13]), Integer.valueOf(test1[14])),
                                farbe1, Double.valueOf(test1[15]));
                        sreet.setHaeuserAnzahl(Integer.valueOf(test1[16]));
                        street.add(sreet);

                    }

                }

            }
        }

    }

    public void zeichneFiguren(Graphics2D gd2) {

        for (Spieler s : spielerArray) {
            gd2.drawImage(s.getSpielFigur(), s.getPoint().x, s.getPoint().y, null);
        }

    }

    public void changePositionofplayer(int spielerID, int newID) {
        int oldID = 0;

        for (Spieler s : spielerArray) {

            if (s.getSpielerID() == spielerID) {
                oldID = s.getId();
            }

        }

        int anzahlSpielerAufDemFeld = 0;
        ArrayList<Integer> spielerDieAufDemNeuenFeldStehen = new ArrayList<Integer>();
        ArrayList<Integer> spielerDieAufDemAltenFeldStehen = new ArrayList<Integer>();
        for (Spieler s : spielerArray) {

            if (s.getId() == newID && s.getSpielerID() != spielerID) {

                spielerDieAufDemNeuenFeldStehen.add(s.getSpielerID());
                anzahlSpielerAufDemFeld++;
            }
        }

        for (Spieler s : spielerArray) {

            if (s.getId() == oldID && s.getSpielerID() != spielerID) {

                spielerDieAufDemAltenFeldStehen.add(s.getSpielerID());

            }
        }

        if ((newID < 10 && newID >= 0) || (newID < 30 && newID >= 20)) {
            int anzahlSpieler = anzahlSpielerAufDemFeld + 1;
            int platzFuerEinBild = (StreetManager.getSpielfeld().get(newID).getInfoRechteck().height / anzahlSpieler - 30) / 2;
            int x = StreetManager.getSpielfeld().get(newID).getInfoRechteck().x + (StreetManager.getSpielfeld().get(newID).getInfoRechteck().width - 50) / 2;
            int y = StreetManager.getSpielfeld().get(newID).getInfoRechteck().y + (platzFuerEinBild * anzahlSpieler + (30 * anzahlSpielerAufDemFeld));

            int mitlaeufer = 0;
            for (int i : spielerDieAufDemNeuenFeldStehen) {
                anzahlSpieler = mitlaeufer + 1;
                spielerArray[i - 1].setPoint(x, StreetManager.getSpielfeld().get(newID).getInfoRechteck().y + (platzFuerEinBild * anzahlSpieler + (30 * mitlaeufer)));
                mitlaeufer++;

            }
            spielerArray[spielerID - 1].setPoint(x, y);
        } else if ((newID < 20 && newID >= 10) || (newID <= 39 && newID >= 30)) {
            int anzahlSpieler = anzahlSpielerAufDemFeld + 1;
            int platzFuerEinBild = (StreetManager.getSpielfeld().get(newID).getInfoRechteck().width / anzahlSpieler - 30) / 2;

            int y = StreetManager.getSpielfeld().get(newID).getInfoRechteck().y + (StreetManager.getSpielfeld().get(newID).getInfoRechteck().height - 20) / 2;
            int x = StreetManager.getSpielfeld().get(newID).getInfoRechteck().x + (platzFuerEinBild * anzahlSpieler + (30 * anzahlSpielerAufDemFeld));

            int mitlaeufer = 0;
            for (int i : spielerDieAufDemNeuenFeldStehen) {

                anzahlSpieler = mitlaeufer + 1;
                spielerArray[i - 1].setPoint(StreetManager.getSpielfeld().get(newID).getInfoRechteck().x + (platzFuerEinBild * anzahlSpieler + (30 * mitlaeufer)), y);
                mitlaeufer++;

            }

            spielerArray[spielerID - 1].setPoint(x, y);

        }

        gamePanel.repaint();
        changePositionofPlayerOnTheOldFeld(spielerDieAufDemAltenFeldStehen, oldID);
    }

    private void changePositionofPlayerOnTheOldFeld(ArrayList<Integer> spielerAufDemAltenFeld, int oldID) {

        if (spielerAufDemAltenFeld != null) {
            if ((oldID < 10 && oldID >= 0) || (oldID < 30 && oldID >= 20)) {
                int x = StreetManager.getSpielfeld().get(oldID).getInfoRechteck().x + (StreetManager.getSpielfeld().get(oldID).getInfoRechteck().width - 50) / 2;
                for (int i = 0; i < spielerAufDemAltenFeld.size(); i++) {
                    int anzahlSpieler2 = spielerAufDemAltenFeld.size();
                    int platzFuerEinBild = (StreetManager.getSpielfeld().get(oldID).getInfoRechteck().width / anzahlSpieler2 - 30) / 2;
                    int anzahlSpieler1 = i + 1;
                    spielerArray[spielerAufDemAltenFeld.get(i) - 1].setPoint(x, StreetManager.getSpielfeld().get(oldID).getInfoRechteck().y + (platzFuerEinBild * anzahlSpieler1 + (30 * i)));

                }
            } else if ((oldID < 20 && oldID >= 10) || (oldID < 39 && oldID >= 30)) {

                int y = StreetManager.getSpielfeld().get(oldID).getInfoRechteck().y + (StreetManager.getSpielfeld().get(oldID).getInfoRechteck().height - 20) / 2;
                for (int i = 0; i < spielerAufDemAltenFeld.size(); i++) {
                    int anzahlSpieler2 = spielerAufDemAltenFeld.size();
                    int platzFuerEinBild = (StreetManager.getSpielfeld().get(oldID).getInfoRechteck().width / anzahlSpieler2 - 30) / 2;
                    int anzahlSpieler1 = i + 1;
                    spielerArray[spielerAufDemAltenFeld.get(i) - 1].setPoint(StreetManager.getSpielfeld().get(oldID).getInfoRechteck().x + (platzFuerEinBild * anzahlSpieler1 + (30 * i)), y);

                }
            }
            gamePanel.repaint();
        }

    }

    public void changeGeld(int spielerID, int geld) {
        spielerArray[spielerID - 1].setGeld(spielerArray[spielerID - 1].getGeld() + geld);
    }
}