package ru.stqa.ptf.sandbox;

public class Launcher {
    public static void main(String[] args) {
        Point p1 = new Point(0., 0.);
        Point p2 = new Point(3.0, 4.0);
        System.out.println(p1.distance(p2));
        System.out.println(distance(p1, p2));
    }

    public static double distance(Point p1, Point p2) {
        double px = p1.x - p2.x;
        px = px * px;
        double py = p1.y - p2.y;
        py = py * py;
        return Math.sqrt(px + py);

    }
}





