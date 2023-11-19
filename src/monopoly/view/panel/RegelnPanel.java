package monopoly.view;


import monopoly.controller.manager.ImageManager;
import monopoly.view.panel.ContainerPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class RegelnPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private final ContainerPanel containerPanel;
    private JLabel lblRegel;
    private GridLayout gl;
    private JButton btnZurueck;
    private JTextArea scpEndRegeln;

    public RegelnPanel(ContainerPanel containerPanel) {
        this.containerPanel = containerPanel;
        initVars();
        initComps();
    }


    public void initVars() {
        gl = new GridLayout(3, 1);
        lblRegel = new JLabel("Hier befinden sich die Regeln");
        btnZurueck = new JButton("Zurueck");
        scpEndRegeln = new JTextArea();
    }


    public void initComps() {
        setLayout(gl);
        setBackground(new Color(187, 231, 230));
        lblRegel.setFont(new Font("Monospaced", Font.BOLD, 30));
        scpEndRegeln.setText("1. Jeder Spieler erhaelt 1500EUR Startkapital \n2. Wenn ein Spieler ueber das Feld Los kommt erhaehlt der jeweilige Spieler 200EUR \n"
                + "3. Haeuser duerfen nur gebaut werden auf dem Feld wo der Spieler steht und auch nur wenn er alle Strassen einer Farbe hat\n"
                + "4. Drei malige Pasch wiederholung fuehrt zum Gefaengis\n"
                + "5. Miete wird NICHT verdoppelt wenn man alle Strassen einer Farbe hat\n"
                + "6. Man kann keine Strassen Tauschen oder Verkaufen auch nicht eine Hypothek drauf legen");
        scpEndRegeln.setFont(new Font("Monospaced", Font.PLAIN, 20));
        scpEndRegeln.setEditable(false);
        lblRegel.setHorizontalAlignment(SwingConstants.CENTER);
        btnZurueck.setIcon((Icon) (ImageManager.resizeImageOrIcon(ImageManager.loadIcon("Rotesx.png"), 200, 100)));
        btnZurueck.setHorizontalTextPosition(SwingConstants.LEFT);
        btnZurueck.setFont(new Font("Monospaced", Font.PLAIN, 25));
        btnZurueck.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                containerPanel.switchPanel("MenuePanel");
            }
        });
        add(lblRegel);
        add(scpEndRegeln);
        add(btnZurueck);


    }


}
