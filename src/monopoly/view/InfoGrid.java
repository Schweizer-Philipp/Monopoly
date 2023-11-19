package monopoly.view;

import monopoly.controller.manager.DateiManager;
import monopoly.controller.manager.ImageManager;
import monopoly.controller.manager.StreetManager;
import monopoly.model.RoundedPanel;
import monopoly.model.Strassentyp;
import monopoly.model.Street;
import monopoly.view.panel.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class InfoGrid extends RoundedPanel {

    private static String aktpanel;
    private final GamePanel gamePanel;
    private final GameGrid gameGrid;
    private GridLayout gLayout;
    private CardLayout cLayout;
    private GridLayout gridLayout;
    private JPanel panelUebersichtKarten;
    private JPanel panelUebersichtSpieler;
    private StrassenPanel strassenPanel;
    private WerkePanel werkePanel;
    private ButtonPanel buttonPanel;
    private BanhofPanel bahnhofPanel;
    private EckFelderPanel eckFelderPanel;
    private GemeinschaftsFeldPanel gPanel;
    private EreignisFeldPanel ePanel;
    private JPanel panelwechsel;
    private SpielerPanel spielerPanel;
    private StrassenSpielerPanel strassenSpielerPanel;
    private SteuernPanel steuernPanel;
    private JTabbedPane tab;

    public InfoGrid(GamePanel gamePanel, GameGrid gameGrid, boolean neuesSpiel) {
        this.gameGrid = gameGrid;
        this.gamePanel = gamePanel;
        initVars(neuesSpiel);
        initJcomp();
        if (neuesSpiel) {
            setaltesSpiel();
        }
    }

    public static String getaktpanel() {
        return aktpanel;
    }

    private void initVars(boolean neuesspiel) {

        tab = new JTabbedPane();

        spielerPanel = new SpielerPanel();
        strassenSpielerPanel = new StrassenSpielerPanel(this);

        strassenPanel = new StrassenPanel();
        werkePanel = new WerkePanel();

        steuernPanel = new SteuernPanel();
        panelwechsel = new JPanel();
        bahnhofPanel = new BanhofPanel();
        eckFelderPanel = new EckFelderPanel();
        gPanel = new GemeinschaftsFeldPanel();
        ePanel = new EreignisFeldPanel();
        buttonPanel = new ButtonPanel(neuesspiel, gameGrid, this, spielerPanel, strassenSpielerPanel, strassenPanel, werkePanel, bahnhofPanel, eckFelderPanel, gPanel, ePanel, steuernPanel, gamePanel);

        gLayout = new GridLayout(2, 1, 10, 10);
        cLayout = new CardLayout();
        gridLayout = new GridLayout(1, 2);


        panelUebersichtKarten = new RoundedPanel();
        panelUebersichtSpieler = new RoundedPanel();
    }

    private void initJcomp() {

        setBackground(Color.BLACK);
        setLayout(gLayout);
        panelwechsel.setOpaque(false);
        panelUebersichtSpieler.setLayout(new BorderLayout());


        tab.setFont(new Font("Arial", Font.PLAIN, 20));

        tab.addTab("Spieler", (Icon) ImageManager.resizeImageOrIcon(ImageManager.loadIcon("Spielstarten.jpg"), 50, 50), spielerPanel, "SpielerUebersicht");
        tab.addTab("Strassen", (Icon) ImageManager.resizeImageOrIcon(ImageManager.loadIcon("Strasse.jpg"), 50, 50), strassenSpielerPanel, "StrassenUebersicht");
        tab.setTabPlacement(JTabbedPane.TOP);
        tab.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);


        panelUebersichtKarten.setLayout(gridLayout);
        panelwechsel.setLayout(cLayout);
        panelUebersichtKarten.setBackground(new Color(187, 231, 230));
        panelUebersichtSpieler.setBackground(new Color(187, 231, 230));

        panelUebersichtKarten.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(15, 15, 15, 15),
                BorderFactory.createTitledBorder(
                        null, "Strassenuebersicht", TitledBorder.LEADING, TitledBorder.TOP,
                        new Font("Arial", Font.PLAIN, 25), new Color(0, 0, 0))
        ));

        panelUebersichtSpieler.setBorder(BorderFactory.createCompoundBorder(

                BorderFactory.createEmptyBorder(15, 15, 15, 15),
                BorderFactory.createTitledBorder(
                        null, "Spieleruebersicht", TitledBorder.LEADING, TitledBorder.TOP,
                        new Font("Arial", Font.PLAIN, 25), new Color(0, 0, 0))
        ));
        panelwechsel.add(ePanel, "EPanel");
        panelwechsel.add(gPanel, "GPanel");
        panelwechsel.add(eckFelderPanel, "EckfelderPanel");
        panelwechsel.add(bahnhofPanel, "BahnhofPanel");
        panelwechsel.add(werkePanel, "WerkePanel");
        panelwechsel.add(strassenPanel, "StrassenPanel");
        panelwechsel.add(steuernPanel, "SteuernPanel");
        panelUebersichtKarten.add(panelwechsel);
        panelUebersichtKarten.add(buttonPanel);
        panelUebersichtSpieler.add(tab, BorderLayout.CENTER);
        add(panelUebersichtKarten);
        add(panelUebersichtSpieler);
        cLayout.show(panelwechsel, "EckfelderPanel");


    }

    private void setaltesSpiel() {
        switchPanel(DateiManager.getEinstellungen(9));
        Strassentyp typ = null;
        for (Street s : StreetManager.getSpielfeld()) {

            if (s.getId() == Integer.valueOf(DateiManager.getEinstellungen(8))) {
                typ = s.getTyp();

            }
        }
        if (typ.equals(Strassentyp.STRAssE)) {
            strassenPanel.aktuelleStrasseermitteln((DateiManager.getEinstellungen(8)));
        } else if (typ.equals(Strassentyp.BAHNHOF)) {
            bahnhofPanel.initComps(DateiManager.getEinstellungen(8));

        } else if (typ.equals(Strassentyp.EREIGNISFELD)) {
            ePanel.setUpEreignisfeld(DateiManager.getEinstellungen(2), false, 0, null, null);

        } else if (typ.equals(Strassentyp.GEFAeNGNIS) || typ.equals(Strassentyp.LOS)
                || typ.equals(Strassentyp.PARKEN) || typ.equals(Strassentyp.GEHE_INS_GEFAeNGNIS)) {
            eckFelderPanel.setUpEckfelder(DateiManager.getEinstellungen(8));
        } else if (typ.equals(Strassentyp.GEMEINSCHAFTSFELD)) {
            gPanel.setUpGemeinschaftsfeld(DateiManager.getEinstellungen(2), false, 0, null);
        } else if (typ.equals(Strassentyp.WERK)) {
            werkePanel.initComps(DateiManager.getEinstellungen(8));
        } else if (typ.equals(Strassentyp.ZUSATZSTEUER) || typ.equals(Strassentyp.EINKOMENSTEUER)) {
            steuernPanel.setUpPanel(DateiManager.getEinstellungen(8));
        }
        GameGrid.setfrei(DateiManager.getEinstellungen(4));
        spielerPanel.setSpielerID(Integer.valueOf(DateiManager.getEinstellungen(3)));
        strassenSpielerPanel.spielerID(Integer.valueOf(DateiManager.getEinstellungen(3)));

    }

    public void switchPanel(String panel) {
        cLayout.show(panelwechsel, panel);
        aktpanel = panel;

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(795, 1040);
    }

    public ButtonPanel getbuttonPanel() {
        return buttonPanel;
    }


}
