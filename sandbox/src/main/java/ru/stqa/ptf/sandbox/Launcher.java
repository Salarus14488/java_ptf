package ru.stqa.ptf.sandbox;

public class Launcher {
    public static void main(String[] args) {
        Point start = new Point(6, 6);
        System.out.println(start.distance(1, 6));
        System.out.println(distance(3, 4, 8, 10));
    }

    public static double distance(double x1, double y1, double x2, double y2) {
       double a = x1 - x2;
       double b = y1 - y2;
       if (a < 0) {
           a = a * -1;
       }
       if (b < 0) {
           b = b * -1;
       }
       double c = a * a + b * b;
       return Math.sqrt(c);
    }
















}
