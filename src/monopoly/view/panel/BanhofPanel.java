package monopoly.view.panel;

import monopoly.controller.manager.DateiManager;
import monopoly.controller.manager.ImageManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class BanhofPanel extends JPanel {


    private String name;

    private JLabel bild;

    private JLabel lblname;
    private JLabel lblPreis;
    private JLabel lblMiete;

    private JLabel lblMiete2;
    private JLabel lblMiete3;
    private JLabel lblMiete4;

    private GridLayout gl;

    private ArrayList<String> Bahnhoefe;

    private Font lblFont;

    public BanhofPanel() {

        initVars();

    }


    private void initVars() {
        lblMiete = new JLabel();
        lblMiete2 = new JLabel();
        lblMiete3 = new JLabel();
        lblMiete4 = new JLabel();
        lblname = new JLabel();
        lblPreis = new JLabel();
        bild = new JLabel();

        Bahnhoefe = DateiManager.dateiLesen("Bahnhof.txt");

        gl = new GridLayout(7, 1);

        lblFont = new Font("Monospaced", Font.PLAIN, 20);
    }


    public void initComps(String feldID) {

        for (String s : Bahnhoefe) {

            String[] test = s.split("@");

            if (test[0].equals(feldID)) {
                name = test[1];
                break;
            } else {
                name = "Julianhateskapiert <3";
            }

        }
        setOpaque(false);
        setLayout(gl);
        setupLabels("Bahnhof.png", bild);
        setupLabels(name, lblname);
        setupLabels("Preis:    " + "      " + "200" + " EUR", lblPreis);
        setupLabels("Miete:    " + "      " + " 25" + " EUR", lblMiete);
        setupLabels("Miete 2B:    " + "   " + " 50" + " EUR", lblMiete2);
        setupLabels("Miete 3B:    " + "   " + "100" + " EUR", lblMiete3);
        setupLabels("Miete 4B:    " + "   " + "200" + " EUR", lblMiete4);

    }

    private void setupLabels(String text, JLabel label) {
        if (label == bild) {
            label.setIcon(ImageManager.loadIcon(text));
            label.setHorizontalAlignment(SwingConstants.CENTER);
            add(label);
        } else if (label == lblname) {
            label.setText(text);
            label.setFont(lblFont);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            add(label);
        } else {
            label.setText(text);
            label.setFont(lblFont);
            add(label);
        }
    }
}
