package part2.week3;

import java.util.Arrays;

public class IndependentSet {
	
	 int[] path = {1,2,3,4,5,6,7,8,9};
	 int[] memo = new int[path.length+1];
	 
	 int[] A = new int[path.length+1];
	 
	 
	 
	 public int solve() {
		 memo[0] = 0;
		 memo[1] = path[0];
		 return solve(path.length);
	 }
	 
	 private int solve(int i) {
		 
		 if (memo[i] != 0 || i == 0) return memo[i];
		 else {
			 memo[i] = Math.max(solve(i-1), solve(i-2) + path[i-1]); 
		 }
		 
		 return memo[i];
	 }
	 
	 public void solveIter() {
		 
		 A[0] = 0;
		 A[1] = path[0];
		 
		 for (int i = 2; i <= path.length; i++) {
			 A[i] = Math.max( A[i-1] , A[i-2] + path[i-1]);
		}
		 
	 }
	 
	 

	public static void main(String[] args) {

		IndependentSet is = new IndependentSet();
		is.solve();
		System.out.println("memo=" + Arrays.toString(is.memo));
		
		is.solveIter();
		System.out.println("A=" + Arrays.toString(is.A));
	}

}
