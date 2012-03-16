package explorer;

import java.util.concurrent.Semaphore;

public interface MinMaxListener {

    public void locked(int indexInTreeGame, boolean moveFoward, Semaphore semaphore);

    public void locked(boolean moveFoward, int indexInTreeGame);
}
