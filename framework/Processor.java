package framework;

import morpion.*;
import connectFour.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

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
        //game = new ConnectFour();
    }

    public void giveGameCopy() {
        for(int i = 0; i < players.size(); i++) {
            players.get(i).setGame(game.clone());
        }
    }

    public void initPlayers() {
        /**players.add(new MorpionAI("ia0", 0));
        game.addPlayer(players.get(0));*/
        for(int i = 0; i < game.getNbMaxPlayer() - 1; i++) {
            System.out.print("Ajouter un joueur : ");
            players.add(new MorpionHumanPlayer(in.next(), i));
            //players.add(new CFHumanPlayer(in.next(), i));
            game.addPlayer(players.get(i));
        }
        players.add(new MorpionAI("ia1", 1));
        game.addPlayer(players.get(1));

        game.piecesDistribution();
        System.out.println(game.nextPlayer().getPiece().getId());
    }

    public void setPlayer(int i, Player player) {
    }

    public void startGame() {
        Move makedMove = null;
        Thread alarmT = null;
        Semaphore sem = null;
        while(!game.isFinish()) {
            Alarm alarm = new Alarm(10000);
            try {
                alarmT = new Thread(alarm);
            } catch(Exception e) {
                e.printStackTrace();
            }
            giveGameCopy();
            System.out.println(game);
            Player currentPlayer = game.nextPlayer();
            Thread threadPlayer =  new Thread(currentPlayer);
            try {
                sem = new Semaphore(0);
                alarm.setSemaphore(sem);
                currentPlayer.setSemaphore(sem);
                threadPlayer.start();
                alarmT.start();
                sem.acquire();
                alarmT.interrupt();
                threadPlayer.interrupt();

                /*int cpt = 0;
                while(!currentPlayer.isFinalDecision() && cpt < 5000) {
                    Thread.sleep(100);
                    cpt++;
                }*/
                if(!currentPlayer.hasMove()) {
                    System.out.println("désolé, tu n'as pas joué assez vite.");
                }
                else {
                    makedMove = currentPlayer.getMove();
                }
            } catch(Exception e) {
                e.printStackTrace();
                System.exit(0);
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
