package view;

import logic.Game;

public class Play {

    private Game game;
    private int level = 1;
    private MainFrame mainFrame;

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public MainFrame getMainFrame() {
        return mainFrame;
    }

    public void setMainFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public Play() {
        this.game = new Game(this.level);
        this.mainFrame = new MainFrame(game);


    }

    /**
     * Recupère la touche appuyé par l'utilisateur
     *
     * @return un entier correspondant à la touche appuyé et 0 si aucune touche n'a été appuyé
     */
    public int getKeys() {
        if (mainFrame.isDownPressed()) {
            System.out.println("Ok");
            return 1;
        } else if (mainFrame.isLeftPressed()) {
            return 2;
        } else if (mainFrame.isRightPressed()) {
            return 3;
        } else if (mainFrame.isUpPressed()) {
            return 4;
        } else {
            return 0;
        }
    }

    public void draw() {

    }

    public void repaint() {

    }


}
