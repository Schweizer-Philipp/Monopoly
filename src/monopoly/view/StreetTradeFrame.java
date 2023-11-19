package monopoly.view;

import monopoly.controller.manager.SpielerManager;
import monopoly.model.Spieler;
import monopoly.model.StreetBuyAble;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;

public class StreetTradeFrame extends JPanel implements ActionListener, ItemListener {
    private final InfoGrid infoGrid;
    private JFrame frame;
    private JButton btnAngebot;
    private JLabel lblstreet;
    private JLabel lblName;
    private JComboBox<String> jcbStreets;
    private GridLayout gl;
    private JComboBox<String> jcbSpielernamen;
    private JTextField jtfPreisA;
    private JTextField jtfPreis;
    private Spieler aSpieler;


    public StreetTradeFrame(Spieler spielerName, String strassename, String[] spielername, InfoGrid infoGrid) {
        this.infoGrid = infoGrid;
        initVars(spielerName, strassename, spielername);
        initComps();
    }

    /**
     * Initializes Variables.
     */

    private void initVars(Spieler spielerName, String strassename, String[] spielernamen) {
        aSpieler = spielerName;
        ArrayList<StreetBuyAble> strasse = null;
        int i = 0;
        for (Spieler s : SpielerManager.getSpielerarray()) {
            if (s.getSpielerName().equals(spielernamen[0])) {
                strasse = s.getStreet();
            }
        }
        String[] street = new String[strasse.size()];
        for (StreetBuyAble s1 : strasse) {
            street[i] = s1.getStrassennamen();
            i++;
        }
        frame = new JFrame("Strasseverkaufen");
        btnAngebot = new JButton("Angebot machen");
        lblstreet = new JLabel(strassename);
        jcbStreets = new JComboBox<>(street);
        gl = new GridLayout(7, 1);
        jcbSpielernamen = new JComboBox<>(spielernamen);
        jtfPreis = new JTextField("Zusaetzliches Geld");
        jtfPreisA = new JTextField("Zusaetzliches Geld");
        lblName = new JLabel(spielerName.getSpielerName());
    }

    /**
     * Initializes and set up Window Components.
     */
    private void initComps() {
        frame.setSize(600, 600);
        frame.add(this);
        frame.setLocation(Toolkit.getDefaultToolkit()
                .getScreenSize().width / 2 - frame.getWidth() / 2, Toolkit.getDefaultToolkit()
                .getScreenSize().height / 2 - frame.getHeight() / 2);
        btnAngebot.addActionListener(this);
        lblName.setFont(new Font("Monospaced", Font.PLAIN, 25));
        lblName.setHorizontalAlignment(SwingConstants.CENTER);
        lblstreet.setFont(new Font("Monospaced", Font.PLAIN, 25));
        lblstreet.setHorizontalAlignment(SwingConstants.CENTER);
        btnAngebot.setFont(new Font("Monospaced", Font.PLAIN, 25));
        jtfPreis.setFont(new Font("Monospaced", Font.PLAIN, 25));
        jtfPreisA.setFont(new Font("Monospaced", Font.PLAIN, 25));
        jcbSpielernamen.setFont(new Font("Monospaced", Font.PLAIN, 25));
        jcbStreets.setFont(new Font("Monospaced", Font.PLAIN, 25));
        jcbSpielernamen.addItemListener(this);
        setLayout(gl);
        add(lblName);
        add(lblstreet);
        add(jtfPreisA);
        add(jcbSpielernamen);
        add(jcbStreets);
        add(jtfPreis);
        add(btnAngebot);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JPasswordField pf = new JPasswordField();
        JOptionPane.showConfirmDialog(null, pf, lblName.getText() + " bitte geben Sie ihr Passwort ein", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (Arrays.equals(pf.getPassword(), aSpieler.getPasswort().toCharArray())) {
            Spieler spieler = null;
            for (Spieler s : SpielerManager.getSpielerarray()) {
                if (s.getSpielerName().equals(jcbSpielernamen.getSelectedItem())) {
                    spieler = s;
                }
            }
            JPasswordField pf1 = new JPasswordField();
            JOptionPane.showConfirmDialog(null, pf1, spieler.getSpielerName() + " bitte geben Sie ihr Passwort ein", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (Arrays.equals(pf1.getPassword(), spieler.getPasswort().toCharArray())) {
                StreetBuyAble street = null;
                for (StreetBuyAble s1 : aSpieler.getStreet()) {
                    if (s1.getStrassennamen().equals(lblstreet.getText())) {
                        street = s1;
                        aSpieler.getStreet().remove(s1);
                        break;
                    }
                }
                spieler.getStreet().add(street);
                street = null;
                for (StreetBuyAble s1 : spieler.getStreet()) {
                    if (s1.getStrassennamen().equals(jcbStreets.getSelectedItem())) {
                        street = s1;
                        spieler.getStreet().remove(s1);
                        break;
                    }
                }
                aSpieler.addStreet(street);
                int spieler1geld = Integer.valueOf(jtfPreisA.getText());
                int spieler2geld = Integer.valueOf(jtfPreis.getText());
                int spieler1zahl = spieler2geld - spieler1geld;
                int spieler2zahl = spieler1geld - spieler2geld;
                aSpieler.setGeld(aSpieler.getGeld() + spieler1zahl);
                spieler.setGeld(spieler.getGeld() + spieler2zahl);
                JOptionPane.showMessageDialog(null, "Tausch war erfolgreich");
                infoGrid.getbuttonPanel().getgameManager().setInfoGridUp(false);
                infoGrid.getbuttonPanel().getgameManager().setSpielerPanel();
                infoGrid.getbuttonPanel().getgameManager().setSpielerStrassenPanel();
                infoGrid.getbuttonPanel().getGamePanel().repaint();
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Sie haben das falsche Passwort eingegeben", "Falsches Passwort!", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Sie haben das falsche Passwort eingegeben", "Falsches Passwort!", JOptionPane.ERROR_MESSAGE);
        }

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        ArrayList<StreetBuyAble> strasse = null;
        if (e.getStateChange() == ItemEvent.SELECTED) {
            for (Spieler s : SpielerManager.getSpielerarray()) {
                if (s.getSpielerName().equals(jcbSpielernamen.getSelectedItem())) {
                    strasse = s.getStreet();
                }
            }
            jcbStreets.removeAllItems();
            for (StreetBuyAble s1 : strasse) {
                jcbStreets.addItem(s1.getStrassennamen());
            }
            revalidate();
            repaint();
        }
    }

}
