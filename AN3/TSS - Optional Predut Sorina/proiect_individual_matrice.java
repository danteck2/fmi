package tss.proiect;

public class App 
{
	public static void validateMatrix(int a[][], int n, int m, int x){
		if(n<=1 || m<=1){
			throw new IllegalArgumentException("Conditions not met.");
		}
		if(x<0){
			throw new IllegalArgumentException("Conditions not met.");
		}
    	for(int i=0;i<n;i++){
    		for(int j=0;j<m;j++){
    			if(a[i][j]<0){
    				throw new IllegalArgumentException("Conditions not met.");
    			}
    		}
    	}
	}
    public static boolean sumInMatrix(int a[][], int n, int m, int x){
    	
    	validateMatrix(a,n,m,x);
    	int sum = 0;
    	for(int i=0;i<n;i++){
    		for(int j=0;j<m;j++){
    			sum = sum + a[i][j];
    		}
    	}
    	if(sum==x){
			return true;
    	}
    	return false;
    }
}

///////////////////////////////////////////////////////////////////////
//--AppTest.java
package tss.proiect;
import static tss.proiect.App.sumInMatrix;
import static tss.proiect.App.validateMatrix;
import static org.junit.Assert.*;

import org.junit.Test;

public class AppTest {
	int A[][];
	void populate(int n, int m){
		A = new int[n][m];
		for(int i=0;i<n;i++){
			for(int j=0;j<m;j++){
				A[i][j]=i+1;
			}
		}
		
	}
	// 1.1 Testare functionala prin clase de echivalenta =============================================
	@Test
	public void testareFunctionalaC_1() {
		populate(2,2);
		
		//Case C_1
		assertTrue(sumInMatrix(A,2,2,6));
	}
	@Test
	public void testareFunctionalaC_2() {
		populate(2,2);
		assertFalse(sumInMatrix(A,2,2,0));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testareFunctionalaC_3() {
		populate(2,2);
		sumInMatrix(A,2,-2,6);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testareFunctionalaC_4() {
		populate(2,2);
		sumInMatrix(A,-2,2,6);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testareFunctionalaC_5() {
		populate(2,2);
		A[0][0] = -1;
		sumInMatrix(A,2,2,6);
	}
	
	//1.2 Testare functionala - Analiza valorilor de frontiera ========================================
	@Test(expected = IllegalArgumentException.class)
	public void testareFrontieraN_1(){
		populate(1,5);
		//Frontiera n=1
		assertTrue(sumInMatrix(A,1,5,1));
	}
	@Test
	public void testareFrontieraN_2(){
		//Frontiera n=2
		populate(2,5);
		assertTrue(sumInMatrix(A,2,5,15));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testareFrontieraM_1(){
		populate(5,1);
		//Frontiera m=1
		assertTrue(sumInMatrix(A,5,1,1));
	}
	@Test
	public void testareFrontieraM_2(){
		//Frontiera m=2
		populate(5,2);
		assertTrue(sumInMatrix(A,5,2,30));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testareFrontieraX_1(){
		populate(5,5);
		//Frontiera x=-1
		assertTrue(sumInMatrix(A,5,5,-1));
	}
	@Test
	public void testareFrontieraX_2(){
		//Frontiera x=0
		populate(5,5);
		assertTrue(sumInMatrix(A,5,5,75));
	}
	//1.3 Testare functionala - Partitionarea pe categorii ================================================
	@Test(expected = Test.None.class /* no exception expected */)
	public void testarePartitionareCategorii(){
		populate(2,2);
		validateMatrix(A, 2, 2, 1);
		validateMatrix(A, 2, 2, 5);
	}
	@Test(expected = IllegalArgumentException.class)
	public void testarePartitionareCategorii2(){
		populate(2,2);
		validateMatrix(A, 2, 2, -2);
		validateMatrix(A, 2, -2, 1);
		validateMatrix(A, -2, 2, 1);
		A[0][1]=-1;
		validateMatrix(A, 2, 2, 5);
	}
	@Test
	public void testarePartitionareCategorii3(){
		populate(2,2);
		assertTrue(sumInMatrix(A, 2, 2, 6));
		assertFalse(sumInMatrix(A, 2, 2, 5));
	}
	// 2. Testarea structurala
	// 2.1 Statement Coverage (instructiune)
	@Test(expected = IllegalArgumentException.class)
	public void sc_t13(){
		populate(2,2);
		sumInMatrix(A, -2, 2, 2);
		sumInMatrix(A, 2, 2, -1);
		A[0][1]=-1;
		sumInMatrix(A, 2, 2, 2);
	}
	@Test
	public void sc_t4(){
		populate(2,2);
		assertTrue(sumInMatrix(A, 2, 2, 6));
	}
	// 2.2 Branch coverage (ramura)
	@Test(expected = IllegalArgumentException.class)
	public void bc_t135(){
		populate(2,2);
		sumInMatrix(A, -2, -2, 2);
		sumInMatrix(A, 2, 2, -2);
		A[1][0]=-3;
		sumInMatrix(A, 2, 2, 2);
	}
	@Test
	public void bc_t2467(){
		populate(2,2);
		assertTrue(sumInMatrix(A, 2, 2, 6));
		assertFalse(sumInMatrix(A, 2, 2, 3));
		assertFalse(sumInMatrix(A, 2, 2, 4));
		assertFalse(sumInMatrix(A, 2, 2, 5));
	}
	// 2.3 Condition coverage
	@Test(expected = IllegalArgumentException.class)
	public void cc_t1234(){
		populate(2,2);
		sumInMatrix(A, -2, 2, 2);
		sumInMatrix(A, 2, -2, 2);
		sumInMatrix(A, 2, 2, -2);
		A[1][0]=-3;
		sumInMatrix(A, 2, 2, 2);
	}
	@Test
	public void cc_t56(){
		populate(2,2);
		assertTrue(sumInMatrix(A, 2, 2, 6));
		assertFalse(sumInMatrix(A, 2, 2, 5));
	}

}
