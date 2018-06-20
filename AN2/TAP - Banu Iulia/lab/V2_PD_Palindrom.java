package tema_pd;

public class Palindrom {
	public int minPalindorm(String s) {
        if(s==null)
           return 0;
        int i,j,n=s.length();
        int taiet[]=new int[n];
        boolean dp[][]=new boolean[n][n]; 
       
       for(i=0;i<n;i++)
       {   
    	   taiet[i]=i; 
           for(j=0;j<=i;j++)
           { 
               if(j == i)
                  dp[j][i] = true;
               else
               {
                 if(s.charAt(i)!= s.charAt(j))
                 continue;
                 if(j==i-1)
                 
                 dp[j][i]=true;
                 else
                 dp[j][i]=dp[j+1][i-1] ;
               }
               
             if(dp[j][i])
             {
                 if(j==0)
                 taiet[i]=0;
                 else
                 taiet[i]=Math.min(taiet[j-1]+1,taiet[i]);  
                 }
             
             
           }
       }
       return taiet[n-1];
       
   }
}
