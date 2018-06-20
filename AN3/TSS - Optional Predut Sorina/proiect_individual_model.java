package perfectsquares;

import java.util.Scanner;

public class PerfectSquares {

    public static int solve(int n, int[] a, int low, int high) {
        int s = 0;
        if(n < 1 || n > 100 || a == null || low < 0 || low >= n || high < 0 || high >= n) {
            System.out.println("Conditions not met.");
            return -1;
        }
        for(int i = 0; i < n; ++i) {
            if(a[i] < 0) {
                System.out.println("Conditions not met.");
                return -1;
            }
        }
        for(int i = low; i <= high; ++i) {
            if(isPerfectSquare(a[i])) {
                System.out.println(a[i]);
                s += a[i];
            }
        }
        return s;
    }

    public static boolean isPerfectSquare(int x) {
        return x >= 0 && Math.sqrt(x) * Math.sqrt(x) == x;
    }

//    public static void main(String[]args) {
//        int n = 5;
//        int[] a = {3, 4, 5, 8, 9};
//        int low, high;
//
//        Scanner sc = new Scanner(System.in);
//        low = sc.nextInt();
//        high = sc.nextInt();
//        PerfectSquares.solve(n, a, low, high);
//
//    }

}

//////////////////////////////////////////////////////////////////////////
//---PerfectSquaresTest.java
package perfectsquaretests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.*;
import perfectsquares.PerfectSquares;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class PerfectSquaresTest {


    /**
     * Functional Testing
     */

    @Test
    public void equivalencePartitioning() {
        int[] a = {3, 4, 5, 8, 9};
        int n = 5;
        assertEquals(13, PerfectSquares.solve(n, a, 1, 4));
        assertEquals(-1, PerfectSquares.solve(n, a, 3, -1));
        assertEquals(-1, PerfectSquares.solve(n, a, 2, 101));
        assertEquals(-1, PerfectSquares.solve(n, a, -1, -1));
        assertEquals(-1, PerfectSquares.solve(n, a, -2, 101));
        assertEquals(-1, PerfectSquares.solve(n, a, 101, 101));

        a[1] = -4;
        assertEquals(-1, PerfectSquares.solve(n, a, 2, 3));
        assertEquals(-1, PerfectSquares.solve(0, null, 2, 3));
        assertEquals(-1, PerfectSquares.solve(101, null, 2, 3));

    }

    @Test
    public void boundaryValueAnalysis() {
        int[] a = {4};
        assertEquals(-1, PerfectSquares.solve(1, a, -1, -1));
        assertEquals(-1, PerfectSquares.solve(1, a, -1, 0));
        assertEquals(-1, PerfectSquares.solve(1, a, -1, 1));
        assertEquals(-1, PerfectSquares.solve(1, a, 0, -1));
        assertEquals(4, PerfectSquares.solve(1, a, 0, 0));
        assertEquals(-1, PerfectSquares.solve(1, a, 0, 1));
        assertEquals(-1, PerfectSquares.solve(1, a, 1, 0));
        assertEquals(-1, PerfectSquares.solve(0, null, 0, 0));
        assertEquals(-1, PerfectSquares.solve(101, null, 0, 0));
    }

    @Test
    public void categoryPartitioning() {
        assertTrue(PerfectSquares.isPerfectSquare(4));
        assertTrue(PerfectSquares.isPerfectSquare(1));
        assertTrue(PerfectSquares.isPerfectSquare(0));
        assertFalse(PerfectSquares.isPerfectSquare(3));
        assertFalse(PerfectSquares.isPerfectSquare(-1));
        assertFalse(PerfectSquares.isPerfectSquare(8));

        int a[] = {4};
        assertEquals(-1, PerfectSquares.solve(-1, null, 0, 0));
        assertEquals(-1, PerfectSquares.solve(0, null, 0, 0));
        assertEquals(4, PerfectSquares.solve(1, a, 0, 0));
        assertEquals(-1, PerfectSquares.solve(1, a, -1, 0));
        assertEquals(-1, PerfectSquares.solve(1, a, 3, 0));
        assertEquals(-1, PerfectSquares.solve(1, a, 0, -1));

        int[]b = {3, 4, 5, 8, 9};
        assertEquals(9, PerfectSquares.solve(5, b, 3, 4));
        assertEquals(-1, PerfectSquares.solve(5, b, 3, -1));
        assertEquals(-1, PerfectSquares.solve(5, b, 3, 5));
        assertEquals(-1, PerfectSquares.solve(5, b, 3, 6));

        int[]c = new int[100];
        Arrays.fill(c, 1);
        assertEquals(100, PerfectSquares.solve(100, c, 0, 99));
        assertEquals(-1, PerfectSquares.solve(101, c, 0, 99));
        assertEquals(-1, PerfectSquares.solve(100, c, 0, 101));
        assertEquals(1, PerfectSquares.solve(100, c, 99, 99));

        int[]d = {3, -4, -9};
        assertEquals(-1, PerfectSquares.solve(3, d, 0, 0));

        int[]e = new int[100];
        Arrays.fill(e, -1);
        assertEquals(-1, PerfectSquares.solve(100, e, 0, 0));
    }


    /**
     * Structural testing
     */
    @Test
    public void statementCoverage() {
        assertEquals(-1, PerfectSquares.solve(0, null, 0, 0));

        int[]a = {4};
        assertEquals(-1, PerfectSquares.solve(1, a, -1, 0));
        assertEquals(-1, PerfectSquares.solve(1, a, 0, 5));

        int[]b = {3, 4, -5, -4, 8, 9};
        assertEquals(-1, PerfectSquares.solve(6, b, 0, 4));

        int[]c = {3, 4, 5, 8, 9};
        assertEquals(13, PerfectSquares.solve(5, c, 0, 4));
        assertEquals(0, PerfectSquares.solve(5, c, 0, 0));
    }


    @Test
    public void branchCoverage() {
        assertEquals(-1, PerfectSquares.solve(0, null, 0, 0));

        int[]a = {1, 3, 5, 4, 9};
        assertEquals(-1, PerfectSquares.solve(5, a, 6, 3));
        assertEquals(-1, PerfectSquares.solve(5, a, 2, -2));
        assertEquals(-1, PerfectSquares.solve(5, a, 2, 10));
        assertEquals(1, PerfectSquares.solve(5, a, 0, 1));

        int[]b = {1, -1, -4, 9, 3};
        assertEquals(-1, PerfectSquares.solve(5, b, 2, 4));

        int[]c = {4};
        assertEquals(4, PerfectSquares.solve(1, c, 0, 0));

        int[]d = {3};
        assertEquals(0, PerfectSquares.solve(1, d, 0, 0));
    }

    @Test
    public void conditionCoverage() {
        assertEquals(-1, PerfectSquares.solve(0, null, 0, 0));
        assertEquals(-1, PerfectSquares.solve(101, null, 0, 0));

        int[] a = {1, 2, 3};
        assertEquals(-1, PerfectSquares.solve(3, a, -5, 0));
        assertEquals(-1, PerfectSquares.solve(3, a, 10, 2));
        assertEquals(-1, PerfectSquares.solve(3, a, 2, -5));
        assertEquals(-1, PerfectSquares.solve(3, a, 2, 10));

        int[] b = {-1};
        assertEquals(-1, PerfectSquares.solve(0, b, 0, 0));

        int[] c = {2};
        assertEquals(0, PerfectSquares.solve(1, c, 0, 0));

        int[] d = {4};
        assertEquals(4, PerfectSquares.solve(1, d, 0, 0));

        int[] e = {1, 4, 9};
        assertEquals(13, PerfectSquares.solve(3, e, 1, 2));
    }

    /**
     * Acoperirea la nivel de cale
     */
    @Test
    public void pathCoverage() {
        assertEquals(-1, PerfectSquares.solve(0, null, 0, 0));

        int[]a = {-1, 1, 0};
        assertEquals(-1, PerfectSquares.solve(3, a, 0, 1));

        int[]b = {2, 3, 5};
        assertEquals(0, PerfectSquares.solve(3, b, 0, 1));

        int[]c = {2, 3, 4};
        assertEquals(4, PerfectSquares.solve(3, c, 0, 2));
    }


    @Test
    public void solveTest() {
        int[]a = {4};

        assertEquals(-1, PerfectSquares.solve(0, null, 0, 0));
        assertEquals(4, PerfectSquares.solve(1, a, 0, 0));
        assertEquals(-1, PerfectSquares.solve(101, null, 0, 0));

        int[]b = new int[100];
        Arrays.fill(b, 2);
        assertEquals(0, PerfectSquares.solve(100, b, 0, 99));

        int[]c = {1, 2, 3, 4, 5};
        assertEquals(-1, PerfectSquares.solve(5, c, -1, 1));
        assertEquals(5, PerfectSquares.solve(5, c, 0, 4));
        assertEquals(-1, PerfectSquares.solve(5, c, 5, 0));
        assertEquals(-1, PerfectSquares.solve(5, c, 6, 0));
        assertEquals(-1, PerfectSquares.solve(5, c, 2, -1));
        assertEquals(1, PerfectSquares.solve(5, c, 0, 0));
        assertEquals(1, PerfectSquares.solve(5, c, 0, 0));
        assertEquals(0, PerfectSquares.solve(5, c, 2, 0));
        assertEquals(4, PerfectSquares.solve(5, c, 2, 4));
        assertEquals(-1, PerfectSquares.solve(5, c, 2, 5));
        assertEquals(-1, PerfectSquares.solve(5, c, 2, 6));

        int[]d = {0, 1, 2};
        assertEquals(0, PerfectSquares.solve(1, d, 0, 0));
        assertEquals(-1, PerfectSquares.solve(1, d, 2, 3));


    }


}

