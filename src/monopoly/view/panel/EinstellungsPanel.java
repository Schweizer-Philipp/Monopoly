package monopoly.view.panel;


import monopoly.controller.EinstellungsPanelHandler;
import monopoly.controller.manager.DateiManager;
import monopoly.view.MenuePanel;
import monopoly.view.MonopolyFrame;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;

public class EinstellungsPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private final MenuePanel menuePanel;
    private final MonopolyFrame monopolyFrame;
    private final ContainerPanel panelContainer;
    private final String[] jbcAuswahl = {"2", "3", "4"}; // Laenge  = N Elemente 0 ... (n-1)
    private final JComponent[][] spielerKomponenten = new JComponent[4][2];
    private final ArrayList<String> data;
    private JLabel lblWuerfelnf;
    private JLabel lblSpielerf;
    private GridBagLayout gblEinstellungsPanel;
    private GridLayout glSpielernamen;
    private GridBagLayout gblGenerelleEinstellungen;
    private Font lblFont;
    private EinstellungsPanelHandler einstellungsHandler;
    private JButton btnSpeichern;
    private JButton btnAbbrechen;
    private JTextField jtfTasteWuerfeln;
    private JComboBox<String> jcbSpielerAuswahl;
    private JPanel panelSpielerKomponeten;
    private JPanel panelGenerelleEinstellungen;
    private JPanel panelButton;


    // btn = button
    // lbl = label
    // jtf = JTextFIeld
    // jta  = JTextARea
    // jcb  = JcomboBox
    // f   = JFrame
    // p    = Jpanel
    // tbl  = JTabel
    // imgV = ImageView


    public EinstellungsPanel(MonopolyFrame monopolyFrame, MenuePanel menuePanel, ContainerPanel panelContainer) {
        this.menuePanel = menuePanel;
        this.monopolyFrame = monopolyFrame;
        this.panelContainer = panelContainer;

        data = DateiManager.dateiLesen("Einstellungen.txt");

        initVars();
        initJcomp();
        int rows = (data.size() - 1 > 0) ? data.size() - 1 : 4;
        spielerAnzahlVariable(rows, true);
        ladeEinstellungen(data);
    }


    private void initVars() {
        panelButton = new JPanel();
        glSpielernamen = new GridLayout(4, 2, 500, 100);

        einstellungsHandler = new EinstellungsPanelHandler(this, menuePanel, panelContainer);
        panelSpielerKomponeten = new JPanel();
        panelGenerelleEinstellungen = new JPanel();

        lblWuerfelnf = new JLabel("Auf welcher Taste wollen Sie wuerfeln:");
        lblSpielerf = new JLabel("Zu wie vielt wollen Sie spielen?");

        lblFont = new Font("Monospaced", Font.BOLD, 29);

        gblEinstellungsPanel = new GridBagLayout();
        gblGenerelleEinstellungen = new GridBagLayout();


        btnSpeichern = new JButton("Speichern");
        btnAbbrechen = new JButton("Abbrechen");

        jcbSpielerAuswahl = new JComboBox<String>(jbcAuswahl);


        jtfTasteWuerfeln = new JTextField(4);
        jtfTasteWuerfeln.addKeyListener(einstellungsHandler);
    }


    public void spielerAnzahlVariable(int rows, boolean first) {
        ArrayList<String> namen = new ArrayList<String>();

        // Speichern der Inhalte der TextFelder
        for (int i = 0; i < glSpielernamen.getRows(); i = i + 1) {
            JTextField textField = (JTextField) spielerKomponenten[i][1];
            if (textField != null) {
                String spielerName = textField.getText();
                namen.add(spielerName);
            }
        }


        // loeschen Panel
        panelSpielerKomponeten.removeAll();
        // Zeilen werden geupdated
        glSpielernamen.setRows(rows);

        // durchlaufen jeder neuen Zeile
        for (int i = 0; i < rows; i += 1) {
            // Initialiseren des Labels fuer Zeile i.
            spielerKomponenten[i][0] = new JLabel("Spielername " + (i + 1));
            spielerKomponenten[i][0].setFont(lblFont);

            // Initialisieren des TextFeldes fuer Zeile i
            spielerKomponenten[i][1] = new JTextField(4);
            JTextField jtf = (JTextField) spielerKomponenten[i][1];

            // Hinzufuegen des alten Wertes (falls vorhanden)
            if (i < namen.size()) jtf.setText(namen.get(i));

            // Set-Up
            jtf.setFont(lblFont);
            jtf.setHorizontalAlignment(SwingConstants.CENTER);
            jtf.addKeyListener(einstellungsHandler);

            // Hinzufuegen des Labels / TextFeldes zum JPanel
            panelSpielerKomponeten.add(spielerKomponenten[i][0]);
            panelSpielerKomponeten.add(jtf);
            // Updaten des Panel
            panelSpielerKomponeten.revalidate();

            if (!first) // !first
                // Updaten des kompletten Fensters.
                monopolyFrame.getJframe().revalidate();
        }
    }


    private void initJcomp() {
        setLayout(gblEinstellungsPanel);

        setBackground(menuePanel.getBackColor());

        int index = (data.size() - 1 > 0) ? data.size() - 1 : 4;
        jcbSpielerAuswahl.setSelectedItem(String.valueOf(index));

        jcbSpielerAuswahl.setPreferredSize(new Dimension(100, 75));
        ((JLabel) jcbSpielerAuswahl.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        jcbSpielerAuswahl.addItemListener(einstellungsHandler);
        jcbSpielerAuswahl.setFont(lblFont);

        erstelleRahmenVonPanel("Generelle Einstellungen", gblGenerelleEinstellungen, panelGenerelleEinstellungen);
        erstelleRahmenVonPanel("Spieler Namen", glSpielernamen, panelSpielerKomponeten);

        lblSpielerf.setFont(lblFont);
        lblWuerfelnf.setFont(lblFont);
        jtfTasteWuerfeln.setFont(lblFont);
        jtfTasteWuerfeln.setHorizontalAlignment(SwingConstants.CENTER);


        btnSpeichern.setFont(lblFont);
        btnSpeichern.addActionListener(einstellungsHandler);
        btnSpeichern.setActionCommand("Speichern");
        btnAbbrechen.setFont(lblFont);
        btnAbbrechen.addActionListener(einstellungsHandler);
        btnAbbrechen.setActionCommand("Abbrechen");

        addComponent(lblWuerfelnf, 0, 0, 1, 50, 0.5, panelGenerelleEinstellungen);
        addComponent(jtfTasteWuerfeln, 0, 1, 1, -1, 0.5, panelGenerelleEinstellungen);
        addComponent(lblSpielerf, 1, 0, 1, 50, 0.5, panelGenerelleEinstellungen);
        addComponent(jcbSpielerAuswahl, 1, 1, 1, -1, 0.5, panelGenerelleEinstellungen);
        addComponent(panelSpielerKomponeten, 2, 0, 3, -1, 1, this);

        addComponent(panelGenerelleEinstellungen, 0, 0, 2, -1, 1, this);

        panelButton.setBackground(menuePanel.getBackColor());
        panelButton.setLayout(new GridLayout(1, 2));
        panelButton.add(btnAbbrechen);
        panelButton.add(btnSpeichern);
        addComponent(panelButton, 3, 1, 3, 50, 1, this);


    }


    private void erstelleRahmenVonPanel(String name, Object layout, JPanel panel) {
        panel.setLayout((LayoutManager) layout);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        null,
                        name,
                        TitledBorder.LEADING,
                        TitledBorder.TOP,
                        lblFont,
                        Color.blue),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        panel.setBackground(menuePanel.getBackColor());

    }

    private void addComponent(JComponent c, int row, int col, int w, int h, double weightCol, JPanel i) {
        GridBagConstraints gbc = new GridBagConstraints();

        if (h == -1) gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridy = row;
        gbc.gridx = col;
        gbc.gridwidth = w;
        gbc.weightx = weightCol;
        gbc.weighty = weightCol;

        if (col == 1)
            gbc.anchor = GridBagConstraints.EAST;
        else
            gbc.anchor = GridBagConstraints.WEST;

        i.add(c, gbc);
    }

    /**
     * Methode um die ArrayList mit den zuspeichernden Daten zu erstellen.
     *
     * @return daten Die {@code ArrayList<String>} mit den Daten oder {@code null}
     */
    public ArrayList<String> getDatenZumSpeichern() {
        ArrayList<String> daten = new ArrayList<String>();
        einstellungsHandler.setPruefziffer(0);

        // Wenn das Feld leer ist dann soll er null zurueckgeben.
        if (jtfTasteWuerfeln == null || jtfTasteWuerfeln.getText().equals("")) {
            return null;
        }

        // Wert aus TextFeld holen --> dazugehoerigen Key finden --> in Daten adden
        daten.add(einstellungsHandler.getKeyFromValue(jtfTasteWuerfeln.getText()));

        // Wie viele Spielerdatensaetze soll er schreiben
        int anzahl = Integer.parseInt((String) jcbSpielerAuswahl.getSelectedItem());

        for (int i = 0; i < anzahl; i++) {
            // <anzahl> an Datensaetzen mit dem jeweiligen Inhalt in die ArrayList schreiben
            JTextField jtf = (JTextField) spielerKomponenten[i][1];
            if (jtf == null || jtf.getText().equals("")) {
                return null;
            }

            if (daten.contains(jtf.getText())) {
                einstellungsHandler.setPruefziffer(1);
            }

            daten.add(jtf.getText());
        }

        return daten;
    }

    private void ladeEinstellungen(ArrayList<String> data) {

        if (data == null) {
            JOptionPane.showMessageDialog(this,
                    "Beim Auslesen Ihrer Einstellungen ist ein Fehler aufgetreten"
                            + "Starten Sie das Programm neu. "
                            + "Sollte der fehler erneut auftreten wenden Sie sich an den"
                            + " Admin Philipp.schw@directbox.com oder Alessio");
        } else if (data.size() > 0) {
            for (int i = 0; i < data.size(); i++) {
                if (i == 0) {
                    int keycode = Integer.parseInt(data.get(i));
                    String taste = einstellungsHandler.getKeyList().get(keycode);
                    jtfTasteWuerfeln.setText(taste);
                } else {
                    ((JTextField) spielerKomponenten[i - 1][1]).setText(data.get(i));
                }
            }

        }
    }

    public JTextField getJtfWuerfel() {
        return this.jtfTasteWuerfeln;
    }
}
