package explorer;

public abstract class AbstractGameEvalFctFactory {

    public abstract String[] getBuildableEvalFct();

    public abstract EvalFunction getEvalFct(String name);
}
