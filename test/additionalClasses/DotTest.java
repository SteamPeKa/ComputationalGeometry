package additionalClasses;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;


/**
 * Author: Balagurov Vladimir (privatejoker441@gmail.com)
 * Group: 1742
 * Date: 05/01/15 12:30
 */


public class DotTest {
    @Test
    public void testImmutable() {
        final double[] outerCoordinates = new double[]{1.0, 2.0, 3.0};
        final Dot immutableDot = new Dot(outerCoordinates);
        assertEquals(1.0, immutableDot.getCoordinate(0));
        assertEquals(2.0, immutableDot.getCoordinate(1));
        assertEquals(3.0, immutableDot.getCoordinate(2));
        outerCoordinates[0] = 4.0;
        outerCoordinates[1] = 5.0;
        outerCoordinates[2] = 6.0;
        assertEquals(1.0, immutableDot.getCoordinate(0));
        assertEquals(2.0, immutableDot.getCoordinate(1));
        assertEquals(3.0, immutableDot.getCoordinate(2));

    }


}
