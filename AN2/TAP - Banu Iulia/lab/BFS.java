/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bfs;

/**
 *
 * @author Iolanda
 */
import java.util.Scanner;

public class BFS {

    /**
     * Plec dintr-un nod x care se adauga in coada si se marcheaza ca vizitat; se adauga in coada si se marcheaza
     * ca vizitati toti vecinii nevizitati ai nodului x; se trece la urmatorul nod din coada;
     * Algortimul se continua pana cand am expandat toate nodurile din coada
     * MATRICEA VECINILOR
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int n,m,x; //n=nr noduri  m=nr muchii  x=nodul din care pornesc
        int z,y;
        int [][]a;
        Scanner sc=new Scanner(System.in);
        System.out.println("n=");
        n=sc.nextInt();
        System.out.println("m=");
        m=sc.nextInt();
        System.out.println("x=");
        x=sc.nextInt();
        a=new int [n+1][n+1];
        for(int i=1;i<=n;i++)
            for(int j=1;j<=n;j++)
                a[i][j]=0;
        //citesc muchiile
        for(int i=1;i<=m;i++)
        {
            System.out.printf("Citeste muchia %d:",i);
            z=sc.nextInt();
            y=sc.nextInt();
            a[z][y]=1; a[y][z]=1;
        }
        int []c;
        int []viz;
        int p,u;
        c=new int [n+1];
        viz=new int [n+1];
        c[1]=x; viz[x]=1;
        p=1; u=1;
        while(p<=u)
        {
            x=c[p];
            p++;
            for(int i=1;i<=n;i++)
                if(a[x][i]==1&&viz[i]==0)
                {
                    u++;
                    c[u]=i;
                    viz[i]=1;
                }
        }
        for(int i=1;i<=n;i++)
            System.out.printf("%d ", c[i]);
    }
    
}
