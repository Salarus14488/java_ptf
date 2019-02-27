package ru.stqa.ptf.sandbox;

public class Launcher {
    public static void main(String[] args) {
        Point a = new Point(4, 5);
        Point b = new Point(5, 8);
        System.out.println(distance(a, b));
    }


    public static double distance(Point a, Point b) {
        return new Line(a, b).calculateDistance();
    }


}
