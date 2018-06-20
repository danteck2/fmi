package t4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

class Punct {
	int x;
	int y;

	public Punct(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int CompX(Punct p) {
		
		return this.x < p.x ? -1: this.x > p.x ? 1: 0;
	}
	public int CompY(Punct p) {
		
		return this.y < p.y ? -1: this.y > p.y ? 1: 0;
	}
}

public class DistantaPuncte {
	private static List<Punct> PuncteMinime = new ArrayList<Punct>();
	static float Distanta(Punct A, Punct B) {
		return (float) Math.sqrt((A.x - B.x) * (A.x - B.x) + (A.y - B.y) * (A.y - B.y));
	}

	static float bruteForce(List<Punct> A, int n, List<Punct> PuncteMinime) {
		float min = 9999999;
		for (int i = 0; i < n; ++i)
			for (int j = i + 1; j < n; ++j)
				if (Distanta(A.get(i), A.get(j)) < min) {
					min = Distanta(A.get(i), A.get(j));
					PuncteMinime.clear();
					PuncteMinime.add(A.get(i));
					PuncteMinime.add(A.get(j));
				}
		return min;
	}

	static float min(float x, float y) {
		return (x < y) ? x : y;
	}

	static float Banda(List<Punct> banda, int n, float x, List<Punct> PuncteMinime) {
		float min = x;
		for (int i = 0; i < n; ++i)
			for (int j = i + 1; j < n && (banda.get(j).y - banda.get(i).y) < min; ++j)
				if (Distanta(banda.get(i), banda.get(j)) < min) {
					min = Distanta(banda.get(i), banda.get(j));
					PuncteMinime.clear();
					PuncteMinime.add(banda.get(i));
					PuncteMinime.add(banda.get(j));
				}
		return min;
	}

	static float DMin(List<Punct> PlanX, List<Punct> PlanY, int n, List<Punct> PuncteMinime) {
		if (n <= 3)
			return bruteForce(PlanX, n, PuncteMinime);
		int m = n / 2;
		Punct mijloc = PlanX.get(m);
		List<Punct> PlanYS = new ArrayList<Punct>(m + 1);
		List<Punct> PlanYD = new ArrayList<Punct>(n - m - 1);
		for (int i = 0; i < n; i++) {
			if (PlanY.get(i).x <= mijloc.x) {
				PlanYS.add(PlanY.get(i));
			} else {
				PlanYD.add(PlanY.get(i));
			}
		}
		float dl = DMin(PlanX.subList(0, m), PlanYS, m, PuncteMinime);
		float dr = DMin(PlanX.subList(m, n), PlanYD, n - m - 1, PuncteMinime);
		float d = min(dl, dr);
		List<Punct> banda = new ArrayList<Punct>(n);
		int j = 0;
		for (int i = 0; i < n; i++)
			if (Math.abs(PlanY.get(i).x - mijloc.x) < d) {
				banda.add(PlanY.get(i));
				j++;
			}
		return min(d, Banda(banda, j, d, PuncteMinime));
	}

	static float Aprop(Punct[] A, int n, List<Punct> PuncteMinime) {
		List<Punct> PlanX = new ArrayList<Punct>();
		List<Punct> PlanY = new ArrayList<Punct>();
		for (int i = 0; i < n; i++) {
			PlanX.add(A[i]);
			PlanY.add(A[i]);
		}
		Collections.sort(PlanX,(p1, p2) -> p1.CompX(p2));
		Collections.sort(PlanY,(p1, p2) -> p1.CompY(p2));
		return DMin(PlanX, PlanY, n, PuncteMinime);
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner f = new Scanner(new File("date.in"));
		int n = f.nextInt();
		Punct[] plan=new Punct[n];
		for (int i = 0; i < n; i++) {
			plan[i] = new Punct(f.nextInt(),f.nextInt());
		}
		f.close();
		PrintWriter g=new PrintWriter("date.out");
		float dist=Aprop(plan, n, PuncteMinime);
		g.print("DistMin[");
		for(int i=0;i<PuncteMinime.size();i++)
		g.print("(" + PuncteMinime.get(i).x + ", " + PuncteMinime.get(i).y + ")");
		g.print("] = " + dist);
		g.close();
	}
}
