package VAR1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class pd2 {

	
public static void main(String[] args) throws FileNotFoundException {
		
		Scanner sc=new Scanner(new File("E:\\JAVAEEworkspace\\EXAMEN\\src\\VAR1\\pd2.in"));
	 int n=sc.nextInt();
	 int m=sc.nextInt();
	 int i;
	 int j;
	 int[][] a=new int[n][m];
	 int[][] temp=new int[n][m];
	 int[][] ct=new int[n][m];
	 for(i=0;i<n;i++)
		 for(j=0; j< m ;j++){
			 a[i][j]=sc.nextInt();
			temp[i][j]=a[i][j];
		 }
	 for(i=0;i<n;i++){
		 for(j=0; j< m ;j++){
			 if(i>0 && j>0){
			 if( j<m-1 && i<n-1 || j==m-1 && i==n-1 || j==m-1 || i==n-1){
				 int tp1;
		 tp1 = a[i][j]+a[i][j-1];
		 int tp2 = a[i][j]+a[i-1][j];
		 // 0 pentru stanga si 1 pentru sus
		 if(tp1>tp2){
			 a[i][j]=tp1;
			 ct[i][j]=0;
		 }
		 else {a[i][j]=tp2;
		 ct[i][j]=1;
		 }
			 }
			
			 }
			 
			 else if(j==0 && i>0) {
			a[i][j]+=a[i-1][j];
			ct[i][j]=1;
		}
			 else if(i==0 && j>0){
				 a[i][j]+=a[i][j-1];
				 ct[i][j]=0;
			 }
			
	 
		 }
	 }
	System.out.println(a[n-1][m-1]);
//	 for(i=0;i<n;i++){
//		for(j=0;j<m;j++)
//		System.out.print(a[i][j]);	
//	 System.out.println();
	i=n-1;
	j=m-1;
	int ok=0;
	 while(ok==0){
		 System.out.println(i+" "+j);
		 if(ct[i][j]==1) i--;
		 else if(ct[i][j]==0) j--;
		if(i==0 && j==0) ok=1;
	 }
	 System.out.println(i+" "+j);
	}
}
