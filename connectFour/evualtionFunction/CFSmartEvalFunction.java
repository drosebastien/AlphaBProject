package connectFour.evaluationFunction;

import connectFour.*;
import framework.*;
import explorer.*;

import java.util.ArrayList;

public class CFSmartEvalFunction extends RandomEvalFunction {
    private C4Analyser analyser;

    public int evalFunction() {
        if(getGame().getListOfPlayer().get(0).equals(getPlayer())) {
            int count = 0;
            for(int i = 0; i < 69; i++) {
                if(analyser.getPlayer1Value(i) >=0) {
                    if(analyser.getPlayer1Value(i) >= 4) {
                        return 200;
                    }
                    count+= Math.pow(2,analyser.getPlayer1Value(i));
                }
            }
            for(int i = 0; i < 69; i++) {
                if(analyser.getPlayer2Value(i) >=4) {
                    return -200;
                }
            }
            return count;
        }
        int count = 0;
        for(int i = 0; i < 69; i++) {
            if(analyser.getPlayer2Value(i) >=0) {
                if(analyser.getPlayer2Value(i) >= 4) {
                    return 200;
                }
                count+= Math.pow(2,analyser.getPlayer1Value(i));;
            }
        }
        for(int i = 0; i < 69; i++) {
            if(analyser.getPlayer1Value(i) >=4) {
                return -200;
            }
        }
        return count;
    }

    public static String getName() {
        return "CF Analyser";
    }

    public void started() {
        ArrayList<Player> players = getGame().getListOfPlayer();
        analyser = new C4Analyser(players.get(0), players.get(1));
        analyser.initializePossibleC4Array((ConnectFour) getGame());
    }

    public void moved(Movement dir, int indexInTreeGame, MinMaxEvent event) {
        Move move = event.getMove();
        if(move != null) {
            if(dir == Movement.FORWARD) {
                analyser.addToken(move);
            }
            else {
                analyser.removeToken(move);
            }
        }
        System.out.println(analyser);
    }

    public void setValueOfNode(String value, MinMaxEvent event) {}

    public void refreshTree(MinMaxEvent event) {}

    public void setNewBestNode(int indexOfChild, MinMaxEvent event) {}

    public void setDropedNode(int indexOfChild, MinMaxEvent event) {}
}
