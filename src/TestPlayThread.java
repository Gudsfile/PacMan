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
        // int[] mo = {1,1,1,1,1,2,2,2,2,2,2,4,2,2,2,4,4,4,4,3,3,3,4}; // va à l'entrée des fantômes
        // int[] mo = {1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,1,1,1,3,1,2,2,2}; // va au SP en haut à droite
        // int[] mo = {1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1, 1}; // va dans un tp à droite
        // int[] mo = {4,4,4,4,4,2,2,2,2,2,2,4,4,4,4,4}; // va dans un tp à gauche

        switch (this.getName()) {
            case "PC":
                while (g.getLife() >= 0) {
                    // Récupération de la touche pressée puis mouvement
                    if (this.play.getMainFrame().isUpPressed()) {
                        g.play((1));
                    } else if (this.play.getMainFrame().isDownPressed()) {
                        g.play((2));
                    } else if (this.play.getMainFrame().isLeftPressed()) {
                        g.play((3));
                    } else if (this.play.getMainFrame().isRightPressed()) {
                        g.play((4));
                    }
                    // g.play(mo[temp]);
                    // temp = (temp + 1) % mo.length;
                    // verification();
                    try {
                        Thread.sleep(350);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            case "G1":
                while (g.getLife() >= 0) {
                    threadGhost(g.getGhostList().get(0));
                    // verification();
                    try {
                        Thread.sleep(350);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            case "G2":
                while (g.getLife() >= 0) {
                    threadGhost(g.getGhostList().get(1));
                    // verification();
                    try {
                        Thread.sleep(350);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            case "G3":
                while (g.getLife() >= 0) {
                    threadGhost(g.getGhostList().get(2));
                    // verification();
                    try {
                        Thread.sleep(350);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            case "G4":
                while (g.getLife() >= 0) {
                    threadGhost(g.getGhostList().get(3));
                    // verification();
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

    private synchronized void threadGhost(Ghost ghost) {
        int dx = (int) (Math.random() * ((399) + 1));
        int dy = (int) (Math.random() * ((399) + 1));
        if (dx < 100) {
            dx = -1;
            dy = 0;
        } else if (dx < 200) {
            dx = 0;
            dy = 1;
        } else if (dx < 300) {
            dx = 1;
            dy = 0;
        } else if (dx < 400) {
            dx = 0;
            dy = -1;
        }
        this.g.play(ghost, dx, dy);
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
}