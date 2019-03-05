package ru.stqa.ptf.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
    @Test
    public void testLine() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(3, 4);
        double actual = p1.distance(p2);
        double expected = 5;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testLine1() {
        Point p1 = new Point(-2, -3);
        Point p2 = new Point(-3, -2);
        double actual = p1.distance(p2);
        double expected = 1.4142135623730951;
        Assert.assertEquals(actual, expected);

    }

    @Test
    public void testLine2() {
        Point p1 = new Point(22, 33);
        Point p2 = new Point(0, 0.2);
        double actual = Launcher.distance(p1, p2);
        double expected = 39.49480978559082;
        Assert.assertEquals(actual, expected);
    }
}

