package monopoly.view;


/*
 * Modifier	Class	Package	Subclass	World
   public	  Y	     Y	      Y	        Y
   protected	Y	Y	Y	N
   no modifier	Y	Y	N	N
   private	Y	N	N	N
 */
// WORLD = 
// genau

import monopoly.controller.MenuePanelHandler;
import monopoly.controller.manager.DateiManager;
import monopoly.controller.manager.ImageManager;
import monopoly.controller.manager.SpielerManager;
import monopoly.model.Spieler;
import monopoly.view.panel.ContainerPanel;
import monopoly.view.panel.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class MenuePanel extends JPanel {


    private final ContainerPanel container;
    private final GamePanel gamePanel;
    private JButton btnSpielstarten;
    private JButton btnEinstellungen;
    private JButton btnCredits;
    private JButton btnRegeln;
    private JButton btnSpielBeenden;
    private JButton btnSpielLaden;
    private Font btnFont;
    private BufferedImage imgBackground;
    private ImageIcon icon;
    private ImageIcon iconHover;
    private GridBagLayout gr;
    private MenuePanelHandler menueHandler;
    private Color backColor;


    public MenuePanel(ContainerPanel container, GamePanel gamePanel) {


        this.gamePanel = gamePanel;

        this.container = container;

        initVars();

        initJComp();

        setVisible(true);
    }

    private void initVars() {
        imgBackground = (BufferedImage) ImageManager.loadImage("/Bilder/Startbild.png");
        iconHover = (ImageIcon) ImageManager.resizeImageOrIcon(ImageManager.loadIcon("SpielstartenHover.jpg"), 100, 100);
        icon = (ImageIcon) ImageManager.resizeImageOrIcon(ImageManager.loadIcon("Spielstarten.jpg"), 100, 100);

        gr = new GridBagLayout();

        menueHandler = new MenuePanelHandler(this/*5d099f62*/);
        // menueHandler == 5d099f62

        btnFont = new Font("Monospaced", Font.BOLD, 40);

        btnSpielLaden = new JButton("Gespeichertes Spiel starten", (ImageIcon) ImageManager.resizeImageOrIcon(ImageManager.loadIcon("Spielstarten.jpg"), 100, 100));
        btnSpielstarten = new JButton("Neues Spiel Starten", (ImageIcon) ImageManager.resizeImageOrIcon(ImageManager.loadIcon("Spielstarten.jpg"), 100, 100));
        btnEinstellungen = new JButton("Einstellungen", (ImageIcon) ImageManager.resizeImageOrIcon(ImageManager.loadIcon("Einstellungen.png"), 100, 100));
        btnCredits = new JButton("Credits", (ImageIcon) ImageManager.resizeImageOrIcon(ImageManager.loadIcon("Credits.png"), 100, 100));
        btnRegeln = new JButton("Regeln", (ImageIcon) ImageManager.resizeImageOrIcon(ImageManager.loadIcon("Regeln.png"), 100, 100));
        btnSpielBeenden = new JButton("Spiel beenden", (ImageIcon) ImageManager.resizeImageOrIcon(ImageManager.loadIcon("Spielbeenden.jpg"), 100, 100));
        backColor = new Color(187, 231, 230);

    }

    private void initJComp() {
        setLayout(gr);

        setUpButton(btnSpielLaden);
        setUpButton(btnSpielstarten);
        setUpButton(btnEinstellungen);
        setUpButton(btnCredits);
        setUpButton(btnRegeln);
        setUpButton(btnSpielBeenden);


        add(btnSpielstarten, addContraintsToButton(0));
        add(btnSpielLaden, addContraintsToButton(1));
        btnSpielLaden.setEnabled(DateiManager.getSpeichern());
        add(btnEinstellungen, addContraintsToButton(2));
        add(btnRegeln, addContraintsToButton(3));
        add(btnSpielBeenden, addContraintsToButton(4));
        add(btnCredits, addContraintsToButton(5));
        setBackground(backColor);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x = (getWidth() - imgBackground.getWidth()) / 2;
        int y = 10;

        g.drawImage(imgBackground, x, y, null);
    }

    /**
     * Methode die einem Button Eigenschaften wie {@code Font},
     * {@code Border}, {@code Position} setzt.
     *
     * @param button Der Button der veraendert wird.
     */
    private void setUpButton(JButton button) {

        button.setFocusPainted(false);
        button.addMouseListener(menueHandler);
        button.addActionListener(menueHandler);
        button.setPreferredSize(new Dimension(400, 200));
        button.setForeground(Color.BLACK);
        button.setFont(btnFont);
        button.setHorizontalTextPosition(SwingConstants.LEFT);
    }


    /**
     * Fuegt einen Button in die mitgeliefertet Zeile und fuegt ein Top-Margin von
     * 40px hinzu.
     *
     * @param zeile Die Zeile in der die Komponente steht
     * @return c Eigenschaften des Layouts
     */
    private GridBagConstraints addContraintsToButton(int zeile) {

        GridBagConstraints c = new GridBagConstraints();


        int insetsTop = (zeile > 0) ? 30 : imgBackground.getHeight() + 20;
        repaint();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = zeile;
        c.insets = new Insets(insetsTop, 0, 0, 0);

        return c;

    }

    public void createHover(JButton btn, String status) {
        switch (status) {
            case "entered":
                if (btn.equals(btnSpielstarten)) {
                    btn.setIcon(iconHover);
                }
                btn.setForeground(Color.BLUE);
                //btn.setBorder(BorderFactory.createLineBorder(Color.BLUE));
                btn.repaint();

                break;

            case "exit":
                if (btn.equals(btnSpielstarten)) {
                    btn.setIcon(icon);
                }
                btn.setForeground(Color.BLACK);
                //btn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                btn.repaint();

                break;

            default:
                break;

        }
    }


    public void pressedButton(JButton btn) {
        if (btn.equals(btnSpielBeenden)) {
            System.exit(0);
        } else if (btn.equals(btnCredits)) {
            JOptionPane.showMessageDialog(this, "Herzlich Willkommen bei den Credits\nDies ist ein Masterwerk geschaffen von : \n"
                    + "Leitende Programmierer: Julian Singer und Philipp Schweizer\nProgrammier unterstuetzer: Robert Hahn\nBubblesort "
                    + "ForSchleifen Experte : Yannic Engler" + "\nWir setzten Massstaebe !");
        } else if (btn.equals(btnEinstellungen)) {
            container.switchPanel("Einstellungspanel");
        } else if (btn.equals(btnRegeln)) {
            container.switchPanel("RegelPanel");
        } else if (btn.equals(btnSpielstarten)) {
            int anzahlSpieler = DateiManager.zeilenLesen("Einstellungen.txt") - 1;
            if (anzahlSpieler < 0) {
                JOptionPane.showMessageDialog(this, "Bitte nehmen SIe zuerst die Einstellungen vor! Danke");
            } else {
                int a;
                if (DateiManager.getSpeichern()) {
                    a = JOptionPane.showConfirmDialog(this, "Es gibt bereits einen Spielstand sind sie sicher das den loeschen wollen?");
                    if (a == 0) {
                        gamePanel.getgameGrid().erstelleSpieler(true);
                        DateiManager.emptySpeichern();
                        DateiManager.clearstreet();
                        DateiManager.mischeErgeingniskarten();
                        DateiManager.mischeGemeinschaftskarten();
                        gamePanel.spielstarten(false);
                        for (Spieler s : SpielerManager.getSpielerarray()) {
                            JPasswordField pf = new JPasswordField();
                            int okCxl = JOptionPane.showConfirmDialog(null, pf, s.getSpielerName() + " bitte geben Sie ihr Passwort ein", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                            while (okCxl != JOptionPane.OK_OPTION || Arrays.equals(pf.getPassword(), "".toCharArray()) || Arrays.toString(pf.getPassword()).contains("@")) {
                                okCxl = JOptionPane.showConfirmDialog(null, pf, s.getSpielerName() + " bitte geben Sie ihr Passwort ein (keine @) ", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                            }
                            s.setPasswort(((JTextField) pf).getText());
                        }
                        container.switchPanel("Gamepanel");
                    }
                } else {
                    gamePanel.getgameGrid().erstelleSpieler(true);
                    DateiManager.mischeErgeingniskarten();
                    DateiManager.mischeGemeinschaftskarten();
                    for (Spieler s : SpielerManager.getSpielerarray()) {
                        JPasswordField pf = new JPasswordField();
                        int okCxl = JOptionPane.showConfirmDialog(null, pf, s.getSpielerName() + " bitte geben Sie ihr Passwort ein", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                        while (okCxl != JOptionPane.OK_OPTION || Arrays.equals(pf.getPassword(), "".toCharArray()) || Arrays.toString(pf.getPassword()).contains("@")) {
                            okCxl = JOptionPane.showConfirmDialog(null, pf, s.getSpielerName() + " bitte geben Sie ihr Passwort ein (keine @) ", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                        }
                        s.setPasswort(((JTextField) pf).getText());
                    }
                    gamePanel.spielstarten(false);
                    container.switchPanel("Gamepanel");
                }
            }
        } else if (btn.equals(btnSpielLaden)) {
            gamePanel.getgameGrid().erstelleSpieler(false);
            gamePanel.spielstarten(true);
            container.switchPanel("Gamepanel");

        }

    }

    public Color getBackColor() {
        return backColor;
    }

    public Icon getIcon() {
        return icon;
    }
}
