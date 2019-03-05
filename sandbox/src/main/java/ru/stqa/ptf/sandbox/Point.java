package ru.stqa.ptf.sandbox;

public class Point {
    public double x;
    public double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point p){
        double px = this.x - p.x;
        px = px * px;
        double py = this.y - p.y;
        py = py * py;
        return Math.sqrt(px + py);
    }

}


