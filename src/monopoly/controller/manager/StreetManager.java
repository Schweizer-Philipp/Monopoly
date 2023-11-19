package monopoly.controller.manager;

import monopoly.model.Strassentyp;
import monopoly.model.Street;
import monopoly.view.panel.GamePanel;

import java.awt.*;
import java.util.ArrayList;

public class StreetManager {
    private static ArrayList<Street> spielfeld;
    private final GamePanel gamePanel;
    int mitzaehlerx;
    int mitzaehlery;
    int width;
    private ArrayList<String> spielfeldData;
    private Rectangle farbRechteck;
    private Color farbe;
    private int kosten;


    public StreetManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        initVars();
        erstelleStrassen();
    }

    public static ArrayList<Street> getSpielfeld() {
        return spielfeld;
    }

    private void initVars() {
        spielfeldData = new ArrayList<String>();

        spielfeld = new ArrayList<Street>();

        mitzaehlerx = gamePanel.getMaxXandY().x / 13;
    }

    public void erstelleStrassen() {

        spielfeldData = DateiManager.dateiLesen("Spielfeld.txt");
        //sE= strasseEigenschaften

        for (int i = 0; i < 40; i++) {
            String strasse = spielfeldData.get(i);

            String[] sE = strasse.split("\\@");

            if (sE[9].equals("null")) {
                farbRechteck = null;
                farbe = null;
            } else {
                farbRechteck = new Rectangle(Integer.parseInt(sE[9]), Integer.parseInt(sE[10]), Integer.parseInt(sE[11]), Integer.parseInt(sE[12]));
                farbe = Color.decode(sE[13]);
            }
            if (sE[6].equals("null")) {
                kosten = 0;
            } else {
                kosten = Integer.parseInt(sE[6]);
            }

            Street s = new Street(Integer.parseInt(sE[0]), new Rectangle(Integer.parseInt(sE[1]), Integer.parseInt(sE[2]), Integer.parseInt(sE[3]), Integer.parseInt(sE[4])),
                    sE[5], kosten, (!sE[7].contains("Icons") ? null : ImageManager.loadImage(sE[7])), Strassentyp.getStrassentyp(Integer.parseInt(sE[8])), this.farbRechteck, this.farbe);
            spielfeld.add(s);

        }

    }
}
