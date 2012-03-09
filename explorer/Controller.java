package explorer;

public class Controller {
    private String name;
    private Explorer explorer;
    private Executor executor;

    public Controller(String name) {
        this.name = name;
    }

    public void printMessage(String message, boolean mode) {
        System.out.println(name + " : " + message);
        if(mode) {
            explorer.printMessage(message);
        }
        else {
            executor.printMessage(message);
        }
    }

    public void addExplorer(Explorer explorer) {
        this.explorer = explorer;
    }

    public void addExecutor(Executor executor) {
        this.executor = executor;
    }
}
