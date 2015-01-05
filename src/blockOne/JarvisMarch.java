package blockOne;

import additionalClasses.Dot;

import java.util.Collection;
import java.util.Stack;

/**
 * Author: Balagurov Vladimir (privatejoker441@gmail.com)
 * Group: 1742
 * Date: 05/01/15 19:31
 */


public class JarvisMarch implements ConvexHullFinder {
    @Override
    public ConvexHull getConvexHull(Collection<Dot> dots) {
        if (dots.size() < 3) {
            throw new IllegalArgumentException("Колличество точек меньше 3");

        }
        Dot leftmost = dots.iterator().next();
        for (final Dot i : dots) {
            if (i.getDimension() != 2) {
                throw new IllegalArgumentException("Не все точки двумерны");
            }
            if (i.getCoordinate(0) < leftmost.getCoordinate(0)) {
                leftmost = i;
            } else if (i.getCoordinate(0) == leftmost.getCoordinate(0)) {
                if (i.getCoordinate(1) < leftmost.getCoordinate(1)) {
                    leftmost = i;
                }
            }
        }
        Dot pointOnHull = leftmost;
        Dot endpoint;
        final Stack<Dot> hull = new Stack<Dot>();
        do {
            hull.push(pointOnHull);
            endpoint = leftmost;

            for (final Dot currentDot : dots) {
                if ((!hull.contains(currentDot)) & (isNotRightTurn(hull.peek(), endpoint, currentDot))) {
                    endpoint = currentDot;
                }
            }
            pointOnHull = endpoint;
        }

        while (leftmost != endpoint);

        return new

                ConvexHull(hull);

    }

    private static boolean isNotRightTurn(final Dot dot1, final Dot dot2, final Dot dot3) {
        double BmAx = dot2.getCoordinate(0) - dot1.getCoordinate(0);
        double BmAy = dot2.getCoordinate(1) - dot1.getCoordinate(1);
        double CmAx = dot3.getCoordinate(0) - dot1.getCoordinate(0);
        double CmAy = dot3.getCoordinate(1) - dot1.getCoordinate(1);
        double vectorMultiply = BmAx * CmAy - BmAy * CmAx;
        return vectorMultiply >= 0;
    }
}
