package VAR1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class di1 {
public static int divide(int p,int q,int v[])
{
	int m=(p+q)/2;
	if(v[m]==m)
		return m;
	if(q<p)
		return -1;
	if(v[m]>m)
		return divide(p,m,v);
	else 
		return divide(m+1,q,v);
	
}

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		
		Scanner sc=new Scanner(new File("E:\\JAVAEEworkspace\\EXAMEN\\src\\VAR1\\di1.in"));
	//	PrintWriter pw=new PrintWriter("E:\\JAVAEEworkspace\\EXAMEN\\src\\VAR1\\di1.out","UTF-8");
		int n=sc.nextInt();
		int[] a= new int[n];
		for(int i=0;i<n;i++)
			a[i]=sc.nextInt();
		int x=0,y=n;
		int nr=divide(x,y,a);
		System.out.println(nr);
		
	}
}
