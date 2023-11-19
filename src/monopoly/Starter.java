package monopoly;

import monopoly.view.MonopolyFrame;

import javax.swing.*;
import java.io.File;

public class Starter {


    public static void main(String[] args) {


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MonopolyFrame();
                File currentDir = new File("");
                String projectPath = currentDir.getAbsolutePath();
            }
        });
    }


}
