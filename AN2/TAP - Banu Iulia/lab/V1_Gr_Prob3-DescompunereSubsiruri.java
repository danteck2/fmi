package VAR1;
import java.io.*;
import java.util.*;
public class g3 {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		
		Scanner sc=new Scanner(new File("E:\\JAVAEEworkspace\\EXAMEN\\src\\VAR1\\g3.in"));
		PrintWriter pw=new PrintWriter("E:\\JAVAEEworkspace\\EXAMEN\\src\\VAR1\\g3.out","UTF-8");
		int n=sc.nextInt();
		ArrayList<Integer> a=new ArrayList<Integer>();
		for(int i=0;i<n;i++){
			a.add(sc.nextInt());
		}
	
	ArrayList<ArrayList<Integer>> sub=new ArrayList<ArrayList<Integer>>();
	ArrayList<Integer> e= new ArrayList<Integer>();
	sub.add(e);
	sub.get(0).add(a.get(0));
	 for(int i=1;i<n;i++){
		 int ok=0;
	for(int j=0;j<sub.size();j++){
		if(a.get(i)<=sub.get(j).get(sub.get(j).size()-1)){
			sub.get(j).add(a.get(i));
		ok=1;
		break;
			}
		}
	if(ok==0) {
		ArrayList<Integer> x= new ArrayList<Integer>();
		sub.add(x);
		sub.get(sub.size()-1).add(a.get(i));

	}
 	}
	 sc.close();
	 for(int i=0;i<sub.size();i++)
	 pw.println(sub.get(i));
	 pw.close();
}
}
