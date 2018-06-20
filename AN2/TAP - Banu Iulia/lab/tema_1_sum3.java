package tema1;
import java.io.*;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
//import java.util.Locale;
import java.io.FileNotFoundException;

class Muchie{
	int vi,vf;
	double cost;
	Muchie(int vi,int vf, double cost){
	this.vi=vi;
	this.vf=vf;
	this.cost=cost;
	}
	public String toString(){
	return "("+vi+","+vf+")"+" "+cost;
	}
}

class WrongNumberOfFields extends Exception {
	WrongNumberOfFields(int x, int y){
		super(x + "fields, reg " + y);
	}
}

public class sum3 {

	static void readCSV() throws WrongNumberOfFields{
		int nTokensLine1=0, nTokens=0;
		if(nTokensLine1 != nTokens)
			throw new WrongNumberOfFields(nTokensLine1, nTokens);
		
	}
	
	public static void main(String[] args) {
        try(FileInputStream fin=new FileInputStream("C://date//date.in"))
        {    
            int i=-1;
            while((i=fin.read())!=-1){
             
                System.out.print((char)i);
            }   
        }
        catch(IOException ex){
             
            System.out.println(ex.getMessage());
        } 
        
        String s1 = "xyz";
        String s1CpyLiteral = "xyz";
        String s1CpyNew = new String("xyz");
        if(s1==s1CpyLiteral)
        	System.out.println("\n s1 egal s1CpyLiteral");
        if(s1==s1CpyNew)
        	System.out.println("s1 egal s1CpyNew");
        String s4 = s1CpyNew.intern();
        if(s4==s1CpyNew)
        	System.out.println("s4 egal s1CpyNew");
        
        int i,n;
        Muchie muchii[];
        try{
       Scanner scFisier=new Scanner(new java.io.File("date.in"));
       //scFisier.useLocale(Locale.ENGLISH);
       n=scFisier.nextInt();
       muchii=new Muchie[n];
       for(i=0;i<n;i++)
        muchii[i]=new Muchie(scFisier.nextInt(), scFisier.nextInt(),
        scFisier.nextDouble());
        scFisier.close();
        for(i=0;i<n;i++)
        System.out.println(muchii[i]);
        }
        catch(FileNotFoundException fnf){ //obligatoriu
        System.out.println("Fisier inexistent.");
        }
        catch(InputMismatchException im){ //optional
		 System.out.println("Date de tip incorect.");
		 }
		 catch(NoSuchElementException nse){ // optional
		 System.out.println("Nu exista informatii despre toate muchiile.");
		 }

	//in loc de scaner (caracter cu caracter)
        try(Reader r = new FileReader("cifre.in")){
        int c = r.read();
        char ch = (char)c;
        while(c!=-1){
        	c=r.read();
        	ch=(char)c;
        	System.out.print((char)ch);
        }
        }
        catch(IOException ey){
            
            System.out.println(ey.getMessage());
        } 
	
	
	
	}
}

