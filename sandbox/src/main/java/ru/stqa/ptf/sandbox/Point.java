package ru.stqa.ptf.sandbox;

public class Point {
    // region Поля
    private double x;
    private double y;

    // endregion

    public Point(double x, double y) {
        this.x = x;
        this.y = y;

    }

    public double distance(Point end) {
        double a = Math.abs(this.x - end.x);
        double b = Math.abs(this.y - end.y);
        double c = Math.pow(a, 2) + Math.pow(b, 2);
        return Math.sqrt(c);
    }

    public double distance(double x, double y) {
        Point end = new Point(x, y);
        return distance(end);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}

