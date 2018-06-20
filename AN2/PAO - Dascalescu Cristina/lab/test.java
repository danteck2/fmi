import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class Problema1 {

	public static void main(String[] args) {
		int n = 0;
		Integer a = 10;
		Collection<Integer> intset = new ArrayList<Integer>();
		try{
			Scanner sc = new Scanner(new java.io.File("date.in"));
			n=sc.nextInt();
			for(int i=0; i<n; i++){
				intset.add(sc.nextInt());
			}
			sc.close();
		}
		catch(FileNotFoundException fnf){
			System.out.println("Fisier inexistent.");
	    }
		System.out.println(intset.stream().anyMatch(a::equals));
		System.out.println(intset.stream().reduce(0, (x,y) -> x+y));
		ArrayList<Integer> list = new ArrayList<Integer>();
		intset.stream().filter((intr) -> (intr % 2 == 0)).forEachOrdered((intr) -> {list.add(intr);});
		System.out.println(list.toString());
	}
}
