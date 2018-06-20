import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

class Cursuri implements Comparable<Cursuri> {
	int inceput, sfarsit;

	Cursuri(int x, int y) {
		inceput = x;
		sfarsit = y;
	}

	@Override
	public int compareTo(Cursuri x) {
		// TODO Auto-generated method stub
		if (this.inceput - x.inceput != 0)
			return this.inceput - x.inceput;
		else
			return this.sfarsit - x.sfarsit;
	}

	public void afisare() {
		System.out.print("["+inceput + ", " + sfarsit+"] ");
	}
}

public class Intervale {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		int n, i, j = 0;
		Scanner f = new Scanner(new File("date.in"));
		n = f.nextInt();
		Cursuri[] c = new Cursuri[n];
		for (i = 0; i < n; i++)
			c[i] = new Cursuri(f.nextInt(), f.nextInt());
		f.close();
		Arrays.sort(c);
		CopyOnWriteArrayList<CopyOnWriteArrayList<Cursuri>> sali = new CopyOnWriteArrayList<CopyOnWriteArrayList<Cursuri>>();
		for (i = 0; i < n; i++) {
			j = 0;
			if (sali.size() == 0) {
				CopyOnWriteArrayList<Cursuri> SalaNoua = new CopyOnWriteArrayList<Cursuri>();
				sali.add(SalaNoua);
				sali.get(i).add(c[i]);
			} else {
				while (j < sali.size()) {
					if (c[i].inceput >= sali.get(j).get(sali.get(j).size() - 1).sfarsit) {
						sali.get(j).add(c[i]);
						j = sali.size();
					} else if (c[i].inceput < sali.get(j).get(sali.get(j).size() - 1).sfarsit) {
						CopyOnWriteArrayList<Cursuri> SalaNoua = new CopyOnWriteArrayList<Cursuri>();
						sali.add(SalaNoua);
						sali.get(j + 1).add(c[i]);
						j = sali.size();
					}
					j++;
				}
			}
		}
		PrintWriter g = new PrintWriter("date.out", "UTF-8");
		g.print("Numar minim de intervale: " + sali.size());
		g.close();
		/*
		for (i = 0; i < sali.size(); i++) {
			System.out.print("Sala"+(i+1)+": ");
			for (j = 0; j < sali.get(i).size(); j++)
				sali.get(i).get(j).afisare();
			System.out.println();
		}
		*/
	}

}
