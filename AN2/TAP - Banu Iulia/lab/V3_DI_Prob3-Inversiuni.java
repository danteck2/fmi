package t4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class Inversiuni {
	static long merge(int[] a, int[] l, int[] r) {
	    int i = 0, j = 0, nr = 0;
	    while (i < l.length || j < r.length) {
	        if (i == l.length) {
	            a[i+j] = r[j];
	            j++;
	        } else if (j == r.length) {
	            a[i+j] = l[i];
	            i++;
	        } else if (l[i] <= 2*r[j]) {
	            a[i+j] = l[i];
	            i++;                
	        } else {
	            a[i+j] = r[j];
	            nr += l.length-i;
	            j++;
	        }
	    }
	    return nr;
	}

	static long invNr(int[] a) {
	    if (a.length < 2)
	        return 0;
	    int m = (a.length + 1) / 2;
	    int l[] = Arrays.copyOfRange(a, 0, m);
	    int r[] = Arrays.copyOfRange(a, m, a.length);

	    return invNr(l) + invNr(r) + merge(a, l, r);
	}
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner f=new Scanner(new File("date.in"));
		PrintWriter g=new PrintWriter("date.out");
		int n,i;
		n=f.nextInt();
		int [] a= new int[n];
		for(i=0;i<n;i++)
			a[i]=f.nextInt();
		g.print(invNr(a));
		g.close();
		f.close();
	}

}
