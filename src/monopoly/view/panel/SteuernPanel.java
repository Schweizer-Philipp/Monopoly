package monopoly.view.panel;

import monopoly.controller.manager.ImageManager;

import javax.swing.*;
import java.awt.*;


public class SteuernPanel extends JPanel {

    private GridLayout gLayout;
    private Font font;

    private JLabel lblImage;
    private JLabel lblName;

    public SteuernPanel() {
        initVars();
        initComps();
    }


    private void initVars() {

        gLayout = new GridLayout(2, 1);
        font = new Font("Monospaced", Font.PLAIN, 25);
        lblImage = new JLabel();
        lblName = new JLabel();
    }


    private void initComps() {
        setOpaque(false);
        setLayout(gLayout);

    }

    public void setUpPanel(String name) {
        removeAll();
        lblImage.setIcon((Icon) ImageManager.resizeImageOrIcon(ImageManager.loadIcon("EinkommFeld.png"), 150, 150));
        lblImage.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblImage);
        lblName.setFont(font);
        if (name.equals("4")) {
            lblName.setText("Einkommenssteuern");
        } else if (name.equals("38")) {
            lblName.setText("Zusatzsteuern");
        }
        lblName.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblName);
    }
}
