package connectFour.evaluationFunction;

import connectFour.evaluationFunction.tools.C4Analyser;
import connectFour.*;
import framework.*;
import explorer.*;

import java.util.ArrayList;

public abstract class CFAbstractEvalFunction extends RandomEvalFunction {
    protected static final int MAX = 990;
    protected C4Analyser analyser;

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
    }

    public void setValueOfNode(String value, MinMaxEvent event) {}

    public void refreshTree(MinMaxEvent event) {}

    public void setNewBestNode(int indexOfChild, MinMaxEvent event) {}

    public void setDropedNode(int indexOfChild, MinMaxEvent event) {}
}
