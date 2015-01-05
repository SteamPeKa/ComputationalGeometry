package blockOne;

import additionalClasses.Dot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Author: Balagurov Vladimir (privatejoker441@gmail.com)
 * Group: 1742
 * Date: 05/01/15 19:31
 */


public interface ConvexHullFinder {
    public class ConvexHull {
        private final List<Dot> hull;

        public ConvexHull(Collection<Dot> hull) {
            this.hull = new ArrayList<Dot>(hull);
        }

        public boolean contains(final Dot dot) {
            return hull.contains(dot);
        }
    }

    ConvexHull getConvexHull(final Collection<Dot> dots);
}
