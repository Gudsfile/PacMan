package view;

import logic.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class LPanel extends JPanel {
    private JTextField textField1;
    private JButton enregistrerButton;
    private JButton nouvellePartieButton;
    private JButton stopButton;
    public MainPanel mainPanel;
    private JPanel panel1;

    public LPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.add(panel1);
        nouvellePartieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MainFrame mainFrame = new MainFrame();
                Game game = mainFrame.getMainPanel().getGame();

                Thread threadG1 = new Thread(game, "G1");
                Thread threadG2 = new Thread(game, "G2");
                Thread threadG3 = new Thread(game, "G3");
                Thread threadG4 = new Thread(game, "G4");
                Thread threadPO = new Thread(game, "PO");

                threadG1.start();
                threadG2.start();
                threadG3.start();
                threadG4.start();
                threadPO.start();

                mainFrame.go();

            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        enregistrerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    mainPanel.getGame().writeScore(textField1.getText());
            }
        });


    }

    public void paintComponent(Graphics g){
        Image img = null;
        try {
            img = ImageIO.read(new File("res/Img/gameover.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(img, 0, 0,this);

        Font font = new Font("Arial",Font.BOLD,30);
        g.setFont(font);
        g.setColor(Color.WHITE);
        String fs=String.valueOf(mainPanel.getGame().getFinalScore());
        g.drawString("Your score :"+fs,150 ,300);
    }

}
