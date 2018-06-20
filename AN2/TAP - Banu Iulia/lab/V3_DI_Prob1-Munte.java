package t4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Munte {
	 static int max(int i, int j, int v[])
	 {
	   int a, b, m;
	   if (i==j) return v[i];
	   else
	   {
	     m = (i+j)/2;
	     a = max(i, m, v);
	     b = max(m+1, j, v);
	     if  (a>b) return a;
	     else return b;
	   }
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
		g.println(max(0,n-1,a));
		g.close();
		f.close();
	}

}
