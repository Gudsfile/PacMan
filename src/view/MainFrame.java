package view;

import logic.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {

    private Game game;
    private static boolean upPressed, downPressed, leftPressed, rightPressed;
    private GamePanel gamePanel;
    private CardLayout cl;

    public MainFrame(Game game) {
        this.game = game;
        this.setTitle("PacMan");
        this.setSize(665, 785);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        GamePanel gp = new GamePanel(game);
        WinPanel wp = new WinPanel(game);
        LosePanel lp = new LosePanel(game);
        this.cl =new CardLayout();
        JPanel pan = new JPanel(cl);
        pan.add(gp,"GAMEPANEL");
        pan.add(wp,"WINPANEL");
        pan.add(lp,"LOSEPANEL");

        this.add(pan);

        cl.show(pan,"GAMEPANEL");

        this.addKeyListener(new MainFrame.KeyboardListener());
    }



    /**
     * Check whether the UP key is currently pressed
     *
     * @return true if the UP key is currently pressed
     */
    public boolean isUpPressed() {
        return upPressed;
    }

    /**
     * Check whether the DOWN key is currently pressed
     *
     * @return true if the DOWN key is currently pressed
     */
    public boolean isDownPressed() {
        return downPressed;
    }

    /**
     * Check whether the LEFT key is currently pressed
     *
     * @return true if the LEFT key is currently pressed
     */
    public boolean isLeftPressed() {
        return leftPressed;
    }

    /**
     * Check whether the RIGHT key is currently pressed
     *
     * @return true if the RIGHT key is currently pressed
     */
    public boolean isRightPressed() {
        return rightPressed;
    }

    /**
     * Inner class KeyboardListener - listens for the UP, DOWN, RIGHT, LEFT
     * keys.
     */
    private class KeyboardListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent event) {
            switch (event.getKeyCode()) {
                case KeyEvent.VK_UP:
                    System.out.println("Pressed : UP");
                    MainFrame.upPressed = true;
                    MainFrame.downPressed = false;
                    MainFrame.leftPressed = false;
                    MainFrame.rightPressed = false;
                    //game.play(1);
                    gamePanel.repaint();
                    break;
                case KeyEvent.VK_DOWN:
                    System.out.println("Pressed : DOWN");
                    MainFrame.upPressed = false;
                    MainFrame.downPressed = true;
                    MainFrame.leftPressed = false;
                    MainFrame.rightPressed = false;
                    //game.play(2);
                    gamePanel.repaint();
                    break;
                case KeyEvent.VK_LEFT:
                    System.out.println("Pressed : LEFT");
                    MainFrame.upPressed = false;
                    MainFrame.downPressed = false;
                    MainFrame.leftPressed = true;
                    MainFrame.rightPressed = false;
                    //game.play(3);
                    gamePanel.repaint();
                    break;
                case KeyEvent.VK_RIGHT:
                    System.out.println("Pressed : RIGHT");
                    MainFrame.upPressed = false;
                    MainFrame.downPressed = false;
                    MainFrame.leftPressed = false;
                    MainFrame.rightPressed = true;
                    //game.play(4);
                    gamePanel.repaint();
                    break;
            }
        }
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public CardLayout getCardLayout(){
        return this.cl;
    }

}