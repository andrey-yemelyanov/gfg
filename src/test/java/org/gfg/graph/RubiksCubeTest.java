package org.gfg.graph;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;
import java.util.*;

public class RubiksCubeTest{
    @Test
    public void fourRotations(){
        int[] start = RubiksCube.SOLVED;
        int[] end = RubiksCube.performMove(RubiksCube.FRONT_FACE_ROTATED_CLOCKWISE, start);
        end = RubiksCube.performMove(RubiksCube.FRONT_FACE_ROTATED_CLOCKWISE, end);
        end = RubiksCube.performMove(RubiksCube.FRONT_FACE_ROTATED_CLOCKWISE, end);
        end = RubiksCube.performMove(RubiksCube.FRONT_FACE_ROTATED_CLOCKWISE, end);
        assertThat(end, is(RubiksCube.SOLVED));
    }

    @Test
    public void testShortestPath0(){
        int[] start = RubiksCube.SOLVED;
        int[] end = RubiksCube.SOLVED;
        assertThat(RubiksCube.solve(start, end).size(), is(0));
    }

    @Test
    public void testShortestPath1(){
        int[] start = RubiksCube.SOLVED;
        int[] end = RubiksCube.performMove(
            RubiksCube.FRONT_FACE_ROTATED_CLOCKWISE, 
            start);
        List<int[]> solution = RubiksCube.solve(start, end);
        assertThat(solution.size(), is(1));
        assertThat(solution.get(0), is(RubiksCube.FRONT_FACE_ROTATED_CLOCKWISE));
    }

    @Test
    public void testShortestPath2(){
        int[] start = RubiksCube.SOLVED;
        int[] middle = RubiksCube.performMove(
            RubiksCube.FRONT_FACE_ROTATED_CLOCKWISE, start);
        int[] end = RubiksCube.performMove(
            RubiksCube.LEFT_FACE_ROTATED_CLOCKWISE, middle);
        List<int[]> solution = RubiksCube.solve(start, end);
        assertThat(solution.size(), is(2));
        assertThat(solution.get(0), is(RubiksCube.FRONT_FACE_ROTATED_CLOCKWISE));
        assertThat(solution.get(1), is(RubiksCube.LEFT_FACE_ROTATED_CLOCKWISE));
    }

    @Test
    public void testShortestPath3(){
        int[] start = RubiksCube.SOLVED;
        int[] middle1 = RubiksCube.performMove(
            RubiksCube.FRONT_FACE_ROTATED_CLOCKWISE, start);
        int[] middle2 = RubiksCube.performMove(
            RubiksCube.FRONT_FACE_ROTATED_CLOCKWISE, middle1);
        int[] end = RubiksCube.performMove(
            RubiksCube.LEFT_FACE_ROTATED_COUNTER_CLOCKWISE, middle2);
        List<int[]> solution = RubiksCube.solve(start, end);
        assertThat(solution.size(), is(3));
        assertThat(solution.get(0), is(RubiksCube.FRONT_FACE_ROTATED_CLOCKWISE));
        assertThat(solution.get(1), is(RubiksCube.FRONT_FACE_ROTATED_CLOCKWISE));
        assertThat(solution.get(2), is(RubiksCube.LEFT_FACE_ROTATED_COUNTER_CLOCKWISE));
    }

    @Test
    public void testShortestPath4(){
        int[] start = RubiksCube.SOLVED;
        int[] middle1 = RubiksCube.performMove(
            RubiksCube.FRONT_FACE_ROTATED_CLOCKWISE, start);
        int[] middle2 = RubiksCube.performMove(
            RubiksCube.LEFT_FACE_ROTATED_CLOCKWISE, middle1);
        int[] middle3 = RubiksCube.performMove(
            RubiksCube.FRONT_FACE_ROTATED_CLOCKWISE, middle2);
        int[] end = RubiksCube.performMove(
            RubiksCube.LEFT_FACE_ROTATED_COUNTER_CLOCKWISE, middle3);
        List<int[]> solution = RubiksCube.solve(start, end);
        assertThat(solution.size(), is(4));
        assertThat(solution.get(0), is(RubiksCube.FRONT_FACE_ROTATED_CLOCKWISE));
        assertThat(solution.get(1), is(RubiksCube.LEFT_FACE_ROTATED_CLOCKWISE));
        assertThat(solution.get(2), is(RubiksCube.FRONT_FACE_ROTATED_CLOCKWISE));
        assertThat(solution.get(3), is(RubiksCube.LEFT_FACE_ROTATED_CLOCKWISE));
    }

    @Test
    public void testShortestPath14(){
        int[] start = new int[]{
            6, 7, 8, 
            20, 18, 19, 
            3, 4, 5, 
            16, 17, 15, 
            0, 1, 2, 
            14, 12, 13, 
            10, 11, 9, 
            21, 22, 23};
        List<int[]> solution = RubiksCube.solve(start);
        assertThat(solution.size(), is(14));

        int[] current = start;
        for(int[] move : solution){
            current = RubiksCube.performMove(move, current);
        }
        assertThat(current, is(RubiksCube.SOLVED));
    }

    @Test
    public void testShortestPathBad(){
        int[] start = new int[]{
            7, 8, 6, 
            20, 18, 19, 
            3, 4, 5, 
            16, 17, 15, 
            0, 1, 2, 
            14, 12, 13, 
            10, 11, 9, 
            21, 22, 23};
        int[] end = RubiksCube.SOLVED;
        List<int[]> solution = RubiksCube.solve(start);
        assertThat(solution, is(nullValue()));
    }
}