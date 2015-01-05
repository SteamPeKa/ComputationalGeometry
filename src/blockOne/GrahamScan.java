package blockOne;

import additionalClasses.Dot;
import org.junit.Test;

import java.util.*;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Author: Balagurov Vladimir (privatejoker441@gmail.com)
 * Group: 1742
 * Date: 05/01/15 12:26
 */


public class GrahamScan implements ConvexHullFinder {

    public ConvexHull getConvexHull(final Collection<Dot> dots) {
        if (dots.size() < 3) {
            throw new IllegalArgumentException("Колличество точек меньше 3");

        }
        for (final Dot i : dots) {
            if (i.getDimension() != 2) {
                throw new IllegalArgumentException("Не все точки двумерны");
            }
        }
        final Dot minDot = findMinDot(dots);
        final List<Dot> sortedOthers = new ArrayList<Dot>(dots);
        sortedOthers.remove(minDot);
        Collections.sort(sortedOthers, new PolarComparator(minDot));
        final Stack<Dot> convexHull = new Stack<Dot>();
        Iterator<Dot> sortedIterator = sortedOthers.iterator();
        convexHull.push(minDot);
        convexHull.push(sortedIterator.next());
        while (sortedIterator.hasNext()) {
            final Dot currentDot = sortedIterator.next();
            while (!isNotRightTurn(convexHull.get(convexHull.size() - 2), convexHull.peek(), currentDot)) {
                convexHull.pop();
            }
            convexHull.push(currentDot);
        }
        return new ConvexHull(convexHull);
    }


    private static boolean isNotRightTurn(final Dot dot1, final Dot dot2, final Dot dot3) {
        double BmAx = dot2.getCoordinate(0) - dot1.getCoordinate(0);
        double BmAy = dot2.getCoordinate(1) - dot1.getCoordinate(1);
        double CmAx = dot3.getCoordinate(0) - dot1.getCoordinate(0);
        double CmAy = dot3.getCoordinate(1) - dot1.getCoordinate(1);
        double vectorMultiply = BmAx * CmAy - BmAy * CmAx;
        return vectorMultiply >= 0;
    }

    private static Dot findMinDot(final Iterable<Dot> dots) {
        Iterator<Dot> iterator = dots.iterator();
        Dot minDot;
        if (iterator.hasNext()) {
            minDot = iterator.next();
        } else return null;
        while (iterator.hasNext()) {
            Dot currentDot = iterator.next();
            if (currentDot.getCoordinate(1) < minDot.getCoordinate(1)) {
                minDot = currentDot;
            } else if ((currentDot.getCoordinate(1) == minDot.getCoordinate(1)) & (currentDot.getCoordinate(0) < minDot.getCoordinate(0))) {
                minDot = currentDot;
            }
        }
        return minDot;
    }

    @Test
    public void privateTestTurner() {
        final Dot dotInit = new Dot(new double[]{-1.0, 0.0});
        final Dot dot0 = new Dot(new double[]{0.0, 0.0});
        final Dot dot1 = new Dot(new double[]{0.0, 1.0});
        final Dot dot2 = new Dot(new double[]{1.0, 1.0});
        final Dot dot3 = new Dot(new double[]{1.0, 0.0});
        final Dot dot4 = new Dot(new double[]{-1.0, -1.0});
        final Dot dot5 = new Dot(new double[]{0.0, -1.0});
        assertTrue(isNotRightTurn(dotInit, dot0, dot1));
        assertTrue(isNotRightTurn(dotInit, dot0, dot2));
        assertTrue(isNotRightTurn(dotInit, dot0, dot3));
        assertFalse(isNotRightTurn(dotInit, dot0, dot4));
        assertFalse(isNotRightTurn(dotInit, dot0, dot5));
    }

    private static class PolarComparator implements Comparator<Dot> {
        private final Dot minDot;

        private PolarComparator(Dot minDot) {
            this.minDot = minDot;
        }

        @Override
        public int compare(Dot o1, Dot o2) {
            final Dot o1_relative = new Dot(new double[]{o1.getCoordinate(0) - minDot.getCoordinate(0), o1.getCoordinate(1) - minDot.getCoordinate(1)});
            final Dot o2_relative = new Dot(new double[]{o2.getCoordinate(0) - minDot.getCoordinate(0), o2.getCoordinate(1) - minDot.getCoordinate(1)});
            final double o1_cos = o1_relative.getCoordinate(0) / o1_relative.getModulus();
            final double o2_cos = o2_relative.getCoordinate(0) / o2_relative.getModulus();
            if (o1_cos - o2_cos > 0) {
                return -1;
            }
            if (o1_cos - o2_cos == 0) {
                if (o1_relative.getModulus() > o2_relative.getModulus()) {
                    return 1;
                } else return -1;
            }
            return 1;
        }
    }

    @Test
    public void testCompare() {
        Comparator<Dot> comparator = new PolarComparator(new Dot(new double[]{-1, -2}));
        final Dot dot1 = new Dot(new double[]{0, 3});
        final Dot dot2 = new Dot(new double[]{2, 1});
        final Dot dot5 = new Dot(new double[]{-2, 1});
        assertTrue(comparator.compare(dot1, dot5) < 0);
        System.out.println(comparator.compare(dot1, dot2) > 0);

    }

}
