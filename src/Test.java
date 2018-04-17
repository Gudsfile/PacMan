import logic.Game;
import view.MainFrame;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        MainFrame mainFrame = new MainFrame();
        Game game = mainFrame.getMainPanel().getGame();

        Thread threadG1 = new Thread(game, "G1");
        Thread threadG2 = new Thread(game, "G2");
        Thread threadG3 = new Thread(game, "G3");
        Thread threadG4 = new Thread(game, "G4");
        Thread threadPO = new Thread(game, "PO");

        threadG1.start();
        threadG2.start();
        threadG3.start();
        threadG4.start();
        threadPO.start();

        mainFrame.go();

        //Game game = new Game(1);

        //game.displayBoard();

        /*
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

        game.displayBoard();*/

    }
}
