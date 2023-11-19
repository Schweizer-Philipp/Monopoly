package monopoly.view.panel;

import monopoly.controller.manager.ImageManager;

import javax.swing.*;
import java.awt.*;


public class WerkePanel extends JPanel {


    private GridLayout gLayout;


    private Font lblFont;


    private JLabel lblName;
    private JLabel lblImg;
    private JLabel lblTxt;
    private JLabel lblTxt1;
    private JLabel lblPreis;


    public WerkePanel() {

        initVars();

    }

    private void initVars() {
        gLayout = new GridLayout(5, 1);

        lblFont = new Font("Monospaced", Font.PLAIN, 20);

        lblImg = new JLabel();
        lblName = new JLabel();
        lblTxt = new JLabel();
        lblTxt1 = new JLabel();
        lblPreis = new JLabel();

    }

    public void initComps(String feldID) {
        setLayout(gLayout);


        setOpaque(false);


        if (feldID.equals("12")) {
            werkeWerte(lblImg, null, "Ewerk.png");
            werkeWerte(lblName, "Elektrizitaetswerk", null);
            werkeWerte(lblPreis, "150EUR", null);
            werkeWerte(lblTxt, "1.Werk 4 fache", null);
            werkeWerte(lblTxt1, "2.Werke 10 fache", null);
        } else if (feldID.equals("28")) {
            werkeWerte(lblImg, null, "Wasserwerk.png");
            werkeWerte(lblName, "Wasserwerk", null);
            werkeWerte(lblPreis, "150EUR", null);
            werkeWerte(lblTxt, "1.Werk 4 fache", null);
            werkeWerte(lblTxt1, "2.Werke 10 fache", null);
        }

    }

    public void werkeWerte(JLabel lbl, String txt, String img) {
        if (img == null) {

            lbl.setText(txt);
            lbl.setFont(lblFont);
            lbl.setHorizontalAlignment(SwingConstants.CENTER);
            add(lbl);
        } else {
            lbl.setIcon((Icon) (ImageManager.resizeImageOrIcon(ImageManager.loadIcon(img), 100, 100)));
            lbl.setHorizontalAlignment(SwingConstants.CENTER);
            add(lbl);
        }

    }


}
