package org.gfg.math.geometric;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.gfg.math.geometric.Geometry.*;

public class GeometryTest{
    @Test
    public void lineIntersectTest(){
        assertThat(linesIntersect(
            new Point(2, 1), new Point(3, 4), 
            new Point(1, 2), new Point(4, 3)), 
            is(true));

        assertThat(linesIntersect(
            new Point(2, 5), new Point(6, 7), 
            new Point(3, 1), new Point(4, 4)), 
            is(false));

        assertThat(linesIntersect(
            new Point(1, 1), new Point(2, 3), 
            new Point(2, 3), new Point(4, 8)), 
            is(true));

        assertThat(linesIntersect(
            new Point(2, 2), new Point(4, 4), 
            new Point(5, 5), new Point(7, 6)), 
            is(false));

        assertThat(linesIntersect(
            new Point(1, 1), new Point(3, 3), 
            new Point(2, 2), new Point(5, 5)), 
            is(true));

        assertThat(linesIntersect(
            new Point(1, 1), new Point(2, 2), 
            new Point(3, 3), new Point(5, 5)), 
            is(false));

        assertThat(linesIntersect(
            new Point(1, 1), new Point(6, 6), 
            new Point(2, 2), new Point(4, 4)), 
            is(true));

        assertThat(linesIntersect(
            new Point(1, 1), new Point(5, 5), 
            new Point(2, 5), new Point(8, 11)), 
            is(false));
    }

    @Test
    public void countPointsInCircleTest(){
        Point[] points = new Point[]{
            new Point(1, 1),
            new Point(2, 2),
            new Point(3, 3),
            new Point(-1, -1),
            new Point(4, 4)
        };
        Integer[] squareSum = Geometry.preprocessPoints(points);
        assertThat(Geometry.countPointsInsideCircle(squareSum, 3), is(3));
        assertThat(Geometry.countPointsInsideCircle(squareSum, 32), is(5));
    }
}