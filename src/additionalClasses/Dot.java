package additionalClasses;

/**
 * Author: Balagurov Vladimir (privatejoker441@gmail.com)
 * Group: 1742
 * Date: 05/01/15 12:28
 */


public class Dot {
    private final double[] coordinates;
    private final int dimension;

    public Dot(final double[] coordinates) {
        this.coordinates = coordinates.clone();
        dimension = coordinates.length;
    }

    public double getCoordinate(final int i) {
        if (i >= dimension) {
            return Double.NaN;
        } else {
            return coordinates[i];
        }
    }

    public int getDimension() {
        return dimension;
    }

    public double getModulus() {
        double modulus = 0.0;
        for (final double i : coordinates) {
            modulus += i * i;
        }
        return Math.sqrt(modulus);
    }

    @Override
    public String toString() {
        String result = "(";
        for (final double coord : coordinates) {
            result += coord;
            result += "; ";
        }
        return result.substring(0, result.lastIndexOf(';')) + ')';
    }


}
