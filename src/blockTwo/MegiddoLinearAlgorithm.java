package blockTwo;

import additionalClasses.Dot;
import org.junit.Test;

import java.util.*;

import static junit.framework.TestCase.assertEquals;

/**
 * Author: Balagurov Vladimir (privatejoker441@gmail.com)
 * Group: 1742
 * Date: 06/01/15 20:10
 */


public class MegiddoLinearAlgorithm implements MinimumCoveringCircle {

    @Override
    public Circle getMinimumCoveringCircle(Collection<Dot> points) {
        for (final Dot i : points) {
            if (i.getDimension() != 2) {
                throw new IllegalArgumentException("Не все точки двумерны");
            }
        }
        if (points.size() == 0) {
            return null;
        }
        if (points.size() == 1) {
            return new Circle(new ArrayList<Dot>(points).get(0), 0);
        }
        if (points.size() == 2) {
            final Dot dot1 = new ArrayList<Dot>(points).get(0);
            final Dot dot2 = new ArrayList<Dot>(points).get(1);

            final double radius = 0.5 * vectorMinus(dot1, dot2).getModulus();
            final Dot centre = multiplyByScalar(vectorPlus(dot1, dot2), 0.5);
            return new Circle(centre, radius);
        }
        if (points.size() == 3) {
            final Dot dot1 = new ArrayList<Dot>(points).get(0);
            final Dot dot2 = new ArrayList<Dot>(points).get(1);
            final Dot dot3 = new ArrayList<Dot>(points).get(2);
            return circumscribedCircleOfTriangle(dot1, dot2, dot3);
        }
        final List<Dot> permutation = new ArrayList<Dot>(points);
        Collections.shuffle(permutation);
        final Iterator<Dot> iterator = permutation.iterator();
        final List<Dot> innerDots = new ArrayList<Dot>();
        final Dot initialDot1 = iterator.next();
        innerDots.add(initialDot1);
        final Dot initialDot2 = iterator.next();
        innerDots.add(initialDot2);
        Circle resultCircle = getMinimumCoveringCircle(Arrays.asList(initialDot1, initialDot2));
        while (iterator.hasNext()) {
            final Dot currentDot = iterator.next();
            if (resultCircle.isIn(currentDot)) {
                innerDots.add(currentDot);
            } else {
                resultCircle = minCircleBySetAndAPoint(innerDots, currentDot);
            }
        }
        return resultCircle;
    }

    private Circle minCircleBySetAndAPoint(final Collection<Dot> points, final Dot dot1) {
        final List<Dot> permutation = new ArrayList<Dot>(points);
        Collections.shuffle(permutation);
        final Iterator<Dot> iterator = permutation.iterator();
        final Dot initialDot = iterator.next();
        Circle resultCircle = getMinimumCoveringCircle(Arrays.asList(dot1, initialDot));
        final List<Dot> innerPoints = new ArrayList<Dot>();
        innerPoints.add(initialDot);
        while (iterator.hasNext()) {
            final Dot currentDot = iterator.next();
            if (resultCircle.isIn(currentDot)) {
                innerPoints.add(currentDot);
            } else {
                resultCircle = minCircleBySetAndTwoPoints(innerPoints, currentDot, dot1);
            }
        }
        return resultCircle;
    }

    private Circle minCircleBySetAndTwoPoints(final Collection<Dot> points, final Dot dot1, final Dot dot2) {
        final Collection<Dot> initialDots = Arrays.asList(dot1, dot2);
        Circle resultCircle = getMinimumCoveringCircle(initialDots);
        for (final Dot dot : points) {
            if (!resultCircle.isIn(dot)) {
                resultCircle = circumscribedCircleOfTriangle(dot, dot1, dot2);
            }
        }
        return resultCircle;
    }

    private Circle circumscribedCircleOfTriangle(final Dot A, final Dot B, final Dot C) {
        final double a = vectorMinus(B, C).getModulus();
        final double b = vectorMinus(A, C).getModulus();
        final double c = vectorMinus(A, B).getModulus();
        final double p = (a + b + c) / 2.0;
        final double radius = (a * b * c) / (4 * Math.sqrt(p * (p - a) * (p - b) * (p - c)));
        final double S = (a * b * c) / (4 * radius);
        final double alpha = ((a * a) / (8 * S * S)) * (scalarMultiply(vectorMinus(A, B), vectorMinus(A, C)));
        final double betta = ((b * b) / (8 * S * S)) * (scalarMultiply(vectorMinus(B, A), vectorMinus(B, C)));
        final double gamma = ((c * c) / (8 * S * S)) * (scalarMultiply(vectorMinus(C, A), vectorMinus(C, B)));
        final Dot centre = vectorPlus(vectorPlus(multiplyByScalar(A, alpha), multiplyByScalar(B, betta)), multiplyByScalar(C, gamma));
        return new Circle(centre, radius);

    }

    private double scalarMultiply(final Dot dot1, final Dot dot2) {
        double result = 0;

        for (int i = 0; i < dot1.getDimension(); i++) {
            result += dot1.getCoordinate(i) * dot2.getCoordinate(i);
        }
        return result;
    }

    private Dot vectorMinus(final Dot dot1, final Dot dot2) {
        double[] result = new double[dot1.getDimension()];

        for (int i = 0; i < dot1.getDimension(); i++) {
            result[i] = dot1.getCoordinate(i) - dot2.getCoordinate(i);
        }
        return new Dot(result);
    }

    private Dot vectorPlus(final Dot dot1, final Dot dot2) {
        double[] result = new double[dot1.getDimension()];

        for (int i = 0; i < dot1.getDimension(); i++) {
            result[i] = dot1.getCoordinate(i) + dot2.getCoordinate(i);
        }
        return new Dot(result);
    }

    private Dot multiplyByScalar(final Dot vector, final double scalar) {
        double[] result = new double[vector.getDimension()];

        for (int i = 0; i < vector.getDimension(); i++) {
            result[i] = vector.getCoordinate(i) * scalar;
        }
        return new Dot(result);

    }

    @Test
    public void testCircle() {
        final Dot dot1 = new Dot(new double[]{1, 1});
        final Dot dot2 = new Dot(new double[]{1, -1});
        final Dot dot3 = new Dot(new double[]{-1, 1});
        final Circle circle = circumscribedCircleOfTriangle(dot1, dot2, dot3);
        assertEquals(0.0, circle.getCentre().getCoordinate(0));
        assertEquals(0.0, circle.getCentre().getCoordinate(1));
        assertEquals(Math.sqrt(2), circle.getRadius(), 0.001);
    }


}
