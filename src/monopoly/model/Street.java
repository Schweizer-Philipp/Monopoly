package monopoly.model;

import java.awt.*;


public class Street {

    private final int id;

    private final Rectangle infoRechteck;
    private final Rectangle farbRechteck;

    private final int kosten;
    private final String strassennamen;

    private final Image bild;

    private final Color farbe;

    private final Strassentyp typ;


    public Street(int id, Rectangle infoRechteck, String strassennamen, int kosten, Image bild, Strassentyp typ, Rectangle farbRechteck,
                  Color farbe) {
        this.id = id;
        this.infoRechteck = infoRechteck;
        this.strassennamen = strassennamen;
        this.kosten = kosten;
        this.bild = bild;
        this.typ = typ;
        this.farbRechteck = farbRechteck;
        this.farbe = farbe;

    }

    public int getId() {
        return id;
    }

    public Rectangle getInfoRechteck() {
        return infoRechteck;
    }

    public Rectangle getFarbRechteck() {
        return farbRechteck;
    }

    public int getKosten() {
        return kosten;
    }

    public String getStrassennamen() {
        return strassennamen;
    }

    public Image getBild() {
        return bild;
    }

    public Color getFarbe() {
        return farbe;
    }

    public Strassentyp getTyp() {
        return typ;
    }


}
