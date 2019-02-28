package ru.stqa.ptf.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
    @Test
    public void testLine() {
        Point a = new Point(10, 22);
        Point b = new Point(0, 22);
        double actual = Launcher.distance(a, b);
        double expected = 10;
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testLine1() {
        Point a = new Point(-2, -3);
        Point b = new Point(-3, -2);
        double actual = Launcher.distance(a, b);
        double expected = 1.4142135623730951;
        Assert.assertEquals(actual, expected);

    }

    @Test
    public void testLine2() {
        Point a = new Point(22, 33);
        Point b = new Point(0, 0.2);
        double actual = Launcher.distance(a, b);
        double expected = 39.49480978559082;
        Assert.assertEquals(actual, expected);
    }
}

