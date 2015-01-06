package blockTwo;

import additionalClasses.Dot;
import org.junit.Test;

import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;

/**
 * Author: Balagurov Vladimir (privatejoker441@gmail.com)
 * Group: 1742
 * Date: 06/01/15 21:26
 */


public class MegiddoLinearAlgorithmTest {
    @Test
    public void test1() {
        final Dot dot1 = new Dot(new double[]{1, 1});
        final Dot dot2 = new Dot(new double[]{1, -1});
        final Dot dot3 = new Dot(new double[]{-1, 1});
        final MinimumCoveringCircle.Circle circle = new MegiddoLinearAlgorithm().getMinimumCoveringCircle(Arrays.asList(dot1, dot2, dot3));
        assertEquals(0.0, circle.getCentre().getCoordinate(0));
        assertEquals(0.0, circle.getCentre().getCoordinate(1));
        assertEquals(Math.sqrt(2), circle.getRadius(), 0.001);
    }

    @Test
    public void test2() {
        final Dot dot1 = new Dot(new double[]{1, 1});
        final Dot dot2 = new Dot(new double[]{1, -1});
        final Dot dot3 = new Dot(new double[]{-1, 1});
        final Dot dot4 = new Dot(new double[]{-1, -1});
        final MinimumCoveringCircle.Circle circle =
                new MegiddoLinearAlgorithm().getMinimumCoveringCircle(Arrays.asList(dot1, dot2, dot3, dot4));
        assertEquals(0.0, circle.getCentre().getCoordinate(0));
        assertEquals(0.0, circle.getCentre().getCoordinate(1));
        assertEquals(Math.sqrt(2), circle.getRadius(), 0.001);
    }

    @Test
    public void test3() {
        final Dot dot1 = new Dot(new double[]{1, 1});
        final Dot dot2 = new Dot(new double[]{1, -1});
        final Dot dot3 = new Dot(new double[]{-1, 1});
        final Dot dot4 = new Dot(new double[]{-1, -1});
        final Dot dot5 = new Dot(new double[]{0, 0});
        final Dot dot6 = new Dot(new double[]{0.5, 0.5});
        final Dot dot7 = new Dot(new double[]{0.5, -0.5});
        final Dot dot8 = new Dot(new double[]{-0.5, 0.5});
        final Dot dot9 = new Dot(new double[]{-0.5, -0.5});


        final MinimumCoveringCircle.Circle circle =
                new MegiddoLinearAlgorithm().getMinimumCoveringCircle(Arrays.asList(dot1, dot2, dot3, dot4, dot5, dot6, dot7, dot8, dot9));
        assertEquals(0.0, circle.getCentre().getCoordinate(0));
        assertEquals(0.0, circle.getCentre().getCoordinate(1));
        assertEquals(Math.sqrt(2), circle.getRadius(), 0.001);
    }
}
