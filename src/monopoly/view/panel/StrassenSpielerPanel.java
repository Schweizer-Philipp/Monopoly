package monopoly.view.panel;

import monopoly.controller.manager.GameManager;
import monopoly.controller.manager.SpielerManager;
import monopoly.model.Spieler;
import monopoly.model.StreetBuyAble;
import monopoly.view.InfoGrid;
import monopoly.view.StreetTradeFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StrassenSpielerPanel extends JPanel implements ActionListener {
    private final InfoGrid infoGrid;
    private GridLayout gL;
    private int spielerID;

    public StrassenSpielerPanel(InfoGrid infoGrid) {
        this.infoGrid = infoGrid;
        initVars();
        spielerID(1);
    }

    private void initVars() {
        gL = new GridLayout();
    }


    public void spielerID(int spielerID) {
        this.spielerID = spielerID - 1;
        removeAll();
        initComps();
    }

    /**
     * Initializes and set up Window Components.
     */
    private void initComps() {
        setOpaque(false);
        setLayout(gL);

        if (!SpielerManager.getSpielerarray()[spielerID].getStreet().isEmpty()) {
            int colums = SpielerManager.getSpielerarray()[spielerID].getStreet().size() / 11 + 1;
            int rows;
            if (colums == 1) {
                rows = SpielerManager.getSpielerarray()[spielerID].getStreet().size() / colums;
            } else if (SpielerManager.getSpielerarray()[spielerID].getStreet().size() / colums % 2 == 0) {
                rows = SpielerManager.getSpielerarray()[spielerID].getStreet().size() / colums;
            } else {
                rows = SpielerManager.getSpielerarray()[spielerID].getStreet().size() / colums + 1;
            }

            gL.setRows(rows);
            gL.setColumns(colums);
            gL.setHgap(5);
            gL.setVgap(5);

            StreetBuyAble[] streett = new StreetBuyAble[SpielerManager.getSpielerarray()[spielerID].getStreet().size()];
            SpielerManager.getSpielerarray()[spielerID].getStreet().toArray(streett);
            StreetBuyAble street;


            for (int i = 0; i < streett.length; i++) {
                for (int x = 0; x < streett.length - 1; x++) {
                    if (streett[x].getId() > streett[x + 1].getId()) {
                        street = streett[x];
                        streett[x] = streett[x + 1];
                        streett[x + 1] = street;
                    }
                }
            }
            SpielerManager.getSpielerarray()[spielerID].changelist(streett);
            for (StreetBuyAble s : SpielerManager.getSpielerarray()[spielerID].getStreet()) {
                JButton btn = new JButton();
                btn.setOpaque(true);
                btn.setBackground(s.getFarbe());
                btn.setText(s.getStrassennamen());
                btn.setFont(new Font("Monospaced", Font.PLAIN, 25));
                btn.setHorizontalAlignment(SwingConstants.CENTER);
                btn.setActionCommand(s.getStrassennamen());
                btn.setFocusable(false);
                btn.addActionListener(this);
                add(btn);
            }
            revalidate();
            repaint();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int zaehler = 0;
        String[] spielerposible = new String[SpielerManager.getSpielerarray().length - 1];
        for (Spieler s : SpielerManager.getSpielerarray()) {
            if (s.getSpielerID() != GameManager.getakt()) {
                spielerposible[zaehler] = s.getSpielerName();
                zaehler++;
            }


        }

        new StreetTradeFrame(SpielerManager.getSpielerarray()[GameManager.getakt() - 1], e.getActionCommand(), spielerposible, infoGrid);


    }
}
