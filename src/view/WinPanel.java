/*
package view;

import logic.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class WinPanel extends JPanel {

    private MainPanel mainPanel;

    public WinPanel(MainPanel mainPanel) {

        this.mainPanel = mainPanel;

        JLabel pseudo = new JLabel("Pseudo : ");
        JTextField pseudoTextField = new JTextField("");
        JButton playButton = new JButton("Nouvelle Partie");
        JButton stopButton = new JButton("Stop");
        this.add(pseudo);
        this.add(pseudoTextField);
        this.add(playButton);
        this.add(stopButton);
    }

    public void paintComponent(Graphics g) {
        Image img = null;
        try {
            img = ImageIO.read(new File("res/Img/youwin.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(img, 0, 50, this);
        g.setColor(Color.WHITE);
        String fs = String.valueOf(mainPanel.getGame().getFinalScore());
        g.drawString("Your score :" + fs, 432, 100);
    }
}
*/