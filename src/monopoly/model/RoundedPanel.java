package monopoly.model;

import javax.swing.*;
import java.awt.*;

/**
 * @author Robert Hein
 */
public class RoundedPanel extends JPanel {
    /**
     * Strichstaerke. Empfehle ich fuer bessere Ansicht auf 1 zu setzen.
     **/
    protected int strokeSize = 1;
    /**
     * Schattenfarbe.
     **/
    protected Color shadowColor = Color.WHITE;
    /**
     * True wenn ein Schatten gemalt werden soll.
     **/
    protected boolean shady = false;
    /**
     * True wenn AA an sein soll.
     **/
    protected boolean highQuality = true;
    /**
     * Radius der Ecken.
     **/
    protected Dimension arcs = new Dimension(40, 40);
    /**
     * Distanze zwischen Panel und Schatten.
     **/
    protected int shadowGap = 5;
    /**
     * Offset des Schattens.
     **/
    protected int shadowOffset = 4;
    /**
     * Transparenz des Schattens. (0-255)
     **/
    protected int shadowAlpha = 150;

    public RoundedPanel() {
        super();
        setOpaque(false);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();
        int shadowGap = this.shadowGap;
        Color shadowColor = new Color(this.shadowColor.getRed(),
                this.shadowColor.getGreen(),
                this.shadowColor.getBlue(),
                shadowAlpha);
        Graphics2D g2d = (Graphics2D) g;

        // sets Antialising if HQ.
        if (highQuality) {
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }

        // Draws shadow borders if any
        if (shady) {
            g2d.setColor(shadowColor);
            g2d.fillRoundRect(shadowOffset,
                    shadowOffset,
                    width - strokeSize - shadowOffset,
                    height - strokeSize - shadowOffset,
                    arcs.width, arcs.height);
        } else {
            shadowGap = 1;
        }

        // Draws the rounded opaque panel with borders-
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, width - shadowGap, height - shadowGap, arcs.width, arcs.height);
        g2d.setColor(getForeground());
        g2d.setStroke(new BasicStroke(strokeSize));
        g2d.drawRoundRect(0, 0, width - shadowGap, height - shadowGap, arcs.width, arcs.height);

        // Sets stroke to default, is better
        g2d.setStroke(new BasicStroke());
    }
}