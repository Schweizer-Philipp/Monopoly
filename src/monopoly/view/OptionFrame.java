package monopoly.view;


import monopoly.controller.manager.ImageManager;
import monopoly.controller.manager.OptionFrameManager;

import javax.swing.*;
import java.awt.*;

public class OptionFrame extends JPanel {


    private final Image bild;
    private JFrame optionFrame;
    private JButton btnpseichern;
    private JButton btnverlassen;
    private GridLayout gLayout;
    private JLabel lblname;
    private Font font;
    private OptionFrameManager optionFrameManager;

    public OptionFrame() {
        bild = ImageManager.loadImage("/Icons/Rotesx.png");
        initVars();
        initComps();
        optionFrame.setUndecorated(true);
        optionFrame.setLocation(Toolkit.getDefaultToolkit()
                .getScreenSize().width / 2 - optionFrame.getWidth() / 2, Toolkit.getDefaultToolkit()
                .getScreenSize().height / 2 - optionFrame.getHeight() / 2);
        optionFrame.setVisible(false);
    }

    private void initVars() {
        optionFrameManager = new OptionFrameManager(this);
        font = new Font("Monospaced", Font.PLAIN, 20);
        optionFrame = new JFrame();
        btnpseichern = new JButton();
        btnverlassen = new JButton();
        gLayout = new GridLayout(3, 1, 20, 20);
        lblname = new JLabel("Optionen");
    }


    private void initComps() {
        addMouseListener(optionFrameManager);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(new Color(187, 231, 230));
        setLayout(gLayout);
        optionFrame.add(this);
        lblname.setHorizontalAlignment(SwingConstants.CENTER);
        lblname.setFont(new Font("Monospaced", Font.PLAIN, 30));
        add(lblname);
        setUpButtton(btnpseichern, "Speichern und Verlassen");
        setUpButtton(btnverlassen, "Verlassen ohne Speichern");
        optionFrame.setSize(600, 600);
    }

    private void setUpButtton(JButton btn, String txt) {
        btn.setFocusable(false);
        btn.setIcon((Icon) ImageManager.resizeImageOrIcon(ImageManager.loadIcon("Spielstarten.jpg"), 100, 100));
        btn.setHorizontalTextPosition(SwingConstants.LEFT);
        btn.setActionCommand(txt);
        btn.setFont(font);
        btn.setText(txt);
        btn.addActionListener(optionFrameManager);
        btn.setHorizontalAlignment(SwingConstants.CENTER);
        add(btn);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bild, 550, 25, 25, 25, null);

    }

    public JFrame getOptionFrame() {
        return optionFrame;
    }
}
