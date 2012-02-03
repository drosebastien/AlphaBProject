package framework;

import morpion.*;
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
        game = new Morpion();
    }

    public void giveGameCopy() {
        for(int i = 0; i < players.size(); i++) {
            players.get(i).setGame(game.clone());
        }
    }

    public void initPlayers() {
        for(int i = 0; i < game.getNbMaxPlayer() - 1; i++) {
            System.out.print("Ajouter un joueur : ");
            players.add(new HumanPlayerMorpion(in.next(), i));
            game.addPlayer(players.get(i));
        }
        /**players.add(new AI("ia0", 0));
        game.addPlayer(players.get(0));*/
        players.add(new AIMorpion("ia1", 1));
        game.addPlayer(players.get(1));

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
            System.out.println("le vainceur est : " + game.getWinner().getName());
        }
        else {
            System.out.println("il n'y a pas de vainceur");
        }
        System.out.println(game);
    }
}
