/*
import logic.Game;
import logic.Ghost;
import view.Play;

public class TestPlayThread extends Thread {
    private Game g;
    private static int temp = 0;
    private Play play;

    TestPlayThread(String name, Game g) {
        super(name);
        this.g = g;
        this.start();
    }

    TestPlayThread(String name, Game g, Play play) {
        super(name);
        this.g = g;
        this.start();
        this.play = play;
    }

    public void run() {

        switch (this.getName()) {
            case "PC":
                while (g.getLife() >= 0) {
                    if (this.play.getMainFrame().isUpPressed()) {
                        g.play((1));
                    } else if (this.play.getMainFrame().isDownPressed()) {
                        g.play((2));
                    } else if (this.play.getMainFrame().isLeftPressed()) {
                        g.play((3));
                    } else if (this.play.getMainFrame().isRightPressed()) {
                        g.play((4));
                    }
                    try {
                        Thread.sleep(350);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            case "G1":
                while (g.getLife() >= 0) {
                    g.play(g.getGhostList().get(0));
                    try {
                        Thread.sleep(350);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            case "G2":
                while (g.getLife() >= 0) {
                    g.play(g.getGhostList().get(1));
                    try {
                        Thread.sleep(350);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            case "G3":
                while (g.getLife() >= 0) {
                    g.play(g.getGhostList().get(2));
                    // verification();
                    try {
                        Thread.sleep(350);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            case "G4":
                while (g.getLife() >= 0) {
                    g.play(g.getGhostList().get(3));
                    try {
                        Thread.sleep(350);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            case "VE":
                while (g.getLife() >= 0) {
                    g.displayBoard();
                    this.play.getMainFrame().getGamePanel().repaint();
                    System.out.println("Score:" + g.getScore() + " Life:" + g.getLife() + " Power: " + g.isPower());
                    System.out.println("");
                    verification();
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case "PO":
                while (g.getLife() >= 0) {
                    if (g.isPower()) {
                        long now = System.currentTimeMillis();
                        while (System.currentTimeMillis() - now < g.getPowerDuration() * 1000) {

                        }
                        g.setPower(false);
                    }
                    try {
                        Thread.sleep(350);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        }
    }

    private void verification() {
        for (Ghost temp : g.getGhostList()) {
            if (g.getPacMan().getX() == temp.getX() && g.getPacMan().getY() == temp.getY()) {
                if (!g.isPower()) {
                    g.killPacMan();
                } else if (g.isPower()) {
                    g.killGhost(temp.getX(), temp.getY());
                }
            }
        }
    }
}*/
