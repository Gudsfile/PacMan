package view;

import javax.swing.*;
import java.awt.Color;

public class Fenetre extends JFrame {

    public Fenetre(){
        this.setTitle("PacMan");
        this.setSize(400, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);


    }
}
