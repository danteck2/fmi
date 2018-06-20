package t5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Prob1 {
	public static void main(String[] args) throws FileNotFoundException {
		HashMap<String, Integer> p = new HashMap<String, Integer>();
		Scanner f = new Scanner(new File("date.in"));
		String s = f.next();
		int max = 0, j=0;
		String aux;
		ArrayList<String> l = new ArrayList<String>();
		p.put(s.substring(s.length() - 2), 0);
		l.add(s);
		while (f.hasNext()) {
			s = f.next();
			if (p.containsKey(s.substring(0, 2))) {
				int i = (int) p.get(s.substring(0, 2));
				aux = l.get(i);
				aux = aux + " " + s;
				l.set(i, aux);
				p.remove(s.substring(0, 2));
				p.put(s.substring((s.length() - 2)), i);
			} else {
				p.put(s.substring(s.length() - 2), l.size());
				l.add(s);
			}
		}
		f.close();
		for (int i = 0; i < l.size(); i++) {
			if (max < l.get(i).length()) {
				max = l.get(i).length();
				j = i;
			}
		}
		PrintWriter g = new PrintWriter("date.out");
		g.println(l.get(j));
		g.close();
	}
}
