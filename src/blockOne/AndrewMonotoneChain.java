package blockOne;

import additionalClasses.Dot;

import java.util.*;

/**
 * Author: Balagurov Vladimir (privatejoker441@gmail.com)
 * Group: 1742
 * Date: 05/01/15 18:28
 */


public class AndrewMonotoneChain implements ConvexHullFinder {
    public ConvexHull getConvexHull(final Collection<Dot> dots) {
        if (dots.size() < 3) {
            throw new IllegalArgumentException("Колличество точек меньше 3");

        }
        for (final Dot i : dots) {
            if (i.getDimension() != 2) {
                throw new IllegalArgumentException("Не все точки двумерны");
            }
        }
        final List<Dot> sortedDots = new ArrayList<Dot>(dots);
        Collections.sort(sortedDots, new LexicalComparator());
        final List<Dot> lowerHull = new ArrayList<Dot>();

        for (Dot currentDot : sortedDots) {
            while (lowerHull.size() >= 2 && (!isNotRightTurn(lowerHull.get((lowerHull.size() - 1)), lowerHull.get((lowerHull.size() - 2)), currentDot))) {
                lowerHull.remove((lowerHull.size() - 1));
            }
            lowerHull.add(currentDot);
        }
        lowerHull.remove( (lowerHull.size() - 1));
        final List<Dot> upperHull = new ArrayList<Dot>();

        Collections.reverse(sortedDots);
        for (Dot currentDot : sortedDots) {
            while (upperHull.size() >= 2 && (!isNotRightTurn(upperHull.get((upperHull.size() - 1)), upperHull.get( (upperHull.size() - 2)), currentDot))) {
                upperHull.remove( (upperHull.size() - 1));
            }
            upperHull.add(currentDot);
        }
        upperHull.remove((upperHull.size() - 1));
        lowerHull.addAll(upperHull);
        return new ConvexHull(lowerHull);
    }

    private static boolean isNotRightTurn(final Dot dot1, final Dot dot2, final Dot dot3) {
        double BmAx = dot2.getCoordinate(0) - dot1.getCoordinate(0);
        double BmAy = dot2.getCoordinate(1) - dot1.getCoordinate(1);
        double CmAx = dot3.getCoordinate(0) - dot1.getCoordinate(0);
        double CmAy = dot3.getCoordinate(1) - dot1.getCoordinate(1);
        double vectorMultiply = BmAx * CmAy - BmAy * CmAx;
        return vectorMultiply >= 0;
    }

    private static class LexicalComparator implements Comparator<Dot> {
        @Override
        public int compare(Dot o1, Dot o2) {
            for (int i = 0; i < o1.getDimension(); i++) {
                double a = o1.getCoordinate(i) - o2.getCoordinate(i);
                if (a > 0) {
                    return 1;
                }
                if (a < 0) {
                    return -1;
                }
            }
            return 0;
        }
    }
}
