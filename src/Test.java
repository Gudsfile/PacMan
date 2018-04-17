import logic.Game;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        //Play play = new Play();
        //Game game = play.getGame();
        Game game = new Game(1);

        game.displayBoard();


        for (int i = 0; i < 100; i++) {
            int m = (int) (Math.random() * (4) + 1);
            game.play(m);
            game.play(game.getGhostList().get(0));
            game.play(game.getGhostList().get(1));
            game.displayBoard();
            System.out.println("Score:" + game.getScore() + " Life:" + game.getLife() + " Power: " + game.isPower());
            Thread.sleep(500);
        }
        game.play(2);
        game.play(game.getGhostList().get(0));
        game.play(game.getGhostList().get(1));

        game.displayBoard();

    }
}
