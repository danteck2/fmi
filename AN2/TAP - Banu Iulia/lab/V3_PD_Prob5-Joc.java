package t5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Prob5 {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		int n, i, si = 0, sp = 0, s1 = 0, s2 = 0, val = 0;
		Scanner keyboard = new Scanner(System.in);
		Scanner f = new Scanner(new File("date.in"));
		n = f.nextInt();
		int[] a = new int[n];
		for (i = 0; i < n; i++) {
			{
				a[i] = f.nextInt();
				System.out.print(a[i] + " ");
			}
			if ((i % 2 + 1) == 1)
				si += a[i];
			else
				sp += a[i];
		}
		System.out.println();
		f.close();
		int j = 0, k = n - 1;
		while (j <= k) {
			if (si > sp) {
				System.out.println("Calculatorul va castiga cu minim " + ((si + s1) - (sp + s2)) + " diferenta.");
				if ((j % 2 + 1) == (k % 2 + 1) && (j % 2 + 1) == 1) { // Pentru n impar
					if (a[j] > a[k]) {
						System.out.println("Primul jucator alege S (valoarea " + a[j] + ")");
						si -= a[j];
						s1 += a[j];
						j++;
					} else {
						System.out.println("Primul jucator alege D (valoarea " + a[k] + ")");
						si -= a[k];
						s1 += a[k];
						k--;
					}
				} else if ((j % 2 + 1) == 1) {
					System.out.println("Primul jucator alege S (valoarea " + a[j] + ")");
					si -= a[j];
					s1 += a[j];
					j++;
				} else {
					System.out.println("Primul jucator alege D (valoarea " + a[k] + ")");
					si -= a[k];
					s1 += a[k];
					k--;
				}
			} else {
				System.out.println("Calculatorul va castiga cu minim " + ((sp + s1) - (si + s2)) + " diferenta.");
				if ((j % 2 + 1) == 0) {
					System.out.println("Primul jucator alege S (valoarea " + a[j] + ")");
					sp -= a[j];
					s1 += a[j];
					j++;
				} else {
					System.out.println("Primul jucator alege D (valoarea " + a[k] + ")");
					sp -= a[k];
					s1 += a[k];
					k--;
				}
			}
			if (j < n && k > 0) {
				System.out.println("Puteti alege: S: " + a[j] + " sau D " + a[k] + ". (s/d)");
				char x = keyboard.next().charAt(0);
				if (x == 's' || x == 'S')
					val = 1;
				else if (x == 'd' || x == 'D')
					val = 0;
				if (val == 1) {
					if ((j % 2 + 1) == 1) {
						System.out.println("Al doilea jucator alege S (valoarea " + a[j] + ")");
						si -= a[j];
						s2 += a[j];
						j++;
					} else {
						System.out.println("Al doilea jucator alege S (valoarea " + a[j] + ")");
						sp -= a[j];
						s2 += a[j];
						j++;
					}
				} else if (val == 0) {
					if ((k % 2 + 1) == 0) {
						System.out.println("Al doilea jucator alege D (valoarea " + a[k] + ")");
						sp -= a[k];
						s2 += a[k];
						k--;
					} else {
						System.out.println("Al doilea jucator alege D (valoarea " + a[k] + ")");
						si -= a[k];
						s2 += a[k];
						k--;
					}
				}
			}
		}
		System.out.println("Primul jucator a castigat cu suma de: " + s1);
		System.out.println("Al doilea jucator a pierdut cu suma de: " + s2);
		keyboard.close();
	}

}
