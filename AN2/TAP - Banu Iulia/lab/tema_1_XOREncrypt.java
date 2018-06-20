import java.io.*;
import java.util.Scanner;

public class XOREncrypt {
	public static void toCrypt(String Msg, String Key) {    
		try{
			File outFile = new File ("date.out");
			FileWriter fWriter = new FileWriter (outFile);
			PrintWriter pWriter = new PrintWriter (fWriter);
			
			String CTxt = "";
			 int xor;
			 char temp;
			 for(int i = 0; i<Msg.length(); i++) {
				 xor = Msg.charAt(i) ^ Key.charAt(i);
				 temp  = (char)xor;
				 CTxt = CTxt + temp;
			 }
			 //System.out.println(CTxt);
			
			for(int i=0; i<CTxt.length(); i++) 
				pWriter.print((int)CTxt.charAt(i)+" ");
			
			pWriter.close();
		}
		catch (IOException e) { //obligatoriu
			System.out.println("Fisier inexistent.");
	    }
	}
	public static void toDecrypt(String Key) {    
		int[] vector = new int[27];
		try{
			int i=0;
			Scanner sc1 = new Scanner(new java.io.File("date.out"));
			while(sc1.hasNextInt()==true) {
				vector[i]=sc1.nextInt();
				i++;
			}
			sc1.close();
		}
		catch(FileNotFoundException fnf){ //obligatoriu
			System.out.println("Fisier inexistent.");
		}
		try{
			String CTxt = "";
			String aux = "";
			for(int i=0; i<vector.length; i++) {
				aux = Character.toString((char) vector[i]);
				CTxt = CTxt + aux;
			}
			String outMsg = "";
			int xor;
			char temp;
			for(int i = 0; i<CTxt.length(); i++) {
				xor = CTxt.charAt(i) ^ Key.charAt(i);
				temp = (char)xor;
				outMsg  = outMsg + temp;
			}
			File outFile2 = new File ("date2.in");
			FileWriter fWriter2 = new FileWriter (outFile2);
			PrintWriter pWriter2 = new PrintWriter (fWriter2);
			pWriter2.println(outMsg);
			pWriter2.close();
		}
		catch (IOException e) { //obligatoriu
			System.out.println("Fisier inexistent.");
	    }
	}
	public static void main(String[] args) {
		String Msg = null;
		try{
			Scanner sc = new Scanner(new java.io.File("date.in"));
			Msg=sc.nextLine();
			sc.close();
		}
		catch(FileNotFoundException fnf){ //obligatoriu
			System.out.println("Fisier inexistent.");
		}
		 
		 String Key = "";
		 Scanner scanner = new Scanner(System.in);
         System.out.println("Enter key: ");
         Key=scanner.nextLine();
         scanner.close();
         for(int i=0; i<10; i++) {
        	 Key = Key.concat(Key);
         }
		 toCrypt(Msg, Key);
		 toDecrypt(Key);
		 
	}
}
