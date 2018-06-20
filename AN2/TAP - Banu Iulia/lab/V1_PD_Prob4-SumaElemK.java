package VAR1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class pd4 {
	int[] array;
	int id;
public static void main(String[] args) throws FileNotFoundException{
		
		Scanner sc=new Scanner(new File("E:\\JAVAEEworkspace\\EXAMEN\\src\\VAR1\\pd3.in"));
			int n=sc.nextInt();
			int k=sc.nextInt();
			pd4[] a=new pd4[n];
			int i;
			String str;
			String[] splitstr;
			
			for(i=0;i<n;i++){
				str=sc.nextLine();
				splitstr=str.split(str);
				for(int j=0;j<splitstr.length;j++){
					a[i].array[j] =Integer.parseInt(splitstr[j]);
					
				}
			}
			
}
}
