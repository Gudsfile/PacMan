package view;

import logic.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class GamePanel extends JPanel {
    private Game game;

    public GamePanel(Game game) {
        this.game = game;
    }

    public void paintComponent(Graphics g) {

        paintStaticGamePiece(g);
        paintPacMan(g);
        paintGhost(g);
        paintLife(g);
        paintScore(g);
        paintScore(g);
        if(game.getLife()<0){
            getCardLayout().show(pan,"GAMEPANEL");
        }
    }

    public void paintStaticGamePiece(Graphics g) {
        for (int i = 0; i < game.getGameBoard().length; i++) {
            for (int j = 0; j < game.getGameBoard()[0].length; j++) {
                GamePiece gamePiece = game.getGameBoard()[i][j];
                Image img;
                try {
                    if (gamePiece == null) {
                        img = ImageIO.read(new File("res/Img/background.png"));
                        g.drawImage(img, j * 35, 50 + i * 35, 35, 35, this);
                    } else if (gamePiece instanceof Wall) {
                        img = ImageIO.read(new File("res/Img/wall.png"));
                        g.drawImage(img, j * 35, 50 + i * 35, 35, 35, this);
                    } else if (gamePiece instanceof SuperPacDot) {
                        img = ImageIO.read(new File("res/Img/superPacDot.png"));
                        g.drawImage(img, j * 35, 50 + i * 35, 35, 35, this);
                    } else if (gamePiece instanceof Fruit) {
                        String fruitName = gamePiece.getName();
                        if (fruitName.equals(FruitNames.Cerise.toString())) {
                            img = ImageIO.read(new File("res/Img/cerise.png"));
                            g.drawImage(img, j * 35, 50 + i * 35, 35, 35, this);
                        } else if (fruitName.equals(FruitNames.Fraise.toString())) {
                            img = ImageIO.read(new File("res/Img/fraise.png"));
                            g.drawImage(img, j * 35, 50 + i * 35, 35, 35, this);
                        } else if (fruitName.equals(FruitNames.Orange.toString())) {
                            img = ImageIO.read(new File("res/Img/orange.png"));
                            g.drawImage(img, j * 35, 50 + i * 35, 35, 35, this);
                        } else if (fruitName.equals(FruitNames.Pomme.toString())) {
                            img = ImageIO.read(new File("res/Img/pomme.png"));
                            g.drawImage(img, j * 35, 50 + i * 35, 35, 35, this);
                        } else if (fruitName.equals(FruitNames.Melon.toString())) {
                            img = ImageIO.read(new File("res/Img/melon.png"));
                            g.drawImage(img, j * 35, 50 + i * 35, 35, 35, this);
                        } else if (fruitName.equals(FruitNames.Galboss.toString())) {
                            img = ImageIO.read(new File("res/Img/galboss.png"));
                            g.drawImage(img, j * 35, 50 + i * 35, 35, 35, this);
                        } else if (fruitName.equals(FruitNames.Cloche.toString())) {
                            img = ImageIO.read(new File("res/Img/cloche.png"));
                            g.drawImage(img, j * 35, 50 + i * 35, 35, 35, this);
                        } else if (fruitName.equals(FruitNames.Clé.toString())) {
                            img = ImageIO.read(new File("res/Img/cle.png"));
                            g.drawImage(img, j * 35, 50 + i * 35, 35, 35, this);
                        }
                    } else if (gamePiece instanceof PacDot) {
                        img = ImageIO.read(new File("res/Img/pacDot.png"));
                        g.drawImage(img, j * 35, 50 + i * 35, 35, 35, this);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void paintPacMan(Graphics g) {
        int pcX = game.getPacMan().getX();
        int pcY = game.getPacMan().getY();
        Image img = new ImageIcon("res/Img/pacman.gif").getImage();
        g.drawImage(img, pcY * 35, 50 + pcX * 35, 35, 35, this);
    }

    public void paintGhost(Graphics g) {
        Ghost ghost;
        for (int i = 0; i < game.getGameGhostBoard().length; i++) {
            for (int j = 0; j < game.getGameGhostBoard()[0].length; j++) {
                ghost = game.getGameGhostBoard()[i][j];
                if (ghost != null) {
                    if (game.isPower()) {
                        Image img = new ImageIcon("res/Img/ghost_danger.gif").getImage();
                        g.drawImage(img, j * 35, 50 + i * 35, 35, 35, this);
                    } else if (ghost.isStateEaten()) {
                        Image img = new ImageIcon("res/Img/ghost_killed.png").getImage();
                        g.drawImage(img, j * 35, 50 + i * 35, 35, 35, this);
                    } else if (ghost.getName().equals("\033[31m" + "G1" + "\033[39m")) {
                        Image img = new ImageIcon("res/Img/blinky.gif").getImage();
                        g.drawImage(img, j * 35, 50 + i * 35, 35, 35, this);
                    } else if (ghost.getName().equals("\033[32m" + "G2" + "\033[39m")) {
                        Image img = new ImageIcon("res/Img/pinky.gif").getImage();
                        g.drawImage(img, j * 35, 50 + i * 35, 35, 35, this);
                    } else if (ghost.getName().equals("\033[36m" + "G3" + "\033[39m")) {
                        Image img = new ImageIcon("res/Img/inky.gif").getImage();
                        g.drawImage(img, j * 35, 50 + i * 35, 35, 35, this);
                    } else if (ghost.getName().equals("\033[35m" + "G4" + "\033[39m")) {
                        Image img = new ImageIcon("res/Img/clyde.gif").getImage();
                        g.drawImage(img, j * 35, 50 + i * 35, 35, 35, this);
                    }
                }
            }
        }
    }

    public void paintLife(Graphics g) {
        for (int i = 0; i < game.getLife(); i++) {
            Image img = null;
            img = new ImageIcon("res/Img/pacman.png").getImage();
            g.drawImage(img, i * 50, 0, 50, 50, this);
        }
    }

    public void paintScore(Graphics g){
        g.setColor(Color.WHITE);
        Font font = new Font("Courier", Font.BOLD, 40);
        g.setFont(font);
        g.drawString("Score : "+this.game.getFinalScore(), 300, 40);
    }
}