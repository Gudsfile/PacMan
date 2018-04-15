package view;

import logic.Game;

import javax.swing.*;
import java.awt.Color;

public class Fenetre extends JFrame {
    JPanel pan;
    private Game game;
    public Fenetre(Game game){
        this.game=game;
        this.setTitle("PacMan");
        this.setSize(700, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pan = new Panneau(game);
        this.setContentPane(pan);
        this.setVisible(true);


    }

    public void go(){
        while(true){

            try {

                Thread.sleep(3);

            } catch (InterruptedException e) {

                e.printStackTrace();

            }
            pan.repaint();
        }
    }
}
