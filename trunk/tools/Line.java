package tools;
/**
 * Permet de représenté une droite y = ax + b par ses deux points a et b.
 */
public class Line {
    private double a;
    private double b;

    public Line(double a, double b) {
        this.a = a;
        this.b = b;
    }

    public Line() {
        this.a = 0;
        this.b = 0;
    }

    public void setLine(int x1, int y1, int x2, int y2) {
        a = ((y1 - y2) * 1.) / (x1 - x2);
        b = y1 - x1 * a;
    }

    public double getHeight(int y1, int y2) {
        double x1 = getX(y1);
        double x2 = getX(y2);

        return Math.abs(x1 - x2);
    }

    public double getY(double x) {
        return a * x + b;
    }

    public double getX(double y) {
        return (y - b) / a;
    }

    public String toString() {
        return "y = " + a + "x + " + b;
    }
}
