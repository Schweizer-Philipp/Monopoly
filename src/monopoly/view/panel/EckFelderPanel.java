package monopoly.view.panel;

import monopoly.controller.manager.DateiManager;
import monopoly.controller.manager.ImageManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class EckFelderPanel extends JPanel {


    private ArrayList<String> eckFelder;

    private JLabel lblimg;
    private JLabel lblname;

    private GridLayout gl;

    public EckFelderPanel() {
        initVars();
        initComps();
        setUpEckfelder("0");
    }


    private void initVars() {
        gl = new GridLayout(2, 1);
        eckFelder = DateiManager.dateiLesen("Eckfelder.txt");
        lblname = new JLabel();
        lblimg = new JLabel();

    }


    private void initComps() {
        setOpaque(false);
        setLayout(gl);

    }

    public void setUpEckfelder(String feldID) {
        // wir wollen wissen welche feld der spieler ist das wir noch keinen dynamischen wert bekommen nehmen wir die id noch oben um das
        // zu siumlieren
        // heisst : unser ArrayList durch und spliten jeden eintrag an der stelle @ daraus bekommen wir viele strings
        // wir wissen wie unsere textdtei aufgebaut ist : ID@namefuerbild@name
        // also wollen wir den ersten eintrag mit unserer id vergleichen um zu wissen welche zeile wir brauchen.
        // wenn wir die richtige gefunden haben wollen wir Bild und name auf die jlabels machen :)

        for (String s : eckFelder) {
            String[] test = s.split("@");

            if (test[0].equals(feldID)) {
                lblimg.setIcon((ImageIcon) ImageManager.resizeImageOrIcon(ImageManager.loadIcon(test[1]), 150, 150));
                lblimg.setHorizontalAlignment(SwingConstants.CENTER);
                lblimg.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
                add(lblimg);

                lblname.setText(test[2]);
                lblname.setHorizontalAlignment(SwingConstants.CENTER);
                lblname.setFont(new Font("Monospaced", Font.PLAIN, 20));
                add(lblname);
                break;
            }

        }
    }
}
