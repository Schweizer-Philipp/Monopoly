package monopoly.controller;


import monopoly.controller.manager.DateiManager;
import monopoly.controller.manager.ImageManager;
import monopoly.view.MenuePanel;
import monopoly.view.panel.ContainerPanel;
import monopoly.view.panel.EinstellungsPanel;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * <h1> EinstellungsPanelHandler.java</h1>
 * <h2>
 * Die Klasse managed Key-, Click - und ComboxBox-Events
 * der EinstellungsPanel.java.
 * </h2>
 * <p> Durch das implementieren von den folgendenen Klassen:</p>
 * <ul>
 * <li>@see ItemListener.java </li>
 * <li>@see ActionListener.java </li>
 * <li>@see KeyListener.java </li>
 * </ul>
 * <p>werden die Key-, Click- und Itemevents von der EinstellungsPanel.java
 * abgefangen und verarbeitet.
 * Siehe auch:</p>
 * <ul>
 * <li>{@link MenuePanel}</li>
 * <li>{@link EinstellungsPanel}</li>
 * <li>{@link ContainerPanel}</li>
 * </ul>
 *
 * @author Philipp Schweizer
 */
public class EinstellungsPanelHandler implements ItemListener, ActionListener, KeyListener {

    private final EinstellungsPanel einstellungsPanel;
    private final ContainerPanel panelContainer;


    private final Map<Integer, String> keyMap = new HashMap<Integer, String>();

    private final Icon iconDialog;
    private final Icon iconFehlerDialog;
    private int pruefziffer;


    public EinstellungsPanelHandler(EinstellungsPanel einstellungsPanel, MenuePanel menuepanel, ContainerPanel panelContainer) {
        this.einstellungsPanel = einstellungsPanel;
        this.panelContainer = panelContainer;

        setUpKeyMap();

        iconDialog = (ImageIcon) ImageManager.resizeImageOrIcon(ImageManager.loadIcon("Spielstarten.jpg"), 100, 100);
        iconFehlerDialog = (ImageIcon) ImageManager.resizeImageOrIcon(ImageManager.loadIcon("Spielbeenden.jpg"), 100, 100);

        pruefziffer = 0;
    }


    private void setUpKeyMap() {
        //Pfeiltasten
        keyMap.put(37, "Pfeiltaste Links");
        keyMap.put(38, "Pfeiltaste Oben");
        keyMap.put(39, "Pfeiltast Rechts");
        keyMap.put(40, "Pfeiltaste Unten");

        //Zahlen
        keyMap.put(48, "0");
        keyMap.put(49, "1");
        keyMap.put(50, "2");
        keyMap.put(51, "3");
        keyMap.put(52, "4");
        keyMap.put(53, "5");
        keyMap.put(54, "6");
        keyMap.put(55, "7");
        keyMap.put(56, "8");
        keyMap.put(57, "9");

        //Buchstaben
        keyMap.put(65, "A");
        keyMap.put(66, "B");
        keyMap.put(67, "C");
        keyMap.put(68, "D");
        keyMap.put(69, "E");
        keyMap.put(70, "F");
        keyMap.put(71, "G");
        keyMap.put(72, "H");
        keyMap.put(73, "I");
        keyMap.put(74, "J");
        keyMap.put(75, "K");
        keyMap.put(76, "L");
        keyMap.put(77, "M");
        keyMap.put(78, "N");
        keyMap.put(79, "O");
        keyMap.put(80, "P");
        keyMap.put(81, "Q");
        keyMap.put(82, "R");
        keyMap.put(83, "S");
        keyMap.put(84, "T");
        keyMap.put(85, "U");
        keyMap.put(86, "V");
        keyMap.put(87, "W");
        keyMap.put(88, "X");
        keyMap.put(89, "Y");
        keyMap.put(90, "Z");

    }

