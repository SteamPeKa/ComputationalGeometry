package blockTwo;

import additionalClasses.Dot;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Author: Balagurov Vladimir (privatejoker441@gmail.com)
 * Group: 1742
 * Date: 06/01/15 18:15
 */


public interface PointInPolygon {
    boolean isPointInPolygon(final Dot point, final Polygon polygon);


    class Edge {
        private final Dot point1;
        private final Dot point2;

        public Edge(final Dot point1, final Dot point2) {
            this.point1 = point1;
            this.point2 = point2;
        }

        public Dot getPoint1() {
            return point1;
        }

        public Dot getPoint2() {
            return point2;
        }
    }

    class Polygon {
        private final List<Edge> edges;

        public Polygon(List<Edge> edges) {
            this.edges = edges;
        }

        public Set<Dot> getVertexes() {
            final Set<Dot> result = new HashSet<Dot>();
            for (final Edge edge : edges) {
                result.add(edge.getPoint1());
                result.add(edge.getPoint2());
            }
            return result;
        }

        public List<Edge> getEdges() {
            return edges;
        }
    }
}
