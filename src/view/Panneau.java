package view;
import logic.GamePiece;

import java.awt.Graphics;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.awt.Image;
import java.io.File;
import java.awt.Color;
import logic.*;

public class Panneau extends JPanel {
    private Game game;
    public GamePiece[][] gp;
    public PacMan pacMan;
    private Ghost[][] ghostboard;

    public Panneau(Game game) {
        this.game = game;
        this.gp=game.getGameBoard();
        this.ghostboard=game.getGameGhostBoard();
        this.pacMan=game.getPacMan();
    }
    public void paintComponent(Graphics g)
    {
        paintWall(g);
        paintDots(g);
        paintPacMan(g);
        paintGhost(g);
    }
    public void paintWall(Graphics g) {
        Wall w = new Wall();
        g.drawLine(50, 100, 50+gp[0].length*25, 100);
        g.drawLine(50, 100, 50, 100+gp[0].length*25);
        g.drawLine(50, 100+gp[0].length*25, 50+gp[0].length*25, 100+gp[0].length*25);//ligne bas
        g.drawLine(50+gp[0].length*25, 100, 50+gp[0].length*25, 100+gp[0].length*25);
        for (int i = 0; i < gp[0].length; i++) {
            for (int j = 0; j < gp[0].length; j++) {
                if(gp[i][j] instanceof Wall) {
                        g.fillRect(25 * j+50, 25 * i+100, 25, 25);
                }
            }
        }
    }

    public void paintPacMan(Graphics g){
        int pcX = pacMan.getX();
        int pcY = pacMan.getY();
        try {
            Image img = ImageIO.read(new File("res/Img/pacman.png"));
            g.drawImage(img, 50+pcY*25, 100+pcX*25,24,24, this);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void paintDots(Graphics g){
        for (int i = 0; i < gp[0].length; i++) {
            for (int j = 0; j < gp[0].length; j++) {
                if(gp[i][j] instanceof PacDot) {
                    g.setColor(Color.CYAN);
                    g.fillOval(25*j+57, 25*i+107, 10, 10);
                }
                if(gp[i][j] instanceof SuperPacDot){
                    g.setColor(Color.RED);
                    g.fillOval(25*j+53, 25*i+103, 17, 17);
                }
                if(gp[i][j] instanceof Fruit){
                    if(gp[i][j].getName().equals(FruitNames.Cerise.toString())){
                        try {
                            Image img = ImageIO.read(new File("res/Img/cerise.png"));
                            g.drawImage(img, 53+j*25, 103+i*25,17,17, this);
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                    }else if(gp[i][j].getName().equals(FruitNames.Fraise.toString())){
                        try {
                            Image img = ImageIO.read(new File("res/Img/fraise.png"));
                            g.drawImage(img, 53+j*25, 103+i*25,17,17, this);
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                    }else if(gp[i][j].getName().equals(FruitNames.Orange.toString())){
                        try {
                            Image img = ImageIO.read(new File("res/Img/orange.png"));
                            g.drawImage(img, 53+j*25, 103+i*25,17,17, this);
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                    }else if(gp[i][j].getName().equals(FruitNames.Pomme.toString())){
                        try {
                            Image img = ImageIO.read(new File("res/Img/pomme.png"));
                            g.drawImage(img, 53+j*25, 103+i*25,17,17, this);
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                    }else if(gp[i][j].getName().equals(FruitNames.Melon.toString())){
                        try {
                            Image img = ImageIO.read(new File("res/Img/melon.png"));
                            g.drawImage(img, 53+j*25, 103+i*25,17,17, this);
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                    }else if(gp[i][j].getName().equals(FruitNames.Galboss.toString())){
                        try {
                            Image img = ImageIO.read(new File("res/Img/galboss.png"));
                            g.drawImage(img, 53+j*25, 103+i*25,17,17, this);
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                    }else if(gp[i][j].getName().equals(FruitNames.Cloche.toString())){
                        try {
                            Image img = ImageIO.read(new File("res/Img/cloche.png"));
                            g.drawImage(img, 53+j*25, 103+i*25,17,17, this);
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                    }else if(gp[i][j].getName().equals(FruitNames.Clé.toString())){
                        try {
                            Image img = ImageIO.read(new File("res/Img/cle.png"));
                            g.drawImage(img, 53+j*25, 103+i*25,17,17, this);
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                    }
                }

            }
        }
    }

    public void paintGhost(Graphics g){
        for (int i = 0; i < ghostboard[0].length; i++) {
            for (int j = 0; j < ghostboard[0].length; j++) {
                if(ghostboard[i][j] instanceof Ghost) {
                    if((ghostboard[i][j].isStateEaten())){
                        try {
                            Image img = ImageIO.read(new File("res/Img/eatenghost.png"));
                            g.drawImage(img, 50+j*25, 100+i*25,25,25, this);
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                    }
                    else if(ghostboard[i][j].getName()=="\033[31m" + "G1" + "\033[39m") {
                        try {
                            Image img = ImageIO.read(new File("res/Img/ghost3.png"));
                            g.drawImage(img, 50+j*25, 100+i*25,25,25, this);
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                    } else if(ghostboard[i][j].getName()=="\033[32m" + "G2" + "\033[39m") {
                        try {
                            Image img = ImageIO.read(new File("res/Img/ghost2.png"));
                            g.drawImage(img, 50+j*25, 100+i*25,25,25, this);
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                    }else if(ghostboard[i][j].getName()=="\033[36m" + "G3" + "\033[39m") {
                        try {
                            Image img = ImageIO.read(new File("res/Img/ghost1.png"));
                            g.drawImage(img, 50+j*25, 100+i*25,25,25, this);
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                    }else if(ghostboard[i][j].getName()=="\033[35m" + "G4" + "\033[39m") {
                        System.out.println("4");
                        try {
                            Image img = ImageIO.read(new File("res/Img/ghost4.png"));
                            g.drawImage(img, 50+j*25, 100+i*25,25,25, this);
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

}