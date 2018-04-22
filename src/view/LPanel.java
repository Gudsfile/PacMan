package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Panel de défaite
 */
public class LPanel extends JPanel implements ActionListener {
    /**
     * Panel principal
     */
    public MainPanel mainPanel;
    /**
     * Textfield pour le pseudo du joueur
     */
    private JTextField textField1;
    /**
     * Boutton pour enregistrer le score
     */
    private JButton enregistrerButton;
    /**
     * Boutton pour lancer une nouvelle partie
     */
    private JButton nouvellePartieButton;
    /**
     * Boutton pour arrêter le jeu
     */
    private JButton stopButton;
    /**
     * Panel principal du panel Lose
     */
    private JPanel panel1;

    /**
     * Constructeur du panel Lose
     * @param mainPanel
     */
    public LPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.add(panel1);
        this.nouvellePartieButton.addActionListener(this);
        this.stopButton.addActionListener(this);
        this.enregistrerButton.addActionListener(this);
    }

    /**
     * Construit l'affichage
     * @param g
     */
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
        String fs = String.valueOf(this.mainPanel.getGame().getFinalScore());
        g.drawString("Your score :" + fs, 150, 300);
    }

    /**
     * Controlleur des bouttons
     * @param arg0 action effectué
     */
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == nouvellePartieButton) {
            this.mainPanel.startNewGame();
        }
        if (arg0.getSource() == stopButton) {
            System.exit(0);
        }
        if (arg0.getSource() == enregistrerButton) {
            this.mainPanel.getGame().writeScore(this.textField1.getText());
            this.enregistrerButton.setEnabled(false);
        }
    }

}
