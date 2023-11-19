package monopoly.model;


import monopoly.controller.manager.ImageManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Spieler {

    private final Point point;
    private final Image spielFigur;
    private final Color farbe;
    private final String SpielerName;
    private final String icon;
    private int id;
    private int geld;
    private boolean ausDemGefaengnis;
    private boolean spielerladen;
    private int spielerID;
    private ArrayList<StreetBuyAble> strasse;
    private boolean gefaengnisStatus = false;
    private String passwort;
    private int rundeGefaengis = 1;
    private int gNummer;
    private int eNummer;
    private int paschZaehler;

    public Spieler(int id, int geld, Point point, boolean ausDemGefaengnis, String spielerName, Color farbe, int zaehler) {
        this.id = id;
        this.geld = geld;
        this.point = point;
        this.ausDemGefaengnis = ausDemGefaengnis;
        this.spielFigur = ImageManager.loadImage("/Icons/Spielfiguren/SpielFigur" + zaehler + ".png");
        this.icon = "SpielFigur" + zaehler + ".png";
        this.spielerID = zaehler;
        this.SpielerName = spielerName;
        this.farbe = farbe;
        this.strasse = new ArrayList<StreetBuyAble>();
    }

    public String getSpielerName() {
        return SpielerName;
    }

    public String getIcon() {
        return icon;
    }

    public Color getColor() {
        return farbe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;

    }

    public int getGeld() {
        return geld;
    }

    public void setGeld(int geld) {
        this.geld = geld;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(int x, int y) {
        this.point.setLocation(x, y);

    }

    public boolean getAusDemGefaengnis() {
        return ausDemGefaengnis;
    }

    public void setAusDemGefaengnis(boolean ausDemGefaengnis) {
        this.ausDemGefaengnis = ausDemGefaengnis;
    }

    public boolean isSpielerladen() {
        return spielerladen;
    }

    public void setSpielerladen(boolean spielerladen) {
        this.spielerladen = spielerladen;
    }

    public void removeStreet(Street street) {
        for (StreetBuyAble s : strasse)
            if (s.getId() == street.getId())
                strasse.remove(s);
    }

    public void addStreet(StreetBuyAble street) {
        if (street != null)
            strasse.add(street);
    }

    public void setstrasse(ArrayList<StreetBuyAble> lit) {
        this.strasse = lit;
    }

    public void changelist(StreetBuyAble[] street) {
        strasse = new ArrayList<StreetBuyAble>(Arrays.asList(street));
    }

    public ArrayList<StreetBuyAble> getStreet() {
        return strasse;
    }

    public Image getSpielFigur() {
        return spielFigur;
    }

    public int getSpielerID() {
        return spielerID;
    }

    public void setSpielerID(int id) {
        this.spielerID = id;
    }

    public boolean getGefaengnisStatus() {
        return gefaengnisStatus;
    }

    public void setGefaengnisStatus(boolean gefaengnisStatus) {
        this.gefaengnisStatus = gefaengnisStatus;
    }

    public int getRundeGefaengis() {
        return rundeGefaengis;
    }

    public void setRundeGefaengis(int rundeGefaengis) {
        this.rundeGefaengis = rundeGefaengis;
    }

    public int getgNummer() {
        return gNummer;
    }

    public void setgNummer(int gNummer) {
        this.gNummer = gNummer;
    }

    public int geteNummer() {
        return eNummer;
    }

    public void seteNummer(int eNummer) {
        this.eNummer = eNummer;
    }

    public int getPaschZaehler() {
        return paschZaehler;
    }

    public void setPaschZaehler(int paschZaehler) {
        if (paschZaehler == 0) {
            this.paschZaehler = 0;
        } else {
            this.paschZaehler = this.paschZaehler + paschZaehler;
        }

    }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }
}
