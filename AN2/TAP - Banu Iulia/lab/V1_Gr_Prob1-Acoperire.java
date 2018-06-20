package VAR1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class g1 implements Comparable<g1> {
	int s,d;
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new File("E:\\JAVAEEworkspace\\EXAMEN\\src\\VAR1\\g1.in"));
		int st=sc.nextInt(),dt=sc.nextInt();
		int n=sc.nextInt();
		int i;
		g1 a[]=new g1[n];
		g1 sol[]=new g1[n];
		
		for(i=0;i<n;i++)
		{
			a[i]=new g1();
			sol[i]=new g1();
		}
		for(i=0;i<n;i++)
		{
			a[i].s=sc.nextInt();
			a[i].d=sc.nextInt();
		}
		Arrays.sort(a);
	
		int s=st; i=0;
		int j = 0;
		while(s<dt && i<n-1)
		{ 
		while(a[i].s<=s)
		{
			i++; 
			if(i==n)
				break;
		}
		i--;
		//System.out.println(a[i].s + " " + a[i].d);
		sol[j]=a[i];
		j++;
		s=a[i].d;
		}
		if(s<dt)
			System.out.println("NU");
		else
		for(i=0;i<j;i++)
		{	
			System.out.println(sol[i].s + " " + sol[i].d);
		}
	}
	@Override
	public int compareTo(g1 other) {
		
		return this.s-other.s;
	}
}
