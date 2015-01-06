package blockTwo;

import additionalClasses.Dot;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Author: Balagurov Vladimir (privatejoker441@gmail.com)
 * Group: 1742
 * Date: 06/01/15 18:52
 */


public class CrossingNumberTest {
    @Test
    public void testSquare() {
        final Dot dot1 = new Dot(new double[]{1, 1});
        final Dot dot2 = new Dot(new double[]{1, -1});
        final Dot dot3 = new Dot(new double[]{-1, -1});
        final Dot dot4 = new Dot(new double[]{-1, 1});
        final List<PointInPolygon.Edge> edgeList = new ArrayList<PointInPolygon.Edge>();
        edgeList.add(new PointInPolygon.Edge(dot1, dot2));
        edgeList.add(new PointInPolygon.Edge(dot2, dot3));
        edgeList.add(new PointInPolygon.Edge(dot3, dot4));
        edgeList.add(new PointInPolygon.Edge(dot4, dot1));
        final PointInPolygon.Polygon square = new PointInPolygon.Polygon(edgeList);
        assertTrue(new CrossingNumber().isPointInPolygon(new Dot(new double[]{0, 0}), square));
        assertTrue(new CrossingNumber().isPointInPolygon(new Dot(new double[]{0.5, 0.5}), square));
        assertTrue(new CrossingNumber().isPointInPolygon(new Dot(new double[]{0.5, -0.5}), square));
        assertTrue(new CrossingNumber().isPointInPolygon(new Dot(new double[]{-0.5, -0.5}), square));
        assertTrue(new CrossingNumber().isPointInPolygon(new Dot(new double[]{-0.5, 0.5}), square));
    }

    @Test
    public void test() {
        final Dot dot1 = new Dot(new double[]{0, 2});
        final Dot dot2 = new Dot(new double[]{2, 1});
        final Dot dot3 = new Dot(new double[]{2, -1});
        final Dot dot4 = new Dot(new double[]{1, -2});
        final Dot dot5 = new Dot(new double[]{-1, -2});
        final Dot dot6 = new Dot(new double[]{-2, 0});
        final List<PointInPolygon.Edge> edgeList = new ArrayList<PointInPolygon.Edge>();
        edgeList.add(new PointInPolygon.Edge(dot2, dot1));
        edgeList.add(new PointInPolygon.Edge(dot2, dot3));
        edgeList.add(new PointInPolygon.Edge(dot4, dot3));
        edgeList.add(new PointInPolygon.Edge(dot4, dot5));
        edgeList.add(new PointInPolygon.Edge(dot6, dot5));
        edgeList.add(new PointInPolygon.Edge(dot6, dot1));
        final PointInPolygon.Polygon square = new PointInPolygon.Polygon(edgeList);
        assertTrue(new CrossingNumber().isPointInPolygon(new Dot(new double[]{0, 0}), square));
        assertTrue(new CrossingNumber().isPointInPolygon(new Dot(new double[]{0.5, 0.5}), square));
        assertTrue(new CrossingNumber().isPointInPolygon(new Dot(new double[]{0.5, -0.5}), square));
        assertTrue(new CrossingNumber().isPointInPolygon(new Dot(new double[]{-0.5, -0.5}), square));
        assertTrue(new CrossingNumber().isPointInPolygon(new Dot(new double[]{-0.5, 0.5}), square));
        assertFalse(new CrossingNumber().isPointInPolygon(new Dot(new double[]{-3, 3}), square));
        assertFalse(new CrossingNumber().isPointInPolygon(new Dot(new double[]{4, 0}), square));
    }
}
