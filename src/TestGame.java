import logic.Game;

public class TestGame {

    public static void main(String []args) throws InterruptedException {

        Game g = new Game(1);
        g.getGameBoard().displayBoard();

        while(true) {
            TestThread tpc = new TestThread("PC", g);
            TestThread tg1 = new TestThread("G1", tpc, g);
            TestThread tg2 = new TestThread("G2", tg1, g);
            TestThread tg3 = new TestThread("G3", tg2, g);
            TestThread tg4 = new TestThread("G4", tg3, g);
            TestThread tve = new TestThread("VE", tg4, g);
            try {
                Thread.sleep(500);
                g.getGameBoard().displayBoard();
                System.out.println("Score:"+g.getScore()+" Life:"+g.getLife()+" Power: "+g.isPower());
                System.out.println("");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


}
