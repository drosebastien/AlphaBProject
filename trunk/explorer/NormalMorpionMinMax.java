package explorer;

import framework.*;
import morpion.*;
import tree.*;

public class NormalMorpionMinMax extends MinMaxAlgo {

    public NormalMorpionMinMax(Game game, int maxDepth) {
        super(game, maxDepth);

        if(!(game instanceof Morpion)) {
            // lancer une exception
        }
    }

    public NormalMorpionMinMax(Game game, int maxDepth, TreeNode treeRoot) {
        super(game, maxDepth, treeRoot);
    }

    public Move launchMinMax() {
        return null;
    }
}
