package blockTwo;

import additionalClasses.Dot;

import java.util.Collection;

/**
 * Author: Balagurov Vladimir (privatejoker441@gmail.com)
 * Group: 1742
 * Date: 06/01/15 20:05
 */


public interface MinimumCoveringCircle {

    public Circle getMinimumCoveringCircle(final Collection<Dot> points);

    class Circle {
        private final Dot centre;
        private final double radius;

        public Circle(Dot centre, double radius) {
            this.centre = centre;
            this.radius = radius;
        }

        public Dot getCentre() {
            return centre;
        }

        public double getRadius() {
            return radius;
        }

        public boolean isIn(final Dot dot) {
            return vectorMinus(dot, centre).getModulus() <= radius;
        }

        private Dot vectorMinus(final Dot dot1, final Dot dot2) {
            double[] result = new double[dot1.getDimension()];

            for (int i = 0; i < dot1.getDimension(); i++) {
                result[i] = dot1.getCoordinate(i) - dot2.getCoordinate(i);
            }
            return new Dot(result);
        }

        @Override
        public String toString() {
            return '{' + centre.toString() + "; " + radius + '}';
        }
    }
}
