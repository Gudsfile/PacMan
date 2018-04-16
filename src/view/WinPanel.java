package view;
import logic.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class WinPanel extends JPanel {
    private Game game;
    public WinPanel(Game game) {

        this.game = game;
        this.add(this, BorderLayout.SOUTH);
        this.setLayout(new GridLayout(2,2));
        JLabel pseudo=new JLabel("Pseudo : ");
        JTextField pseudoTextField = new JTextField("");
        JButton playButton = new JButton( "Nouvelle Partie", game.writeScore(pseudoTextField.getText()));
        JButton stopButton = new JButton( "Stop");
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
        String fs=String.valueOf(game.getFinalScore());
        g.drawString("Your score :"+fs,432 ,100);
    }
}

