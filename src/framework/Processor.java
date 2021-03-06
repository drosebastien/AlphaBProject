package framework;

import gui.*;
import morpion.*;
import morpion.evaluationFunction.*;
import connectFour.*;
import explorer.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 * Cette classe permet de lancer les différents fonctionnalite de l'outil.
 * Rmq, l'outil pedagogique est la seule fonctionnalite implementee.
 * @author Sebastien Drobisz.
 */
public class Processor {
    private Scanner in;
    private Game game;
    private ArrayList<Player> players;
    private MainFrame mainFrame;

    /**
     * Ce constructeur permet d'initialiser un processor.
     */
    public Processor() {
        in = new Scanner(System.in);
        players = new ArrayList<Player>();
    }

    /**
     * Cette methode permet de lancer l'outil pedagogique avec le jeu dont le
     * nom est passe en parametre.
     * @param gameName Le nom du jeu avec lequel l'outil doit etre lance.
     */
    public void launchPedMode(String gameName) {
        int maxDepth = 2;

        Controller controller = new Controller("sebController");
        GameFactory gameFactory = GameFactory.getInstance();

        game = gameFactory.getGame(gameName);
        int nbPlayer = game.getNbMinPlayer();
        for(int i = 0; i < nbPlayer; i++) {
            game.addPlayer(new MorpionHumanPlayer("joueur " + i, i));
        }


        game.piecesDistribution();
        Game gameCopy = game.clone();

        TreePanel treePanel = new NormalTreePanel();
        treePanel.addListener(controller);

        GamePanel gamePanel = gameCopy.getPanel();
        gamePanel.addListener(controller);

        AlgoPanel algoPanel = new DialogAlgoPanel();

        EvalFunction evalFct = new RandomEvalFunction();

        mainFrame = new MainFrame(gamePanel, treePanel, algoPanel);
        mainFrame.addListener(controller);

        String[] algoNames =
                   MinMaxAlgoFactory.getInstance().getBuildableMinMaxAlgoName();
        MinMaxAlgo minMaxAlgo = MinMaxAlgoFactory.getInstance().getMinMaxAlgo(
                                     algoNames[0], gameCopy, maxDepth, evalFct);

        Explorer explorer = new Explorer(gameCopy, gamePanel,
                                         treePanel, maxDepth);
        Executor executor = new Executor(gameCopy, gamePanel,
                                         treePanel, minMaxAlgo);
        // demande à l'executor de le mettre dans la liste de listener de l'algo

        executor.addMinMaxListener(algoPanel);

        explorer.addExecutor(executor);
        controller.addExplorer(explorer);
        controller.addExecutor(executor);

        executor.setEvalFctFactory(gameFactory.getGameEvalFctFactory(
                                                    gameName));
        mainFrame.setEvalFctFactory(gameFactory.getGameEvalFctFactory(
                                                    gameName));
        //

        explorer.start();
    }


//    public void giveGameCopy(Game game) {
//        for(int i = 0; i < players.size(); i++) {
//            players.get(i).setGame(game);
//        }
//    }

//    public void initPlayers() {
//        String[] names = {"seb", "matt"};
//        /**players.add(new MorpionAI("ia0", 0));
//        game.addPlayer(players.get(0));*/
//        for(int i = 0; i < game.getNbMaxPlayer() - 1; i++) {
//            //System.out.print("Ajouter un joueur : ");
//            players.add(new MorpionHumanPlayer(names[i], i));
//            //players.add(new CFHumanPlayer(in.next(), i));
//            game.addPlayer(players.get(i));
//        }
//        players.add(new MorpionAI("ia1", 1));
//        game.addPlayer(players.get(1));

//        game.piecesDistribution();
//    }

//    public void setPlayer(int i, Player player) {
//    }

//    public void startGame() {
//        Move makedMove = null;
//        Thread alarmT = null;
//        Semaphore sem = null;
//        while(!game.isFinish()) {
//            Alarm alarm = new Alarm(10000000);
//            try {
//                alarmT = new Thread(alarm);
//            } catch(Exception e) {
//                e.printStackTrace();
//            }
//            Game gameCopy = game.clone();
//            giveGameCopy(gameCopy);
//            System.out.println(game);
//            Player currentPlayer = game.nextPlayer();
//            Thread threadPlayer =  new Thread(currentPlayer);
//            try {
//                sem = new Semaphore(0);
//                alarm.setSemaphore(sem);
//                currentPlayer.setSemaphore(sem);
//                threadPlayer.start();
//                alarmT.start();
//                sem.acquire();
//                alarmT.interrupt();
//                threadPlayer.interrupt();

//                if(!currentPlayer.hasMove()) {
//                    System.out.println("désolé, tu n'as pas joué assez vite.");
//                }
//                else {
//                    makedMove = currentPlayer.getMove();
//                }
//            } catch(Exception e) {
//                e.printStackTrace();
//                System.exit(0);
//            }
//            try {
//                game.play(makedMove);
//            }
//            catch(MoveException e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.print("Fin de partie, ");
//        if(game.isVictory()) {
//            System.out.println("le vainqueur est : " + game.getWinner().getName());
//        }
//        else {
//            System.out.println("il n'y a pas de gagnant");
//        }
//        System.out.println(game);
//    }
}
