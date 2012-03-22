package framework;

import java.util.concurrent.Semaphore;

public class Alarm implements Runnable {
    private int time;
    private Semaphore sem;

    public Alarm(int time) {
        this.time = time;
    }

    public void setSemaphore(Semaphore sem) {
        this.sem = sem;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void run() {
        try {
            Thread.sleep(time);
        } catch(InterruptedException e) {
        }

        sem.release();
    }
}
