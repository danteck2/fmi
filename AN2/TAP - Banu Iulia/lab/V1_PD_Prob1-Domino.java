package VAR1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class pd1 {
	int primul;
	int ultimul;
public static void main(String[] args) throws FileNotFoundException {
		
		Scanner sc=new Scanner(new File("E:\\JAVAEEworkspace\\EXAMEN\\src\\VAR1\\pd1.in"));
	 int n=sc.nextInt();
	 pd1 a[]=new pd1[n];
	 for(int i=0;i<n;i++)
		{
			a[i]=new pd1();
		
		}
	 for(int i=0;i<n;i++){
		 a[i].primul = sc.nextInt();
		 a[i].ultimul = sc.nextInt();
	 }
	 	int[][] ct= new int[n][2];
	 	for(int i=0;i<n;i++)
	 		ct[i][0]=0;
	 	for(int i=1;i<n;i++){
	 		for(int j=i;j>0;j--)
	 			if(a[i].primul==a[j].ultimul){
	 				ct[i][0]=1+ct[j][0];
	 				ct[i][1]=j;
	 				
	 			}
	 	}
	 	int max=0;
	 	int sr=0;
	 	int last=0;
	 	for(int i=0;i<n;i++){
	 		
	 		if(max<=ct[i][0]){ max=ct[i][0];
	 				sr=ct[i][1];
	 				last=i;
	 		}
	 		}
	 	System.out.println(a[last].primul+" "+a[last].ultimul);
	 	for(int j=0;j<max;j++){
	 		System.out.println(sr);
	 		System.out.println(a[sr].primul+" "+a[sr].ultimul);
	 		sr=ct[sr][1];
	 		
	 	}
	 			
	 		System.out.println(max);
}
}
