
import logic.Game;
import view.Play;

public class TestPlayGame {

    public static void main(String[] args) throws InterruptedException {

        // Récupération de la fenêtre
        Play play = new Play();

        // Récupération du jeu
        // Game g = new Game(1);
        Game g = play.getGame();

        // Affichage du plateau dans la console
        g.displayBoard();

        // Création des threads
        TestPlayThread tpc = new TestPlayThread("PC", g, play); // PacMan
        TestPlayThread tg1 = new TestPlayThread("G1", g); // Ghost 1
        TestPlayThread tg2 = new TestPlayThread("G2", g); // Ghost 2
        TestPlayThread tg3 = new TestPlayThread("G3", g); // Ghost 3
        TestPlayThread tg4 = new TestPlayThread("G4", g); // Ghost 4
        TestPlayThread tve = new TestPlayThread("VE", g, play); // Affichage
        TestPlayThread tpo = new TestPlayThread("PO", g); // PowerDuration
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/*
System.out.println("                        ##########\n" +
"                     #################\n" +
"                  #######################\n" +
"                ########################## \n" +
"              ########               #######\n" +
"             #####                       #### \n" +
"             ###                           ###\n" +
"            ###                             ## \n" +
"            ##                               # \n" +
"            ##                               # \n" +
"            ##               #              ##\n" +
"             ###             #             ## \n" +
"             ######          #           ### \n" +
"               ###############          ###### \n" +
"                ##############          ###### \n" +
"                    ########## \n" +
"                ### \n" +
"              ########\n" +
"             ##########   ##### \n" +
"            ####    ####  ######\n" +
"            ##        ##      ## \n" +
"            ##        ##       # \n" +
"             ##      ##       ## \n" +
"              ##################\n" +
"            ###################\n" +
"            ##################\n" +
"            ## \n" +
"            #\n" +
"            #                  # \n" +
"            #################### \n" +
"            #################### \n" +
"            #################### \n" +
"            #               ##\n" +
"                             ## \n" +
"            #                ###\n" +
"            #################### \n" +
"            ####################\n" +
"            ##################\n" +
"                             ##\n" +
"            #                ###\n" +
"            #################### \n" +
"            ####################\n" +
"            ################### \n" +
"            # \n" +
"                   ###### \n" +
"                ############ \n" +
"              ################ \n" +
"             #####   ##   ##### \n" +
"            ###      ##       ##\n" +
"            #        ##        # \n" +
"            #        ##        # \n" +
"            #        ##       ##\n" +
"             #       ##     ####\n" +
"             ##      ##########\n" +
"               ##    ########\n" +
"                     #### \n" +
"                        ##########\n" +
"                     #################\n" +
"                  #######################\n" +
"                ########################## \n" +
"              #######                #######\n" +
"             ####                        #### \n" +
"             ##                            ###\n" +
"            ##                              ## \n" +
"            #                                # \n" +
"            #                                # \n" +
"            ##                              ##\n" +
"             ###                           ###\n" +
"             #####                       ####\n" +
"               ########            ######## \n" +
"                ########################## \n" +
"                  ######################\n" +
"                      ##############\n" +
"                                  \n" +
"                               # \n" +
"                              ## \n" +
"                         ####### \n" +
"                       ######### \n" +
"                  ############## \n" +
"              ############ \n" +
"            ############\n" +
"                #### \n" +
"                    #####\n" +
"                       #####   # \n" +
"                           ##### \n" +
"                               # \n" +
"                               # \n" +
"                   ###### \n" +
"                ############ \n" +
"              ################ \n" +
"             #####   ##   ##### \n" +
"            ###      ##       ##\n" +
"            #        ##        # \n" +
"            #        ##        # \n" +
"            #        ##       ##\n" +
"             #       ##     ####\n" +
"             ##      ##########\n" +
"               ##    ########\n" +
"                     #### \n" +
"            #                  # \n" +
"            #################### \n" +
"            #################### \n" +
"            #################### \n" +
"            #             ###\n" +
"                            ## \n" +
"                             ## \n" +
"                             ###\n" +
"                         ####### \n" +
"                         ####### \n" +
"                         #######\n" +
"                              \n");
*/