package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {

    private static boolean upPressed, downPressed, leftPressed, rightPressed;
    private MainPanel mainPanel;

    public MainFrame() {
        this.setTitle("PacMan");
        this.setSize(665, 785);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new MainPanel();
        setBackground(Color.BLACK);
        this.add(mainPanel);

        this.setVisible(true);
        this.addKeyListener(new MainFrame.KeyboardListener());
    }

    public void go() {
        while (!mainPanel.getGame().isFinished()) {
            if (upPressed) {
                mainPanel.getGame().play(1);
            } else if (downPressed) {
                mainPanel.getGame().play(2);
            } else if (leftPressed) {
                mainPanel.getGame().play(3);
            } else if (rightPressed) {
                mainPanel.getGame().play(4);
            }
            mainPanel.repaint();
            //mainPanel.getGame().displayBoard();
            //System.out.println("Life: "+mainPanel.getGame().getLife()+" Power : "+mainPanel.getGame().isPower()+" Score : "+mainPanel.getGame().getFinalScore());
            try {
                Thread.sleep(mainPanel.getGame().getPacMan().getSpeed());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void goRepaint() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mainPanel.repaint();
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

    public MainPanel getMainPanel() {
        return mainPanel;
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
                    MainFrame.upPressed = true;
                    MainFrame.downPressed = false;
                    MainFrame.leftPressed = false;
                    MainFrame.rightPressed = false;
                    mainPanel.getGame().setStarted(true);
                    break;
                case KeyEvent.VK_DOWN:
                    MainFrame.upPressed = false;
                    MainFrame.downPressed = true;
                    MainFrame.leftPressed = false;
                    MainFrame.rightPressed = false;
                    mainPanel.getGame().setStarted(true);
                    break;
                case KeyEvent.VK_LEFT:
                    MainFrame.upPressed = false;
                    MainFrame.downPressed = false;
                    MainFrame.leftPressed = true;
                    MainFrame.rightPressed = false;
                    mainPanel.getGame().setStarted(true);
                    break;
                case KeyEvent.VK_RIGHT:
                    MainFrame.upPressed = false;
                    MainFrame.downPressed = false;
                    MainFrame.leftPressed = false;
                    MainFrame.rightPressed = true;
                    mainPanel.getGame().setStarted(true);
                    break;
            }
        }
    }

    public void setMainPanel(MainPanel mp) {
        this.mainPanel = mp;
    }
}
