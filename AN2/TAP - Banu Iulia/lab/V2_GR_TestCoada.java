
public class TestCoada {

	public static void main(String[] args) {
		Coada<Integer> coadaIntregi = new Coada<Integer>();
		coadaIntregi.add(5);
		coadaIntregi.add(10);
		coadaIntregi.add(11);
		coadaIntregi.add(12);
		coadaIntregi.add(13);
		coadaIntregi.add(15);
		coadaIntregi.remove();
		coadaIntregi.remove();
		coadaIntregi.remove();
		System.out.println(coadaIntregi.toString());
		Coada<String> coadaSirCaractere = new Coada<String>();
		coadaSirCaractere.add("unul");
		coadaSirCaractere.add("doi");
		coadaSirCaractere.add("trei");
		coadaSirCaractere.remove();
		System.out.println(coadaSirCaractere.toString());
	}
}
