package t5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Prob3 {
	static void Submatrice(int[][] A, int m, int n, int k) throws FileNotFoundException {
		int i, j;
		int[][] S = new int[m][n];
		int d, mi = 0, mj = 0;
		for (i = 0; i < m; i++)
			S[i][0] = A[i][0];
		for (j = 0; j < n; j++)
			S[0][j] = A[0][j];
		for (i = 1; i < m; i++) {
			for (j = 1; j < n; j++) {
				if (A[i][j] == 0)
					S[i][j] = min(S[i][j - 1], S[i - 1][j], S[i - 1][j - 1]) + 1;
				else
					S[i][j] = 0;
			}
		}
		d = S[0][0];
		for (i = 0; i < m; i++) {
			for (j = 0; j < n; j++) {
				if (d < S[i][j]) {
					d = S[i][j];
					mi = i;
					mj = j;
				}
			}
		}
		int nr = 0;
		for (i = 0; i < m; i++) {
			for (j = 0; j < n; j++) {
				if (S[i][j] >= k)
					nr++;
			}
		}
		PrintWriter g = new PrintWriter("date.out");
		g.println(mi);
		g.println((mi - 1) + " " + (mj - 1));
		g.println(nr);
		g.close();
	}

	static int min(int x, int y, int z) {
		int min = x;
		if (min > y)
			min = y;
		if (min > z)
			min = z;
		return min;
	}

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(new File("date.in"));
		int n, m;
		m = sc.nextInt();
		n = sc.nextInt();
		int[][] a = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++)
				a[i][j] = sc.nextInt();
		}
		int k = sc.nextInt();
		sc.close();
		Submatrice(a, m, n, k);
	}
}
