package monopoly.controller.manager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ImageManager {
    public static Image loadImage(String name) {
        Image image = null;

        try {
            image = ImageIO.read(ImageManager.class.getResourceAsStream(name));
        } catch (IOException e) {

            e.printStackTrace();
        }

        return image;
    }


    public static ImageIcon loadIcon(String name) {
        ImageIcon icon = null;

        try {
            icon = new ImageIcon(ImageIO.read(ImageManager.class.getResourceAsStream("/Icons/" + name)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return icon;
    }


    public static Object resizeImageOrIcon(Object object, int width, int height) {

        if (object instanceof Image) {
            Image img = ((Image) object).getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return img;
        } else if (object instanceof ImageIcon) {
            Image img = ((ImageIcon) object).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } else {
            return null;
        }

    }

}

