package org.gfg.math.geometric;

import org.gfg.search.SearchAlgorithms;
import org.gfg.sort.Sorting;

/**
 * Utility class that implements a number of geometry-related algorithms.
 */
public class Geometry{

    public static class Point{
        public int x;
        public int y;
        public Point(int x, int y){
            this.x = x; this.y = y;
        }
        @Override
        public String toString(){
            return String.format("(%d, %d)", x, y);
        }
    }

    /**
     * Returns orientation of three points.
     * @return <0 if counter-clockwise, 0 if collinear, >0 if clockwise
     */
    private static int orientation(Point p1, Point p2, Point p3){
        int result = (p2.y - p1.y) * (p3.x - p2.x) 
                   - (p3.y - p2.y) * (p2.x - p1.x);
        if(result < 0) return -1;
        if(result > 0) return 1;
        return 0;
    }

    private static boolean yProjectionsIntersect(Point p1, Point q1, Point p2, Point q2){
        return (p2.y >= p1.y && p2.y <= q1.y) || (q2.y >= p1.y && q2.y <= q1.y)
            || (p1.y >= p2.y && p1.y <= q2.y) || (q1.y >= p2.y && q1.y <= q2.y);
    }

    private static boolean xProjectionsIntersect(Point p1, Point q1, Point p2, Point q2){
        return (p2.x >= p1.x && p2.x <= q1.x) || (q2.x >= p1.x && q2.x <= q1.x)
            || (p1.x >= p2.x && p1.x <= q2.x) || (q1.x >= p2.x && q1.x <= q2.x);
    }

    /**
     * Checks if two line segments intersect.
     * @param p1 first point on the first line segment
     * @param q1 second point on the first line segment
     * @param p2 first point on the second line segment
     * @param q2 second point on the second line segment
     * @return true if the two line segments intersect
     */
    public static boolean linesIntersect(Point p1, Point q1, Point p2, Point q2){
        // see http://www.dcs.gla.ac.uk/~pat/52233/slides/Geometry1x1.pdf for details
        int o1 = orientation(p1, q1, p2);
        int o2 = orientation(p1, q1, q2);
        int o3 = orientation(p2, q2, p1);
        int o4 = orientation(p2, q2, q1);

        // general case
        if(o1 != o2 && o3 != o4) return true;

        // special case: both segments lie on the same line and their x and y projections intersect
        return (o1 == 0 && o2 == 0 && o3 == 0 && o4 == 0 
            && xProjectionsIntersect(p1, q1, p2, q2) 
            && yProjectionsIntersect(p1, q1, p2, q2));
    }

    /**
     * Computes how many input points lie inside a circle with a given radius.
     * @param squareSum a preprocessed array containing sums x^2+y^2 for each point in sorted order
     * @param circleRadius circle radius to compute for
     * @return number of points that lie inside the circle
     */
    public static int countPointsInsideCircle(Integer[] squareSum, int circleRadius){
        int floorIndex = SearchAlgorithms.floor(squareSum, circleRadius * circleRadius);
        return floorIndex + 1;
    }

    public static Integer[] preprocessPoints(Point[] points){
        Integer[] squareSum = new Integer[points.length];
        for(int i = 0; i < points.length; i++){
            Point p = points[i];
            squareSum[i] = p.x * p.x + p.y * p.y;
        }
        Sorting.mergeSort(squareSum);
        return squareSum;
    }
}