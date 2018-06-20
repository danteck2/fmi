//1. Distanta intre documente
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class dist2 {
	
	public static void main(String[] args) {
		String F1 = null;
		String F2 = null;
		try{
			Scanner sc = new Scanner(new java.io.File("F1"));
			F1=sc.nextLine();
			sc.close();
		}
		catch(FileNotFoundException fnf){ //obligatoriu
			System.out.println("Fisier inexistent.");
	    }
		try{
			Scanner sc = new Scanner(new java.io.File("F2"));
			F2=sc.nextLine();
			sc.close();
		}
		catch(FileNotFoundException fnf){ //obligatoriu
			System.out.println("Fisier inexistent.");
	    }
		String[] F1part = F1.split(" ");
		String[] F2part = F2.split(" ");
		Map<String, Integer> hashmap = new HashMap<String, Integer>();
		Map<String, Integer> hashmap1 = new HashMap<String, Integer>();
		Map<String, Integer> hashmap2 = new HashMap<String, Integer>();
		for(int i=0; i<F1part.length; i++) {
			if(!hashmap.containsKey(F1part[i]))
				hashmap.put(F1part[i], new Integer(1));
			else {
				int value = hashmap.get(F1part[i]);
				hashmap.put(F1part[i], value+1);
			}
		}
		for(int i=0; i<F2part.length; i++) {
			if(!hashmap.containsKey(F2part[i]))
				hashmap.put(F2part[i], new Integer(1));
			else {
				int value1 = hashmap.get(F2part[i]);
				hashmap.put(F2part[i], value1+1);
			}
		}
		for(int i=0; i<F1part.length; i++) {
			if(!hashmap1.containsKey(F1part[i]))
				hashmap1.put(F1part[i], new Integer(1));
			else {
				int value = hashmap1.get(F1part[i]);
				hashmap1.put(F1part[i], value+1);
			}
		}
		for(int i=0; i<F2part.length; i++) {
			if(!hashmap2.containsKey(F2part[i]))
				hashmap2.put(F2part[i], new Integer(1));
			else {
				int value1 = hashmap2.get(F2part[i]);
				hashmap2.put(F2part[i], value1+1);
			}
		}
		Integer prod = 0;
		Double r1 = 0.0, r2  = 0.0;
		Set<Map.Entry<String, Integer>> set = hashmap.entrySet();

		for (Map.Entry<String, Integer> me : set) {
			if(hashmap1.containsKey(me.getKey()) && hashmap2.containsKey(me.getKey()))
				prod = prod + (hashmap1.get(me.getKey())*hashmap2.get(me.getKey()));
			if(hashmap1.containsKey(me.getKey()))
				r1 = r1 + (hashmap1.get(me.getKey())*hashmap1.get(me.getKey()));
			if(hashmap2.containsKey(me.getKey()))
				r2 = r2 + (hashmap2.get(me.getKey())*hashmap2.get(me.getKey()));
		}
		r1 = Math.sqrt(r1);
		r2 = Math.sqrt(r2);
		Double dcos;
		dcos = prod /(r1*r2);
		System.out.println("dcos="+dcos);
	}
}
