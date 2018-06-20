package t5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Prob4 {
	public static void pal(String p) {
		int n = p.length();
		@SuppressWarnings("unchecked")
		List<List<String>>[] a = new List[n + 1];
		a[0] = new ArrayList<List<String>>();
		a[0].add(new ArrayList<String>());
		boolean[][] coresp = new boolean[n][n];
		for (int i = 0; i < p.length(); i++) {
			a[i + 1] = new ArrayList<List<String>>();
			for (int s = 0; s <= i; s++) {
				if (p.charAt(s) == p.charAt(i) && (i - s <= 1 || coresp[s + 1][i - 1])) {
					coresp[s][i] = true;
					for (List<String> r : a[s]) {
						List<String> rez = new ArrayList<String>(r);
						rez.add(p.substring(s, i + 1));
						a[i + 1].add(rez);
					}
				}
			}
		}
		System.out.println(a[n]);
	}

	public static int mini(String p) {
		if (p.length() == 0)
			return 0;
		int[] v = new int[p.length() + 1];
		for (int i = 0; i < p.length(); i++)
			v[i] = Integer.MAX_VALUE;
		v[p.length()] = -1;
		for (int i = p.length() - 1; i >= 0; i--) {
			for (int a = i, b = i; a >= 0 && b < p.length() && p.charAt(a) == p.charAt(b); a--, b++)
				v[a] = Math.min(v[a], 1 + v[b + 1]);
			for (int a = i, b = i + 1; a >= 0 && b < p.length() && p.charAt(a) == p.charAt(b); a--, b++)
				v[a] = Math.min(v[a], 1 + v[b + 1]);
		}
		return v[0];
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new File("date.in"));
		String p;
		p = sc.next();
		sc.close();
		System.out.println("Palindromuri: ");
		pal(p);
		System.out.println("Numar minim de palindromuri: " + (mini(p)+1));
	}
}
