package monopoly.model;

import java.awt.*;

public class StreetBuyAble {
    private final int id;
    private final double hauserZaehler;
    private final int kosten;
    private final int miete;
    private final int miete1;
    private final int miete2;
    private final int miete3;
    private final int miete4;
    private final int miete5;
    private final String strassennamen;
    private final Strassentyp typ;
    private final Rectangle farbrect;
    private final int hausKosten;
    private final Color farbe;
    private int haeuserAnzahl = 0;

    public StreetBuyAble(int iD, int miete, int miete1, int miete2, int miete3, int miete4, int mieteH, String strassennamen, Strassentyp typ,
                         int kosten, int hausKosten, Rectangle farbrec, Color farbe, double hauserZaehler) {
        this.id = iD;
        this.miete = miete;
        this.miete1 = miete1;
        this.miete2 = miete2;
        this.miete3 = miete3;
        this.miete4 = miete4;
        this.miete5 = mieteH;
        this.strassennamen = strassennamen;
        this.typ = typ;
        this.kosten = kosten;
        this.farbrect = farbrec;
        this.hausKosten = hausKosten;
        this.farbe = farbe;
        this.hauserZaehler = hauserZaehler;
    }

    public int getKosten(int a) {
        if (a == 0)
            return getMiete();
        else if (a == 1)
            return getMiete1();
        else if (a == 2)
            return getMiete2();
        else if (a == 3)
            return getMiete3();
        else if (a == 4)
            return getMiete4();
        else
            return getMiete5();


    }

    public int getHauskosten() {
        return hausKosten;
    }

    public int getId() {
        return id;
    }

    public int getKosten() {
        return kosten;
    }

    public int getMiete() {
        return miete;
    }

    public int getMiete1() {
        return miete1;
    }

    public int getMiete2() {
        return miete2;
    }

    public int getMiete3() {
        return miete3;
    }

    public int getMiete4() {
        return miete4;
    }

    public int getMiete5() {
        return miete5;
    }

    public String getStrassennamen() {
        return strassennamen;
    }

    public Strassentyp getTyp() {
        return typ;
    }

    public Rectangle getFarbrect() {
        return farbrect;
    }

    public Color getFarbe() {
        return this.farbe;
    }

    public int getHaeuserAnzahl() {
        return haeuserAnzahl;
    }

    public void setHaeuserAnzahl(int haeuserAnzahl) {
        this.haeuserAnzahl = haeuserAnzahl;
    }

    public double getHauserZaehler() {
        return hauserZaehler;
    }
}