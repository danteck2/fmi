/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dfs;

import java.util.Scanner;

/**
 *
 * @author Iolanda
 */
public class DFS {
        static int []viz;
        static int [][]b;
        static int n;

    public static void dfs(int x)
    {
        System.out.printf("%d ", x);
         viz[x]=1;
        for(int i=1;i<=b[x][0];i++)
            if(viz[b[x][i]]==0)
                dfs(b[x][i]);
    }
    public static void main(String[] args) {
        // TODO code application logic here
        //int []viz;
       // int [][]b;
       // int n, m, x,y,z;
       int m,x,y,z;
        Scanner sc=new Scanner(System.in);
        System.out.println("n=");
        n=sc.nextInt();
        System.out.println("m=");
        m=sc.nextInt();
        System.out.println("x=");
        x=sc.nextInt();
        viz=new int[n+1];
        b=new int[n+1][n+1];
        for(int i=1;i<=n;i++)
            for(int j=0;j<=n;j++)
                b[i][j]=0;
        for(int i=1;i<=n;i++)
            viz[i]=0;
        for(int i=1;i<=m;i++)
        {
            System.out.printf("Citeste muchia %d:",i);
            y=sc.nextInt();
            z=sc.nextInt();
            b[z][0]++;
            b[z][b[z][0]]=y;
            b[y][0]++;
            b[y][b[y][0]]=z;
        }
        dfs(x);
    }
    
}
