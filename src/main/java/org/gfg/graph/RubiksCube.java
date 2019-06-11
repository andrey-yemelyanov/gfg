package org.gfg.graph;

import java.util.*;

/**
 * Solves 2x2x2 Rubik's cube. Based on MIT's implementation available at 
 * https://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-006-introduction-to-algorithms-fall-2011/assignments/MIT6_006F11_ps6.pdf
 */
public class RubiksCube{

    private static final int rgw = 0, flu = 0; // 0-th cubie, front face
    private static final int gwr = 1, luf = 1; // 0-th cubie, left face
    private static final int wrg = 2, ufl = 2; // 0-th cubie, up face

    private static final int rwb = 3, fur = 3; // (1-st cubie; front face)
    private static final int wbr = 4, urf = 4; // (1-st cubie; up face)
    private static final int brw = 5, rfu = 5; // (1-st cubie; right face)

    private static final int ryg = 6, fdl = 6; // (2-nd cubie; front face)
    private static final int ygr = 7, dlf = 7; // (2-nd cubie; down face)
    private static final int gry = 8, lfd = 8; // (2-nd cubie; left face)

    private static final int rby = 9, frd = 9; //  (3-rd cubie; front face)
    private static final int byr = 10, rdf = 10; // (3-rd cubie; right face)
    private static final int yrb = 11, dfr = 11; // (3-rd cubie; down face)

    private static final int owg = 12, bul = 12; // (4-th cubie; back face)
    private static final int wgo = 13, ulb = 13; // (4-th cubie; up face)
    private static final int gow = 14, lbu = 14; // (4-th cubie; left face)

    private static final int obw = 15, bru = 15; // (5-th cubie; back face)
    private static final int bwo = 16, rub = 16; // (5-th cubie; right face)
    private static final int wob = 17, ubr = 17; // (5-th cubie; up face)

    private static final int ogy = 18, bld = 18; // (6-th cubie; back face)
    private static final int gyo = 19, ldb = 19; // (6-th cubie; left face)
    private static final int yog = 20, dbl = 20; // (6-th cubie; down face)

    private static final int oyb = 21, bdr = 21; // (7-th cubie; back face)
    private static final int ybo = 22, drb = 22; // (7-th cubie; down face)
    private static final int boy = 23, rbd = 23; // (7-th cubie; right face)

    /**
     * Permutation of faces that corresponds to a solved 2x2x2 Rubik's cube.
     */
    public static final int[] SOLVED = new int[] {
        flu, luf, ufl, 
        fur, urf, rfu, 
        fdl, dlf, lfd, 
        frd, rdf, dfr, 
        bul, ulb, lbu, 
        bru, rub, ubr, 
        bld, ldb, dbl, 
        bdr, drb, rbd
    };

    /**
     * Permutation corresponding to a clockwise rotation of the front face.
     */
    public static final int[] FRONT_FACE_ROTATED_CLOCKWISE = new int[]{
        fdl, dlf, lfd, 
        flu, luf, ufl, 
        frd, rdf, dfr, 
        fur, urf, rfu,
        bul, ulb, lbu, 
        bru, rub, ubr, 
        bld, ldb, dbl, 
        bdr, drb, rbd
    };

    /**
     * Permutation corresponding to a counter-clockwise rotation of the front face.
     */
    public static final int[] FRONT_FACE_ROTATED_COUNTER_CLOCKWISE = new int[]{

    };

    /**
     * Permutation corresponding to a clockwise rotation of the left face.
     */
    public static final int[] LEFT_FACE_ROTATED_CLOCKWISE = new int[]{
        ulb, lbu, bul, 
        fur, urf, rfu, 
        ufl, flu, luf, 
        frd, rdf, dfr,
        dbl, bld, ldb, 
        bru, rub, ubr, 
        dlf, lfd, fdl, 
        bdr, drb, rbd
    };

    /**
     * Permutation corresponding to a counter-clockwise rotation of the left face.
     */
    public static final int[] LEFT_FACE_ROTATED_COUNTER_CLOCKWISE = new int[]{

    };

    /**
     * Permutation corresponding to a clockwise rotation of the upper face.
     */
    public static final int[] UPPER_FACE_ROTATED_CLOCKWISE = new int[]{
        rfu, fur, urf, 
        rub, ubr, bru, 
        fdl, dlf, lfd, 
        frd, rdf, dfr,
        luf, ufl, flu, 
        lbu, bul, ulb, 
        bld, ldb, dbl, 
        bdr, drb, rbd
    };

    /**
     * Permutation corresponding to a counter-clockwise rotation of the upper face.
     */
    public static final int[] UPPER_FACE_ROTATED_COUNTER_CLOCKWISE = new int[]{

    };

    /**
     * Contains a list of permutations representing 6 possible moves from a given cube configuration.
     */
    public static final int[][] MOVES = new int[][]{
        FRONT_FACE_ROTATED_CLOCKWISE,
        FRONT_FACE_ROTATED_COUNTER_CLOCKWISE,
        LEFT_FACE_ROTATED_CLOCKWISE,
        LEFT_FACE_ROTATED_COUNTER_CLOCKWISE,
        UPPER_FACE_ROTATED_CLOCKWISE,
        UPPER_FACE_ROTATED_COUNTER_CLOCKWISE
    };

    /**
     * Performs a single move on the cube that takes it from one configuration to another.
     * @param move move to perform
     * @param currentConfiguration current cube configuration
     * @return a new cube configuration after applying the move
     */
    public static int[] performMove(int[] move, int[] currentConfiguration){
        return applyPermutation(move, currentConfiguration);
    }

    private static int[] applyPermutation(int[] permutation, int[] array){
        int[] newArray = new int[array.length];
        for(int i = 0; i < permutation.length; i++){
            newArray[i] = array[permutation[i]];
        }
        return newArray;
    }

    /**
     * Using BFS finds the shortest path from a given start position to a solved position.
     * @param start starting cube configuration
     * @return a list of moves that solves the cube from {@code start} position to solved position
     */
    public static List<int[]> solve(int[] start){
        return solve(start, SOLVED);
    }

    static List<int[]> solve(int[] start, int[] end){
        List<int[]> moves = new ArrayList<>();
        moves.add(SOLVED);
        return moves;
    }
}