/*package view;
import data.ScoreWritter;
import logic.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class LosePanel extends JPanel {

    public MainPanel mainPanel;

    public LosePanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;

        JTextField pseudoTextField = new JTextField("               ");

        JButton playButton = new JButton( "Nouvelle Partie");
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mainPanel.getGame().writeScore(pseudoTextField.getText());
                mainPanel.startNewGame();
            }
        });

        JButton stopButton = new JButton( "Stop");
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mainPanel.getGame().writeScore(pseudoTextField.getText());
                System.exit(0);
            }
        });

        this.add(pseudoTextField);
        this.add(playButton);
        this.add(stopButton);

    }

    public void paintComponent(Graphics g) {
        Image img = null;
        try {
            img = ImageIO.read(new File("res/Img/gameover.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(img, 0, 0,this);
        g.setColor(Color.WHITE);
        String fs=String.valueOf(mainPanel.getGame().getFinalScore());
        g.drawString("Your score :"+fs,432 ,100);
    }



    }

*/