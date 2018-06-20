import java.io.File;
import java.util.concurrent.CopyOnWriteArrayList;
import java.io.FileNotFoundException;
import java.util.*;

class Nod<T> {
	private CopyOnWriteArrayList<Nod<T>> copil = new CopyOnWriteArrayList<Nod<T>>();
	private Nod<T> parinte = null;
	private T info = null;

	public Nod(T info) {
		this.info = info;
	}

	public T getInfo() {
		return this.info;
	}

	public CopyOnWriteArrayList<Nod<T>> getCopil() {
		return copil;
	}

	public void setParinte(Nod<T> parinte) {
		this.parinte = parinte;
	}

	public void setCopil(T info) {
		Nod<T> child = new Nod<T>(info);
		child.setParinte(this);
		this.copil.add(child);
	}

	public boolean Frunza() {
		if (this.copil.size() == 0)
			return true;
		else
			return false;
	}

	public void adaug(T parinte, T info) {
		int i;
		if (this.getInfo() == parinte)
			this.setCopil(info);
		else {

			for (i = 0; i < this.getCopil().size(); i++)
				this.getCopil().get(i).adaug(parinte, info);
		}
	}

	public void parc(Nod<T> nod) {
		if (nod == null) {
			return;
		}

		for (Nod<T> each : nod.getCopil()) {
			nod.parc(each);
		}

		if (nod.Frunza()) {
			System.out.print(nod.getInfo() + " ");
			if (nod.parinte != null) {
				if (nod.parinte.parinte != null) {

					nod.parinte.parinte.copil.remove(nod.parinte);
				}
			}
			nod = null;
		}
	}
}

public class Arbore<T> {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(new File("date.in"));
		int n;
		n = sc.nextInt();
		Nod<Integer> rad = new Nod<Integer>(sc.nextInt());
		rad.setCopil(sc.nextInt());
		while (sc.hasNext())
			rad.adaug(sc.nextInt(), sc.nextInt());
		sc.close();
		System.out.print("Pentru arborele format din cele " + n + " varfuri a rezultat multimea de cardinal maxim: {");
		rad.parc(rad);
		System.out.print("}");
	}
}