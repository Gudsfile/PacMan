package view;

import logic.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class LPanel extends JPanel implements ActionListener {
    private JTextField textField1;
    private JButton enregistrerButton;
    private JButton nouvellePartieButton;
    private JButton stopButton;
    public MainPanel mainPanel;
    private JPanel panel1;

    public LPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.add(panel1);
        nouvellePartieButton.addActionListener(this);
        stopButton.addActionListener(this);
        enregistrerButton.addActionListener(this);
    }


    public void paintComponent(Graphics g) {
        Image img = null;
        try {
            img = ImageIO.read(new File("res/Img/gameover.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(img, 0, 0, this);

        Font font = new Font("Arial", Font.BOLD, 30);
        g.setFont(font);
        g.setColor(Color.WHITE);
        String fs = String.valueOf(mainPanel.getGame().getFinalScore());
        g.drawString("Your score :" + fs, 150, 300);
    }

    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == nouvellePartieButton) {
            mainPanel.startNewGame();
        }
        if (arg0.getSource() == stopButton) {
            System.exit(0);
        }
        if (arg0.getSource() == enregistrerButton) {
            mainPanel.getGame().writeScore(textField1.getText());
            enregistrerButton.setEnabled(false);
        }
    }

}