    /**
     * Wird ausgefuehrt wenn ein Button ein Click-Event ausloest
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String[] auswahlJOptionDialog = {"Ja, Fortfahren", "Nein, Abbrechen"};
        int userAuswahl = 0;

        /*
         * Abbrechen-Button
         */
        switch (e.getActionCommand()) {
            case "Abbrechen":

                // Dialog aufrufen und Button-Wert speichern
                userAuswahl = showDialog("Wollen Sie wirklich abbrechen-> Alle Einstellungen gehen verloren",
                        "Verlassen der Einstellungen", auswahlJOptionDialog, JOptionPane.YES_NO_OPTION, iconDialog);

                // Wenn Ja gedrueckt wurde
                if (userAuswahl == JOptionPane.YES_OPTION) {
                    // Gehe zurueck zum Menue
                    panelContainer.switchPanel("MenuePanel");
                }

                break;

            /*
             * Speicher-Button
             */
            case "Speichern":

                // Dialog aufrufen und Button-Wert speichern
                userAuswahl = showDialog("Wollen die Einstellungen speichern und zum Menue zurueckkehrern",
                        "Speichern der Einstellungen", auswahlJOptionDialog, JOptionPane.YES_NO_OPTION, iconDialog);

                // Name des Buttons
                String[] button = {"ok"};

                // Wenn ja gedrueckt wurde
                if (userAuswahl == JOptionPane.YES_OPTION) {
                    // ArrayList mit den zuspeichernden Daten.
                    ArrayList<String> daten = einstellungsPanel.getDatenZumSpeichern();
                    // Wenn es Daten zum speichern gibt / alle Felder ausgefuellt sind
                    if (daten != null) {
                        if (pruefziffer == 0) {
                            // Wenn das Speichern erfolgreich war.
                            if (DateiManager.einstellungenSpeichern(daten)) {
                                showDialog("Einstellungen wurden erfolgreich gespeichert", null, button, JOptionPane.OK_OPTION, iconDialog);
                                panelContainer.switchPanel("MenuePanel");

                            } else {
                                // Wenn ein Fehler aufgetreten ist, dann zeige einen Fehler-Dialog.
                                showDialog("Fehler beim Speichern, bitte erneut versuchen oder sich an den Admin(oder Alessio) wenden Philipp.schw@directbox.com",
                                        "Fehler beim Speichern", button, JOptionPane.OK_OPTION, iconFehlerDialog);
                            }
                        } else {
                            showDialog("Einer ihrer Spielernamen kommt doppelt vor, bitte aendern sie das",
                                    "FehlerMedlung", button, JOptionPane.OK_OPTION, iconFehlerDialog);

                        }
                    } else {
                        // Wenn daten null war wurden nicht alle TextFelder ausgefuellt.
                        showDialog("Sie haben nicht alle Textfelder ausgefuellt", "Fehler",
                                button, JOptionPane.OK_OPTION, iconFehlerDialog);
                    }
                }
                pruefziffer = 0;
                break;
        }
    }

    /**
     * Getter fuer die HashMap mit den KeyCode.
     *
     * @return Instanz der keyMap
     */
    public Map<Integer, String> getKeyList() {
        return keyMap;
    }

    /**
     * Methode um ein Key von einem Value zu bekommen.
     *
     * @param value zupruefende Value
     * @return key    den key des Wertes (Value), -1 bei keinem Treffer
     */
    public String getKeyFromValue(String value) {
        // Map.Entry --> 1 Eintrag unserer HashMap (key + Value)
        // keyMap.entrySet() --> Liste mit allen Eintraegen
        for (Map.Entry<Integer, String> eintrag : keyMap.entrySet()) {
            if (eintrag.getValue().equals(value))
                return String.valueOf(eintrag.getKey());
        }
        // Falls es diesen Key nicht gibt, soll -1 zurueck gegeben werden
        return "-1";
    }

    /**
     * Methode um ein JOptionDialog mit verschiedenen Parametern zu erstellen.
     *
     * @param message     Die Nachricht des Dialogs
     * @param title       Der Titel des Dialogs
     * @param buttonNamen Die Namen der Buttons die der Dialog hat
     * @param typ         Der Typ des Dialogs. {@see JOptionPane}
     * @param icon        Das Icon des Dialogs
     * @return Den int-Wert des gedrueckten Buttons {@see JOptionPane}
     */
    private int showDialog(String message, String title, String[] buttonNamen, int typ, Icon icon) {
        return JOptionPane.showOptionDialog(
                einstellungsPanel,   // Parent
                message,             // Nachricht
                title,               // Title
                typ,                // Typ des Dialogs
                JOptionPane.WARNING_MESSAGE, // Message-Typ des Dialogs
                icon,    // Icon
                buttonNamen,   // Namen der Buttons
                0);  // Welcher Button am Anfang ausgewaehlt werden soll.
    }


    /**
     * Wird bei einer Veraenderung in der JComboBox aufgerufen.
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() instanceof JComboBox) {
            @SuppressWarnings("unchecked")
            JComboBox<String> comboBox = (JComboBox<String>) e.getSource();

            // Veraenderung des Layouts je nach selektiertem ComboBox Eintrag
            if (e.getStateChange() == ItemEvent.SELECTED) {
                einstellungsPanel.spielerAnzahlVariable(Integer.parseInt((String) comboBox.getSelectedItem()), false);
            }
        }

    }

    /**
     * Wird ausgefuehrt wenn eine taste getipped wird
     */
    @Override
    public void keyTyped(KeyEvent e) {    // schaut ob die quelle das getJtfWuerfel ist
        if (e.getSource() == einstellungsPanel.getJtfWuerfel()) {
            int keyCode = e.getKeyCode();
            String taste = keyMap.get(keyCode);
            //wenn der code kein wert hat wird die taste gegessen :D hab Hunger
            if (taste == null) {
                e.consume();
            }
        }
        //wenn Quelle getJtf ist dann max=0 else max=10
        int max = (e.getSource() == einstellungsPanel.getJtfWuerfel()) ? 0 : 10;
        // frisst die tasten ab einer anzahl
        JTextField f = (JTextField) e.getSource();
        if (f.getText().length() > max)
            e.consume();
    }


    /**
     * Wird ausgefuehrt wenn eine taste gedrueckt wird
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // schaut ob die quelle das getJtfWuerfel ist
        if (e.getSource() == einstellungsPanel.getJtfWuerfel()) {
            //schaut ob die shift taste gedrueckt wird
            // es wird verhindert, dass der User Eingaben mit SHIFT macht.
            if (!e.isShiftDown()) {
                int keyCode = e.getKeyCode();
                //zuweisung der taste ueber Keymap
                String taste = keyMap.get(keyCode);
                //wenn taste nicht gleich null wird der text der Jtf auf den wert der variable taste gelegt
                if (taste != null) {
                    einstellungsPanel.getJtfWuerfel().setText(taste);
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void setPruefziffer(int pruefziffer) {
        this.pruefziffer = pruefziffer;
    }

}
