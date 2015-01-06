package blockTwo;

import additionalClasses.Dot;

/**
 * Author: Balagurov Vladimir (privatejoker441@gmail.com)
 * Group: 1742
 * Date: 06/01/15 18:18
 */


public class CrossingNumber implements PointInPolygon {
    @Override
    public boolean isPointInPolygon(final Dot point, final Polygon polygon) {
        /*
        Проведём 4 луча из точки параллельно осям
         */
        int xPositiveGets = 0;
        int xNegativeGets = 0;
        int yPositiveGets = 0;
        int yNegativeGets = 0;

        final double pointX = point.getCoordinate(0);
        final double pointY = point.getCoordinate(1);
        for (final Edge edge : polygon.getEdges()) {
            final double edgeMinX;
            final double edgeMaxX;
            if (edge.getPoint1().getCoordinate(0) >= edge.getPoint2().getCoordinate(0)) {
                edgeMinX = edge.getPoint2().getCoordinate(0);
                edgeMaxX = edge.getPoint1().getCoordinate(0);
            } else {
                edgeMinX = edge.getPoint1().getCoordinate(0);
                edgeMaxX = edge.getPoint2().getCoordinate(0);
            }
            final double edgeMinY;
            final double edgeMaxY;
            if (edge.getPoint1().getCoordinate(1) >= edge.getPoint2().getCoordinate(1)) {
                edgeMinY = edge.getPoint2().getCoordinate(1);
                edgeMaxY = edge.getPoint1().getCoordinate(1);
            } else {
                edgeMinY = edge.getPoint1().getCoordinate(1);
                edgeMaxY = edge.getPoint2().getCoordinate(1);
            }
            if (edgeMaxY > 0) {
                if ((pointX <= edgeMaxX) && (pointX > edgeMinX)) {
                    xPositiveGets++;
                }
            } else {
                if ((pointX <= edgeMaxX) && (pointX > edgeMinX)) {
                    xNegativeGets++;
                }
            }
            if (edgeMaxX > 0) {
                if ((pointY <= edgeMaxY) && (pointY > edgeMinY)) {
                    yPositiveGets++;
                }
            } else {
                if ((pointY <= edgeMaxY) && (pointY > edgeMinY)) {
                    yNegativeGets++;
                }
            }

        }
        if ((xPositiveGets%2==1)&&(xNegativeGets%2==1)&&(yPositiveGets%2==1)&&(yNegativeGets%2==1)){
            return true;
        }

        return false;
    }
}
