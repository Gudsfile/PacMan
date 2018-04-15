package view;
import logic.*;

public class Play {

    private Game game;
    private int level=1;
    private MainFrame mainFrame;

    /**
     * animates
     *
     * @param args NONE
     */
    public static void main(String[] args) {

        Play play=new Play();

    }

    public Play() {
        this.game=new Game(this.level);
        this.mainFrame = new MainFrame(game);
    }

    /**
     * Recupère la touche appuyé par l'utilisateur
     * @return un entier correspondant à la touche appuyé et 0 si aucune touche n'a été appuyé
     */
    public int getKeys(){
        if(mainFrame.isDownPressed()) {
            System.out.println("Ok");
            return 1;
        } else if (mainFrame.isLeftPressed()) {
            return 2;
        } else if (mainFrame.isRightPressed()) {
            return 3;
        } else if (mainFrame.isUpPressed()) {
            return 4;
        } else{
            return 0;
        }
    }

    public void draw(){

    }

    public void repaint(){

    }


}
