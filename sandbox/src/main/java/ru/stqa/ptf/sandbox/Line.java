package ru.stqa.ptf.sandbox;

public class Line {
    private Point start;
    private Point end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public double calculateDistance() {
        double a = Math.abs(start.getX() - end.getX());
        double b = Math.abs(start.getY() - end.getY());
        return Math.sqrt(a * a + b * b);

    }
}
