package VAR1;
import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.*;
import java.util.*;

public class g2 implements Comparable<g2>{
		int limita;
		int durata;
		int id;
		int start;
		int intarziere;
		
	
		@Override
		public int compareTo(g2 x) {
			return this.limita-x.limita;
		}
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException{
		Scanner sc=new Scanner(new File("E:\\JAVAEEworkspace\\EXAMEN\\src\\VAR1\\g2.in"));
		PrintWriter pw=new PrintWriter("E:\\JAVAEEworkspace\\EXAMEN\\src\\VAR1\\g2.out","UTF-8");
	int n=sc.nextInt();
	g2[] a=new g2[n]; 
	for(int i=0;i<n;i++)
		a[i]= new g2();	
	for(int i=0;i<n;i++){
		a[i].durata = sc.nextInt();
		a[i].limita = sc.nextInt();
	}
	Arrays.sort(a);
	sc.close();
	int total=0;
	int i;
	for(i=0;i<a.length;i++)
	{
		a[i].start=total;
		total +=a[i].durata;
		a[i].intarziere=total - a[i].limita;
		if(a[i].intarziere<0)
			a[i].intarziere=0;
	}
	for(i=0;i<a.length;i++)
		pw.println("Activitatea:" + a[i].id +"  Intervalul "+ a[i].start +" - " 
		+(int)(a[i].start+a[i].durata) +"  Intarziere: "+ a[i].intarziere);
	pw.close();
	}

	
	}

