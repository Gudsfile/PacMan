import logic.Game;
import view.Fenetre;

public class Test {

    public static void main(String[] args) {
        Game game = new Game(1);
        game.play(1);
        game.play(2);
        game.play(2);
        game.play(1);
        game.play(1);
        game.play(2);
        game.play(2);
        game.play(2);
        game.play(2);
        game.play(2);
        game.play(2);
        game.play(4);
        game.play(4);
        game.play(4);
        game.setPower(true);

        game.displayBoard();
        System.out.println(game.getScore());
    }
}
