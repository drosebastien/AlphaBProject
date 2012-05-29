package framework;

import java.util.concurrent.Semaphore;

/**
 * Cette classe permet de prevenir une classe l'utilisant qu'un temps gagné est
 * passé. 
 */
public class Alarm implements Runnable {
    private int time;
    private Semaphore sem;

    /**
     * Ce constructeur initialise l'alarme avec le temps voulu.
     * @param time The time to wait.
     */
    public Alarm(int time) {
        this.time = time;
    }

    /**
     * Cette methode est utilisée pour fournir a l'alarme le semaphore qu'il
     * faut liberer pour avertir la classe faisant appelle a cette classe.
     * @param sem Le semaphore sur leque la classe utilisant une alarme est
     * bloquee.
     */
    public void setSemaphore(Semaphore sem) {
        this.sem = sem;
    }

    /**
     * Cette methode permet de changer le temps d'attente.
     * @param time Le nouveau temps d'attente de l'alarme.
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * demarre le thread de l'alarme. L'alarme relache le semaphore fournit
     * lorsque le temps est ecoule.
     */
    public void run() {
        try {
            Thread.sleep(time);
        } catch(InterruptedException e) {
        }

        sem.release();
    }
}
