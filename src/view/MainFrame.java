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

    public MainFrame(Game game) {
        this.game = game;
        this.setTitle("PacMan");
        this.setSize(665, 785);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gamePanel = new GamePanel(game);
        setBackground(Color.BLACK);
        this.add(gamePanel);

        this.setVisible(true);
        this.addKeyListener(new MainFrame.KeyboardListener());
    }

    public void go() {
        while (true) {
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gamePanel.repaint();
        }
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
}
