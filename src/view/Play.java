package view;
import logic.*;

public class Play {

    private Game game;
    private int level=1;

    /**
     * animates
     *
     * @param args NONE
     */
    public static void main(String[] args)
    {
        Play play=new Play();
    }

    public Play() {
        this.game=new Game(this.level);
    }

    /**
     * Recupère la touche appuyé par l'utilisateur
     * @return un entier correspondant à la touche appuyé et 0 si aucune touche n'a été appuyé
     */
    public int getTouche(){
        Canvas canvas = Canvas.getCanvas();
        if(canvas.isDownPressed()) {return 1;}
        else if (canvas.isLeftPressed()) {return 2;}
        else if (canvas.isRightPressed()){return 3;}
        else if (canvas.isUpPressed()) { return 4;}
        else{return 0;}
    }

    public void draw(){

    }



}
