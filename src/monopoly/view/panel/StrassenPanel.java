package monopoly.view.panel;

import monopoly.controller.manager.DateiManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class StrassenPanel extends JPanel implements ActionListener {


    private GridLayout gLayout;


    private JLabel lblPreis;
    private JLabel lblMiete;
    private JLabel lblMiete1;
    private JLabel lblMiete2;
    private JLabel lblMiete3;
    private JLabel lblMiete4;
    private JLabel lblMieteH;
    private JLabel lblKHaus;

    private JButton btnname;


    private ArrayList<String> strassenDaten;

    private Font lblFont;

    private boolean statusLbl = true;
    private boolean test4;


    private int zaehler;

    public StrassenPanel() {

        initComps();
        initVars();


    }

    private void initComps() {

        gLayout = new GridLayout(9, 1);

        lblKHaus = new JLabel();
        lblMiete = new JLabel();
        lblMiete1 = new JLabel();
        lblMiete2 = new JLabel();
        lblMiete3 = new JLabel();
        lblMiete4 = new JLabel();
        lblMieteH = new JLabel();
        lblPreis = new JLabel();

        btnname = new JButton();
        btnname.addActionListener(this);

        lblFont = new Font("Monospaced", Font.PLAIN, 20);

        strassenDaten = DateiManager.dateiLesen("Strassen.txt");

    }

    private void initVars() {

        setOpaque(false);
        setLayout(gLayout);

    }

    private void setUpStrassenInfosJLabels(JComponent comp, String inhalt, String farbe) {


        if (comp instanceof JButton) {
            Color color = Color.decode(farbe);
            comp.setForeground(Color.BLACK);
            comp.setFont(lblFont);
            ((JButton) comp).setText(inhalt);
            comp.setBackground(color);
            ((JButton) comp).setActionCommand("Strassen");
            add(comp);
        } else if (comp instanceof JLabel) {
            ((JLabel) comp).setText(inhalt);
            comp.setFont(lblFont);
            add(comp);
        }
        revalidate();
        repaint();

    }


    public void aktuelleStrasseermitteln(String feldID) {
        zaehler = 0;
        for (String s : strassenDaten) {
            zaehler++;
            String[] test = s.split("@");
            if (test[0].equals(feldID)) {

                test4 = true;
                break;
            } else {
                test4 = false;
            }

        }
        if (test4) {

            zaehler = zaehler - 1;

            String test = strassenDaten.get(zaehler);

            String[] test1 = test.split("@");

            removeAll();
            setUpStrassenInfosJLabels(btnname, test1[1], test1[10]);
            setUpStrassenInfosJLabels(lblPreis, "Preis:    " + "      " + test1[2] + "EUR", null);
            setUpStrassenInfosJLabels(lblMiete, "Miete:    " + "      " + test1[4] + "EUR", null);
            setUpStrassenInfosJLabels(lblMiete1, "Miete 1H:    " + "   " + test1[5] + "EUR", null);
            setUpStrassenInfosJLabels(lblMiete2, "Miete 2H:    " + "   " + test1[6] + "EUR", null);
            setUpStrassenInfosJLabels(lblMiete3, "Miete 3H:    " + "   " + test1[7] + "EUR", null);
            setUpStrassenInfosJLabels(lblMiete4, "Miete 4H:    " + "   " + test1[8] + "EUR", null);
            setUpStrassenInfosJLabels(lblMieteH, "Miete Hotel:    " + test1[9] + "EUR", null);
            setUpStrassenInfosJLabels(lblKHaus, "HausK:    " + "      " + test1[3] + "EUR", null);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Strassen")) {
            statusLbl = !statusLbl;
            lblKHaus.setVisible(statusLbl);
            lblMiete.setVisible(statusLbl);
            lblMiete1.setVisible(statusLbl);
            lblMiete2.setVisible(statusLbl);
            lblMiete3.setVisible(statusLbl);
            lblMiete4.setVisible(statusLbl);
            lblMieteH.setVisible(statusLbl);
            lblPreis.setVisible(statusLbl);
        }


    }

}
