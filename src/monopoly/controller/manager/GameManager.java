package monopoly.controller.manager;

import monopoly.model.Spieler;
import monopoly.model.Strassentyp;
import monopoly.model.Street;
import monopoly.model.StreetBuyAble;
import monopoly.view.GameGrid;
import monopoly.view.InfoGrid;
import monopoly.view.panel.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameManager {

    private static int aktSpielerID = 1;
    private static int aktgKarte = 1;
    private static int akteKarte = 1;
    private static ArrayList<StreetBuyAble> strassenDieZuKaufenSind;
    private final SteuernPanel steuernPanel;
    private final InfoGrid infoGrid;
    private final StrassenPanel strassenPanel;
    private final WerkePanel werkePanel;
    private final BanhofPanel bahnhofPanel;
    private final EckFelderPanel eckFelderPanel;
    private final GemeinschaftsFeldPanel gPanel;
    private final EreignisFeldPanel ePanel;
    private final SpielerPanel spielerPanel;
    private final StrassenSpielerPanel strassenSpielerPanel;
    private final ButtonPanel buttonPanel;
    private final GameGrid gameGrid;
    private int wuerfel;
    private int newID;
    private ArrayList<String> strassen;
    private ArrayList<String> moveNachricht;

    public GameManager(boolean neuesSpiel, GameGrid gameGrid, ButtonPanel buttonPanel, InfoGrid infoGrid, SpielerPanel spielerPanel, StrassenSpielerPanel strassenSpielerPanel, StrassenPanel strassenPanel,
                       WerkePanel werkePanel, BanhofPanel bahnhofPanel,
                       EckFelderPanel eckFelderPanel, GemeinschaftsFeldPanel gPanel, EreignisFeldPanel ePanel, SteuernPanel steuernPanel) {
        this.gameGrid = gameGrid;
        this.buttonPanel = buttonPanel;
        this.infoGrid = infoGrid;
        this.strassenPanel = strassenPanel;
        this.werkePanel = werkePanel;
        this.eckFelderPanel = eckFelderPanel;
        this.gPanel = gPanel;
        this.ePanel = ePanel;
        this.spielerPanel = spielerPanel;
        this.strassenSpielerPanel = strassenSpielerPanel;
        this.bahnhofPanel = bahnhofPanel;
        this.steuernPanel = steuernPanel;

        if (neuesSpiel) {
            initvars1();
        } else {
            initvars();
        }

    }

    public static int getakt() {
        return aktSpielerID;
    }

    public static int getaktg() {
        return aktgKarte;
    }

    public static int getakte() {
        return akteKarte;
    }

    public static int getaktid() {
        return SpielerManager.getSpielerarray()[aktSpielerID - 1].getId();
    }

    public static ArrayList<StreetBuyAble> getavalestreet() {
        return strassenDieZuKaufenSind;
    }

    private void initvars1() {
        strassenDieZuKaufenSind = new ArrayList<StreetBuyAble>();
        moveNachricht = DateiManager.dateiLesen("moveNachricht.txt");
        strassen = DateiManager.getStrassen();
        for (String s : strassen) {
            String[] test1 = s.split("@");
            Color farbe1 = SpielerManager.getfarbe(Integer.valueOf(test1[0]));

            StreetBuyAble sreet = new StreetBuyAble(Integer.valueOf(test1[0]), Integer.valueOf(test1[1]),
                    Integer.valueOf(test1[2]), Integer.valueOf(test1[3]), Integer.valueOf(test1[4]),
                    Integer.valueOf(test1[5]), Integer.valueOf(test1[6]), test1[7], Strassentyp.getStrassentyp(Integer.valueOf(test1[8])),
                    Integer.valueOf(test1[9]), Integer.valueOf(test1[10]), new Rectangle(Integer.valueOf(test1[11]),
                    Integer.valueOf(test1[12]), Integer.valueOf(test1[13]), Integer.valueOf(test1[14])),
                    farbe1, Double.valueOf(test1[15]));
            strassenDieZuKaufenSind.add(sreet);
        }
        aktgKarte = Integer.valueOf(DateiManager.getEinstellungen(1));
        akteKarte = Integer.valueOf(DateiManager.getEinstellungen(2));
        aktSpielerID = Integer.valueOf(DateiManager.getEinstellungen(3));


    }

    private void initvars() {
        strassenDieZuKaufenSind = new ArrayList<StreetBuyAble>();
        moveNachricht = DateiManager.dateiLesen("moveNachricht.txt");
        strassen = DateiManager.dateiLesen("Strassenbuyable.txt");
        for (String s : strassen) {
            String[] test = s.split("@");
            if (Boolean.valueOf(test[0])) {

                StreetBuyAble street = new StreetBuyAble(Integer.valueOf(test[1]), Integer.valueOf(test[2]),
                        Integer.valueOf(test[3]), Integer.valueOf(test[4]), Integer.valueOf(test[5]), Integer.valueOf(test[6]),
                        Integer.valueOf(test[7]), test[8], Strassentyp.getStrassentyp(Integer.parseInt(test[9])),
                        Integer.valueOf(test[10]), Integer.valueOf(test[11]),
                        new Rectangle(Integer.valueOf(test[12]), Integer.valueOf(test[13]),
                                Integer.valueOf(test[14]), Integer.valueOf(test[15])), Color.decode(test[16]), Double.valueOf(test[17]));
                strassenDieZuKaufenSind.add(street);
            } else {
                StreetBuyAble street = new StreetBuyAble(Integer.valueOf(test[1]), 0, 0, 0, 0, 0, 0,
                        test[8], Strassentyp.getStrassentyp(Integer.parseInt(test[9])), Integer.valueOf(test[10]), Integer.valueOf(test[11]),
                        new Rectangle(Integer.valueOf(test[12]), Integer.valueOf(test[13]), Integer.valueOf(test[14])
                                , Integer.valueOf(test[15])), Color.WHITE, 0);
                strassenDieZuKaufenSind.add(street);
            }

        }
    }

    public boolean wuerfeln() {
        String[] wuerfelErgebnis = SpielerManager.wuerfeln();
        wuerfel = Integer.valueOf(wuerfelErgebnis[1]);
        newID = SpielerManager.getSpielerarray()[aktSpielerID - 1].getId() + Integer.valueOf(wuerfelErgebnis[1]);
        SpielerManager.getSpielerarray()[aktSpielerID - 1].setPaschZaehler(wuerfelErgebnis[0].equals("true") ? 1 : 0);
        if (SpielerManager.getSpielerarray()[aktSpielerID - 1].getPaschZaehler() < 3) {
            if (newID > 39) {
                newID = newID - 40;
                los();
            }
        }
        if (newID > 39) {
            newID = newID - 40;
        }

        String ersteStrasse = "";
        String zweiteStrasse = "";
        if (Boolean.valueOf(wuerfelErgebnis[0]) && SpielerManager.getSpielerarray()[aktSpielerID - 1].getGefaengnisStatus()) {
            SpielerManager.getSpielerarray()[aktSpielerID - 1].setGefaengnisStatus(false);
            JOptionPane.showMessageDialog(null, "Da sie einen Pasch gewuerfelt haben sind Sie Frei!", "Gefaengnis", JOptionPane.INFORMATION_MESSAGE);
            SpielerManager.getSpielerarray()[aktSpielerID - 1].setRundeGefaengis(1);
            SpielerManager.getSpielerarray()[aktSpielerID - 1].setPaschZaehler(0);
        }

        if (!SpielerManager.getSpielerarray()[aktSpielerID - 1].getGefaengnisStatus() || Boolean.valueOf(wuerfelErgebnis[0])) {
            for (String s : moveNachricht) {
                String[] test = s.split("@");

                if (test[0].equals(String.valueOf(SpielerManager.getSpielerarray()[aktSpielerID - 1].getId()))) {
                    ersteStrasse = test[1];
                }
                if (test[0].equals(String.valueOf(newID))) {
                    zweiteStrasse = test[2];
                }
            }


            if (Boolean.valueOf(wuerfelErgebnis[0])) {
                JOptionPane.showMessageDialog(null, "Sie haben eine " + wuerfelErgebnis[2] + " und " + wuerfelErgebnis[3] + " gewuerfelt!\n" + SpielerManager.getSpielerarray()[aktSpielerID - 1].getSpielerName() + " ist " + ersteStrasse + " " + zweiteStrasse +
                        " gezogen", "Zuginfo", JOptionPane.INFORMATION_MESSAGE);

                new Thread(() -> playerMovment()).start();

                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Sie haben eine " + wuerfelErgebnis[2] + " und " + wuerfelErgebnis[3] + " gewuerfelt!\n" + SpielerManager.getSpielerarray()[aktSpielerID - 1].getSpielerName() + " ist " + ersteStrasse + " " + zweiteStrasse +
                        " gezogen", "Zuginfo", JOptionPane.INFORMATION_MESSAGE);

                new Thread(() -> playerMovment()).start();
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Sie haben eine " + wuerfelErgebnis[2] + " und " + wuerfelErgebnis[3] + " gewuerfelt!"
                    , "Zuginfo", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

    }

    public boolean[] setInfoGridUp(boolean status) {
        int feldID = SpielerManager.getSpielerarray()[aktSpielerID - 1].getId(); // das will ich net wissen wollte nur wissen was die zeile drueber macht :D
        Strassentyp typ = null;
        Color farbe = null;

        boolean[] buttons = new boolean[2];

        for (Street s : StreetManager.getSpielfeld()) {

            if (s.getId() == feldID) {
                typ = s.getTyp();
                farbe = s.getFarbe();
            }
        }
        if (typ.equals(Strassentyp.STRAssE)) {
            strassenPanel.aktuelleStrasseermitteln(String.valueOf(feldID));
            infoGrid.switchPanel("StrassenPanel");
            buttons[0] = false;
            double check = 0;
            for (StreetBuyAble s : SpielerManager.getSpielerarray()[aktSpielerID - 1].getStreet()) {
                if (s.getFarbe().equals(farbe)) {
                    check = check + s.getHauserZaehler();
                }
            }
            buttons[0] = check == 3;

            for (StreetBuyAble s : strassenDieZuKaufenSind) {
                if (s.getId() == newID) {
                    buttons[1] = true;
                    break;
                }
                buttons[1] = false;

            }
            if (status && !buttons[1])
                miete();
            return buttons;
        } else if (typ.equals(Strassentyp.BAHNHOF)) {
            bahnhofPanel.initComps(String.valueOf(feldID));
            infoGrid.switchPanel("BahnhofPanel");
            buttons[0] = false;
            for (StreetBuyAble s : strassenDieZuKaufenSind) {
                if (s.getId() == newID) {
                    buttons[1] = true;
                    break;
                }
                buttons[1] = false;

            }
            if (status && !buttons[1])
                miete();
            return buttons;

        } else if (typ.equals(Strassentyp.EREIGNISFELD)) {
            if (status) {
                SpielerManager.getSpielerarray()[aktSpielerID - 1].seteNummer(akteKarte);
                infoGrid.switchPanel("EPanel");
                int test = ePanel.setUpEreignisfeld(String.valueOf(akteKarte), true, aktSpielerID, this, buttonPanel);
                akteKarte++;
                if (akteKarte > 12) {
                    akteKarte = 1;
                }
                for (StreetBuyAble s : strassenDieZuKaufenSind) {
                    if (s.getId() == test) {
                        buttons[0] = false;
                        buttons[1] = true;
                        return buttons;
                    }
                }
                buttons[0] = false;
                buttons[1] = false;
                if (status && !buttons[1] && test < 80)
                    miete();
                return buttons;
            } else {
                ePanel.setUpEreignisfeld(String.valueOf(SpielerManager.getSpielerarray()[aktSpielerID - 1].geteNummer()), false, aktSpielerID, this, buttonPanel);
                infoGrid.switchPanel("EPanel");
                buttons[0] = false;
                buttons[1] = false;
                return buttons;
            }

        } else if (typ.equals(Strassentyp.GEFAeNGNIS) || typ.equals(Strassentyp.LOS)
                || typ.equals(Strassentyp.PARKEN) || typ.equals(Strassentyp.GEHE_INS_GEFAeNGNIS)) {
            if (typ.equals(Strassentyp.PARKEN) && status) {
                int geld = gameGrid.clearFreiParken();
                SpielerManager.getSpielerarray()[aktSpielerID - 1].setGeld(SpielerManager.getSpielerarray()[aktSpielerID - 1].getGeld() + geld);
                JOptionPane.showMessageDialog(null, SpielerManager.getSpielerarray()[aktSpielerID - 1].getSpielerName() + " hat durch Freiparken "
                        + geld + "EUR bekommen", "Freiparken", JOptionPane.INFORMATION_MESSAGE);
                setSpielerPanel();
            }
            eckFelderPanel.setUpEckfelder(String.valueOf(feldID));
            infoGrid.switchPanel("EckfelderPanel");
            buttons[0] = false;
            buttons[1] = false;
            return buttons;
        } else if (typ.equals(Strassentyp.GEMEINSCHAFTSFELD)) {
            if (status) {
                SpielerManager.getSpielerarray()[aktSpielerID - 1].setgNummer(aktgKarte);
                infoGrid.switchPanel("GPanel");
                gPanel.setUpGemeinschaftsfeld(String.valueOf(aktgKarte), true, aktSpielerID, this);
                aktgKarte++;
                if (aktgKarte > 15) {
                    aktgKarte = 1;
                }
                buttons[0] = false;
                buttons[1] = false;
                return buttons;
            } else if (!status) {
                gPanel.setUpGemeinschaftsfeld(String.valueOf(SpielerManager.getSpielerarray()[aktSpielerID - 1].getgNummer()), false, aktSpielerID, this);
                infoGrid.switchPanel("GPanel");
                buttons[0] = false;
                buttons[1] = false;
                return buttons;
            }

        } else if (typ.equals(Strassentyp.WERK)) {
            werkePanel.initComps(String.valueOf(feldID));
            infoGrid.switchPanel("WerkePanel");
            buttons[0] = false;
            for (StreetBuyAble s : strassenDieZuKaufenSind) {
                if (s.getId() == newID) {
                    buttons[1] = true;
                    break;
                }
                buttons[1] = false;

            }
            if (status && !buttons[1])
                miete();

            return buttons;
        } else if (typ.equals(Strassentyp.ZUSATZSTEUER) || typ.equals(Strassentyp.EINKOMENSTEUER)) {
            if (status) {
                if (typ.equals(Strassentyp.ZUSATZSTEUER)) {
                    gameGrid.erhoeheFreiParken(100);
                    JOptionPane.showMessageDialog(null, "Sie haben durch das Feld ZusatzSteuer 100EUR verloren", "Zusatzsteuer", JOptionPane.INFORMATION_MESSAGE);
                    SpielerManager.getSpielerarray()[aktSpielerID - 1].setGeld(SpielerManager.getSpielerarray()[aktSpielerID - 1].getGeld() - 100);
                    setSpielerPanel();
                    if (SpielerManager.getSpielerarray()[aktSpielerID - 1].getGeld() <= 0) {
                        spielerPleite(aktSpielerID);
                    }
                } else if (typ.equals(Strassentyp.EINKOMENSTEUER)) {
                    gameGrid.erhoeheFreiParken(200);
                    JOptionPane.showMessageDialog(null, "Sie haben durch das Feld Einkommenssteuern 200EUR verloren", "Zusatzsteuer", JOptionPane.INFORMATION_MESSAGE);
                    SpielerManager.getSpielerarray()[aktSpielerID - 1].setGeld(SpielerManager.getSpielerarray()[aktSpielerID - 1].getGeld() - 200);
                    setSpielerPanel();
                    if (SpielerManager.getSpielerarray()[aktSpielerID - 1].getGeld() <= 0) {
                        spielerPleite(aktSpielerID);
                    }
                }
            }
            steuernPanel.setUpPanel(String.valueOf(feldID));
            infoGrid.switchPanel("SteuernPanel");
            buttons[0] = false;
            buttons[1] = false;
            return buttons;
        }
        return buttons;
    }

    public boolean addStreetBuyAble(int andere) {

        for (StreetBuyAble s : strassenDieZuKaufenSind) {
            int geld = SpielerManager.getSpielerarray()[aktSpielerID - 1].getGeld() - s.getKosten();
            if (s.getId() == newID || s.getId() == andere) {
                if (geld > 0) {
                    int b = JOptionPane.showConfirmDialog(null, "Sind Sie sicher das sie die " + s.getStrassennamen() + " kaufen wollen?",
                            "Strassenkauf", JOptionPane.YES_NO_OPTION);

                    if (b == 0) {
                        SpielerManager.getSpielerarray()[aktSpielerID - 1].setGeld(geld);
                        SpielerManager.getSpielerarray()[aktSpielerID - 1].addStreet(s);
                        strassenDieZuKaufenSind.remove(s);
                        JOptionPane.showMessageDialog(null, "Sie haben die Strasse " + s.getStrassennamen() + " gekauft!", "Strassenkauf", JOptionPane.INFORMATION_MESSAGE);
                        return true;
                    } else if (b == 1) {
                        JOptionPane.showMessageDialog(null, "Sie haben die Strasse " + s.getStrassennamen() + " nicht gekauft", "Strassenkauf", JOptionPane.INFORMATION_MESSAGE);
                        return false;
                    } else if (b == -1) {
                        return false;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Da sie nach dem kauf nurnoch " +
                            geld + "EUR haetten durfen sie die Strasse nicht kaufen", "Strassenkauf", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }

        }
        return true;
    }

    public void gefaengis() {
        if (newID == 30 || SpielerManager.getSpielerarray()[aktSpielerID - 1].getPaschZaehler() >= 3) {
            if (!SpielerManager.getSpielerarray()[aktSpielerID - 1].getAusDemGefaengnis()) {
                SpielerManager.getSpielerarray()[aktSpielerID - 1].setGefaengnisStatus(true);
                JOptionPane.showMessageDialog(null, "Sie sind nun im Gefaengnis, da sie Entweder auf dem Feld ins Gefaengnis gelandet sind oder 3 mal ein Pasch hatten", "Gefaengnis", JOptionPane.INFORMATION_MESSAGE);
            } else {
                SpielerManager.getSpielerarray()[aktSpielerID - 1].setAusDemGefaengnis(false);
                JOptionPane.showMessageDialog(null, "Da sie die Gefaengnisfrei karte hatten sind sie nicht im Gefaengnis", "Gefaengnis", JOptionPane.INFORMATION_MESSAGE);
                SpielerManager.getSpielerarray()[aktSpielerID - 1].setRundeGefaengis(1);
                SpielerManager.getSpielerarray()[aktSpielerID - 1].setPaschZaehler(0);
            }

            GameGrid.getSpielermanager().changePositionofplayer(aktSpielerID, 10);
            SpielerManager.getSpielerarray()[aktSpielerID - 1].setId(10);

        }
    }

    public boolean checkGefaengis() {
        if (SpielerManager.getSpielerarray()[aktSpielerID - 1].getGefaengnisStatus()) {
            if (SpielerManager.getSpielerarray()[aktSpielerID - 1].getRundeGefaengis() >= 4) {
                JOptionPane.showMessageDialog(null, "Sie kommen aus dem GefaengisFrei da dies ihre 4 Runde ist", "Gefaengnis",
                        JOptionPane.INFORMATION_MESSAGE);
                SpielerManager.getSpielerarray()[aktSpielerID - 1].setGefaengnisStatus(false);
                SpielerManager.getSpielerarray()[aktSpielerID - 1].setRundeGefaengis(1);
                SpielerManager.getSpielerarray()[aktSpielerID - 1].setPaschZaehler(0);
                return false;
            } else {
                JOptionPane.showMessageDialog(null, "Sie sind die " + SpielerManager.getSpielerarray()[aktSpielerID - 1].getRundeGefaengis() +
                        " Runde im Gefaengnis", "Gefaengnis", JOptionPane.INFORMATION_MESSAGE);
                SpielerManager.getSpielerarray()[aktSpielerID - 1].setRundeGefaengis(SpielerManager.getSpielerarray()[aktSpielerID - 1].getRundeGefaengis() + 1);
                return true;
            }
        } else {
            return false;
        }

    }

    public void miete() {
        Spieler spieler = null;
        StreetBuyAble street = null;
        for (Spieler s : SpielerManager.getSpielerarray()) {

            for (StreetBuyAble st : s.getStreet()) {

                if (st.getId() == SpielerManager.getSpielerarray()[aktSpielerID - 1].getId()) {
                    spieler = s;
                    street = st;
                }
            }
        }

        if (SpielerManager.getSpielerarray()[aktSpielerID - 1].getSpielerID() == spieler.getSpielerID()) {
            if (street.getTyp().equals(Strassentyp.STRAssE)) {
                JOptionPane.showMessageDialog(null, "Willkommen auf ihrer " + street.getStrassennamen() + "!", "Willkommen", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Willkommen auf ihrerm " + street.getStrassennamen() + "!", "Willkommen", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            if (street.getTyp().equals(Strassentyp.STRAssE)) {
                int haeuser = street.getHaeuserAnzahl();
                int kosten = street.getKosten(haeuser);

                SpielerManager.getSpielerarray()[spieler.getSpielerID() - 1].setGeld(SpielerManager.getSpielerarray()[spieler.getSpielerID() - 1].getGeld() + kosten);
                SpielerManager.getSpielerarray()[aktSpielerID - 1].setGeld(SpielerManager.getSpielerarray()[aktSpielerID - 1].getGeld() - kosten);
                JOptionPane.showMessageDialog(null, SpielerManager.getSpielerarray()[aktSpielerID - 1].getSpielerName() +
                        " muss " + kosten + "EUR an " + SpielerManager.getSpielerarray()[spieler.getSpielerID() - 1].getSpielerName() + " zahlen!", "Miete", JOptionPane.INFORMATION_MESSAGE);
                setSpielerPanel();
                if (SpielerManager.getSpielerarray()[aktSpielerID - 1].getGeld() <= 0) {
                    spielerPleite(aktSpielerID);
                }
            } else if (street.getTyp().equals(Strassentyp.BAHNHOF)) {
                int kosten = 25;
                int bahnhoefe = 0;
                for (StreetBuyAble s : spieler.getStreet()) {
                    if (s.getTyp().equals(Strassentyp.BAHNHOF)) {
                        bahnhoefe++;
                    }
                }
                for (int i = 1; i < bahnhoefe; i++) {
                    kosten = kosten * 2;
                }
                SpielerManager.getSpielerarray()[spieler.getSpielerID() - 1].setGeld(SpielerManager.getSpielerarray()[spieler.getSpielerID() - 1].getGeld() + kosten);
                SpielerManager.getSpielerarray()[aktSpielerID - 1].setGeld(SpielerManager.getSpielerarray()[aktSpielerID - 1].getGeld() - kosten);
                JOptionPane.showMessageDialog(null, SpielerManager.getSpielerarray()[aktSpielerID - 1].getSpielerName() +
                        " muss " + kosten + "EUR an " + SpielerManager.getSpielerarray()[spieler.getSpielerID() - 1].getSpielerName() + " zahlen!", "Miete", JOptionPane.INFORMATION_MESSAGE);
                setSpielerPanel();
                if (SpielerManager.getSpielerarray()[aktSpielerID - 1].getGeld() <= 0) {
                    spielerPleite(aktSpielerID);
                }
            } else if (street.getTyp().equals(Strassentyp.WERK)) {
                int kosten;
                double werke = 0;
                for (StreetBuyAble s : spieler.getStreet()) {
                    if (s.getTyp().equals(Strassentyp.WERK)) {

                        if (werke == 1) {
                            werke = 2.5;
                        } else {
                            werke++;
                        }
                    }
                }
                kosten = (int) (werke * 4 * wuerfel);
                SpielerManager.getSpielerarray()[spieler.getSpielerID() - 1].setGeld(SpielerManager.getSpielerarray()[spieler.getSpielerID() - 1].getGeld() + kosten);
                SpielerManager.getSpielerarray()[aktSpielerID - 1].setGeld(SpielerManager.getSpielerarray()[aktSpielerID - 1].getGeld() - kosten);
                JOptionPane.showMessageDialog(null, SpielerManager.getSpielerarray()[aktSpielerID - 1].getSpielerName() +
                        " muss " + kosten + "EUR an " + SpielerManager.getSpielerarray()[spieler.getSpielerID() - 1].getSpielerName() + " zahlen!", "Miete", JOptionPane.INFORMATION_MESSAGE);
                setSpielerPanel();
                if (SpielerManager.getSpielerarray()[aktSpielerID - 1].getGeld() <= 0) {
                    spielerPleite(aktSpielerID);
                }
            }

        }


    }

    public void hauserKaufen() {
        String[] test = {"1Haus", "2Haeuser", "3Haeuser", "4Haeuser", "1Hotel"};

        int a = JOptionPane.showOptionDialog(null, "Was wollen Sie bauen", "Bauen",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                (ImageIcon) ImageManager.resizeImageOrIcon(ImageManager.loadIcon("bau.png"), 50, 50), test, null) + 1;

        if (a != 0) {
            for (StreetBuyAble s : SpielerManager.getSpielerarray()[aktSpielerID - 1].getStreet()) {
                if (s.getId() == newID) {
                    if (a > s.getHaeuserAnzahl()) {
                        int haeuseranzahl = a - s.getHaeuserAnzahl();
                        int haeuserkosten = haeuseranzahl * s.getHauskosten();
                        if (SpielerManager.getSpielerarray()[aktSpielerID - 1].getGeld() > haeuserkosten) {
                            s.setHaeuserAnzahl(a);
                            SpielerManager.getSpielerarray()[aktSpielerID - 1].setGeld(SpielerManager.getSpielerarray()[aktSpielerID - 1].getGeld() - haeuserkosten);
                            JOptionPane.showMessageDialog(null, "Sie haben nun auf ihrer " + s.getStrassennamen() + " " + a + " Haeuser!", "Haeuserkauf", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Sie haben nicht genug Geld um " + haeuseranzahl + " Haeuser zu kaufen");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Sie haben bereits auf der " + s.getStrassennamen() + " " + s.getHaeuserAnzahl() + " Haeuser!", "Haeuserkauf", JOptionPane.INFORMATION_MESSAGE);
                        hauserKaufen();
                    }
                }
            }
        }
    }

    public void spielerPleite(int spielerID) {
        String name = SpielerManager.getSpielerarray()[spielerID - 1].getSpielerName();
        for (StreetBuyAble s : SpielerManager.getSpielerarray()[spielerID - 1].getStreet()) {
            s.setHaeuserAnzahl(0);
            strassenDieZuKaufenSind.add(s);
        }
        ArrayList<Spieler> spieler = new ArrayList<Spieler>(Arrays.asList(SpielerManager.getSpielerarray()));
        for (Spieler s : spieler) {
            if (s.getSpielerID() > spielerID) {
                s.setSpielerID(s.getSpielerID() - 1);
            }
        }
        spieler.remove(spielerID - 1);
        Spieler[] aktspieler = new Spieler[spieler.size()];
        spieler.toArray(aktspieler);
        if (aktspieler.length == 1) {
            JOptionPane.showMessageDialog(null, aktspieler[0].getSpielerName() + " hat Gewonnen er ist der Monopoly King!", "Gewonnen",
                    JOptionPane.INFORMATION_MESSAGE, (Icon) ImageManager.resizeImageOrIcon(ImageManager.loadIcon("sieg.png"), 100, 50));
            JOptionPane.showMessageDialog(null, "Wir hoffen ihnen hat es gefallen!:)\nIhr Programmier Team : Julian, Philipp und Ideen einbringer Yannic", "Credits", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
        SpielerManager.setSpielerarray(aktspieler);
        JOptionPane.showMessageDialog(null, "Der Spieler " + name + " ist Pleite!", "Pleite", JOptionPane.INFORMATION_MESSAGE);
    }

    public void los() {
        SpielerManager.getSpielerarray()[aktSpielerID - 1].setGeld(SpielerManager.getSpielerarray()[aktSpielerID - 1].getGeld() + 200);
        JOptionPane.showMessageDialog(null, "Da Sie ueber Los gezogen sind, erhalten Sie 200 M", "Losuebergang",
                JOptionPane.INFORMATION_MESSAGE, (Icon) ImageManager.resizeImageOrIcon(ImageManager.loadIcon("geld.jpg"), 50, 50));

    }

    public void setSpielerStrassenPanel() {
        strassenSpielerPanel.spielerID(aktSpielerID);
    }

    public void setSpielerPanel() {
        spielerPanel.setSpielerID(aktSpielerID);
    }

    public void setAktSpieler() {
        aktSpielerID = aktSpielerID + 1;
        if (aktSpielerID > SpielerManager.getSpielerarray().length) {
            aktSpielerID = 1;
        }
    }

    public ArrayList<StreetBuyAble> getStrassenDieZuKaufenSind() {
        return strassenDieZuKaufenSind;
    }

    private void playerMovment() {
        // keine ahnung das ding hat keine lust mehr hmm ook komisch neu esitzung machen?=
        int altesfeld = SpielerManager.getSpielerarray()[aktSpielerID - 1].getId();
        SpielerManager.getSpielerarray()[aktSpielerID - 1].setId(newID);
        int i = 0; // <--?? warum faengt i bei 1 an?


        while (i < wuerfel) {
            altesfeld++;

            if (altesfeld > 39)
                altesfeld = 0;

            GameGrid.getSpielermanager().changePositionofplayer(aktSpielerID, altesfeld);
            i++;
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(GameManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
