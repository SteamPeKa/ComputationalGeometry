package blockOne;

import additionalClasses.Dot;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Author: Balagurov Vladimir (privatejoker441@gmail.com)
 * Group: 1742
 * Date: 05/01/15 15:06
 */


public class GrahamScanTest {
    @Test
    public void testGetHull() {
        final Dot dot1 = new Dot(new double[]{0, 3});
        final Dot dot2 = new Dot(new double[]{2, 1});
        final Dot dot3 = new Dot(new double[]{1, -2});
        final Dot dot4 = new Dot(new double[]{-1, -2});
        final Dot dot5 = new Dot(new double[]{-2, 1});
        final Dot dot6 = new Dot(new double[]{1, 1});
        final Dot dot7 = new Dot(new double[]{-1, 1});
        final Dot dot8 = new Dot(new double[]{0, -1});
        final Dot dot9 = new Dot(new double[]{0, 1});
        final Dot dot10 = new Dot(new double[]{-1, -1});
        final Dot dot11 = new Dot(new double[]{1, -1});
        final List<Dot> dots = new ArrayList<Dot>();
        dots.add(dot1);
        dots.add(dot2);
        dots.add(dot3);
        dots.add(dot4);
        dots.add(dot5);
        dots.add(dot6);
        dots.add(dot7);
        dots.add(dot8);
        dots.add(dot9);
        dots.add(dot10);
        dots.add(dot11);
        ConvexHullFinder.ConvexHull hull = new GrahamScan().getConvexHull(dots);
        assertTrue(hull.contains(dot1));
        assertTrue(hull.contains(dot2));
        assertTrue(hull.contains(dot3));
        assertTrue(hull.contains(dot4));
        assertTrue(hull.contains(dot5));
        assertFalse(hull.contains(dot6));
        assertFalse(hull.contains(dot7));
        assertFalse(hull.contains(dot8));
        assertFalse(hull.contains(dot9));
        assertFalse(hull.contains(dot10));
        assertFalse(hull.contains(dot11));
    }
}
