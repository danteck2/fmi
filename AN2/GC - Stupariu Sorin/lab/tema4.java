package g4;
import java.io.*;
import java.util.*;
public class T4 {

public static Puncte[] vector=new Puncte[5];
	
	public static double latura(Puncte a,Puncte b){
		return Math.sqrt( (a.getX()-b.getX())*(a.getX()-b.getX() ) + (a.getY()-b.getY())*(a.getY()-b.getY() )); 
	}

	public static double Unghi(Puncte a){
		//calculam vectorul aA1
		double x1=a.getX()-vector[1].getX();
		double y1=a.getY()-vector[1].getY();
		//calculam vectorul aA3
		double x2=a.getX()-vector[3].getX();
		double y2=a.getY()-vector[3].getY();
		//produsul scalar intre aA1 si aA3
		double scalar=x1*x2+y1*y2;
		//calculam modulul lui aA1
		double modul1=Math.sqrt(x1*x1 + y1*y1);
		//calculam modulul lui aA3
		double modul2=Math.sqrt(x2*x2 + y2*y2);
		double rezultat=scalar/(modul1*modul2);
		rezultat=Math.acos(rezultat);
		return Math.toDegrees(rezultat);
	}
	public static void main(String[] args) throws FileNotFoundException {
		Scanner	sc=new Scanner(new File("date.in"));	
		Formatter fr=new Formatter("date.out");
		for(int i=1;i<=4;i++)
		{
			double x=sc.nextDouble();
			double y=sc.nextDouble();
			Puncte a=new Puncte(x,y);
			vector[i]=a;
		}
		double a2=Unghi(vector[2]);
		double a4=Unghi(vector[4]);
//		System.out.println(a2);
//		System.out.println(a4);
		if(Math.abs(a2+a4-180)<0.001)
			fr.format("A4 este pe cercul circumscris triunghiului\n");
		else
			if(a2+a4<180)
				fr.format("A4 se afla in exteriorul cercului circumscris triunghiului\n");
			else
				fr.format("A4 se afla in interiorul cercului circumscris triunghiului\n");
		
		double l1=latura(vector[1],vector[2]);
		double l2=latura(vector[3],vector[4]);
		double l3=latura(vector[2],vector[3]);
		double l4=latura(vector[1],vector[4]);
		if(l1+l2==l3+l4) fr.format("Patrulaterul este circumscriptibil\n");
		else
			fr.format("Patrulaterul nu este circumscriptibil\n");
		sc.close();
		fr.close();
}
}

package g4;

public class Puncte {

	private double x;
	private double y;
	
	public Puncte(){
		x=0;
		y=0;
	}
	
	public Puncte(double x,double y){
		this.x=x;
		this.y=y;
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public void setX(double x){
		this.x=x;
	}
	
	public void setY(double y){
		this.y=y;
	}

}

