package explorer;

import framework.*;

public interface EvalFunction extends MinMaxListener {

    public int evalFunction();

    public void setGame(Game game);

    public void setPlayer(Player player);
}
