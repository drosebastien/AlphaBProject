package framework;

import java.util.concurrent.Semaphore;

/**
 * This class is used to warn a class which use this alarm once a given time is
 * up. It implements the Runnable interface allowing it to be launched in a
 * thread.
 */
public class Alarm implements Runnable {
    private int time;
    private Semaphore sem;

    /**
     * This constructor initialize the alarm with a given time.
     * @param time The time to wait.
     */
    public Alarm(int time) {
        this.time = time;
    }

    /**
     * This method is used to set the semaphore on which the user class have
     * to wait. This semaphore have to be initialize to 0 (critical section).
     * @param sem The semaphore on which the userClass have to be blocked.
     */
    public void setSemaphore(Semaphore sem) {
        this.sem = sem;
    }

    /**
     * This method is used to change the time of the alarm.
     * @param time The new time of the alarm.
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * Start the alarm thread. The alarm release the selected semaphore when
     * the time is up. The time starts flowing when this method is called.
     */
    public void run() {
        try {
            Thread.sleep(time);
        } catch(InterruptedException e) {
        }

        sem.release();
    }
}
