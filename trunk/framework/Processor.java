package framework;

import morpion.*;
import connectFour.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Processor {
    private Scanner in;
    private Game game;
    private ArrayList<Player> players;

    public Processor() {
        in = new Scanner(System.in);
        players = new ArrayList<Player>();
    }

    public void start() {
        initGame();
        initPlayers();
        startGame();
    }

    public void initGame() {
        //game = new Morpion();
        game = new ConnectFour();
    }

    public void giveGameCopy() {
        for(int i = 0; i < players.size(); i++) {
            players.get(i).setGame(game.clone());
        }
    }

    public void initPlayers() {
        /**players.add(new MorpionAI("ia0", 0));
        game.addPlayer(players.get(0));*/
        for(int i = 0; i < game.getNbMaxPlayer(); i++) {
            System.out.print("Ajouter un joueur : ");
            //players.add(new MorpionHumanPlayer(in.next(), i));
            players.add(new CFHumanPlayer(in.next(), i));
            game.addPlayer(players.get(i));
        }
        /**players.add(new MorpionAI("ia1", 1));
        game.addPlayer(players.get(1));*/

        game.piecesDistribution();
        System.out.println(game.nextPlayer().getPiece().getId());
    }

    public void setPlayer(int i, Player player) {
    }

    public void startGame() {
        Move makedMove = null;
        while(!game.isFinish()) {
            giveGameCopy();
            System.out.println(game);
            Player currentPlayer = game.nextPlayer();
            Thread thread =  new Thread(currentPlayer);
            try {
                thread.start();
                int cpt = 0;
                while(!currentPlayer.isFinalDecision() && cpt < 20000) {
                    thread.sleep(100);
                    cpt++;
                }
                if(!currentPlayer.hasMove()) {
                    System.out.println("désolé, tu n'as pas joué assez vite. BOUM");
                }
                else {
                    makedMove = currentPlayer.getMove();
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
            game.play(makedMove);
        }
        System.out.print("Fin de partie, ");
        if(game.isVictory()) {
            System.out.println("le vainqueur est : " + game.getWinner().getName());
        }
        else {
            System.out.println("il n'y a pas de gagnant");
        }
        System.out.println(game);
    }
}
