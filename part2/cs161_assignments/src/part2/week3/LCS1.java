package part2.week3;

import java.util.Arrays;

public class LCS1 {
	
	String x = "polynomial";
	String y = "exponential";
	
	int m = x.length();
	int n = y.length();
	
	int[][] E;
	
	public LCS1() {
		
		E = new int[m + 1][n + 1];
		for (int i = 1; i <= m; i++) { E[i][0] = i; }
		for (int j = 1; j <= n; j++) { E[0][j] = j; }
		
	}
	
	public void solve() {
		
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				E[i][j] = min(E[i-1][j] + 1, E[i][j-1] + 1,
						E[i-1][j-1] + diff(i, j));
			}
		}
	}
	
	private int min(int a, int b, int c) {
		
		int[] xs = {a, b, c};
		Arrays.sort(xs);
		return xs[0];
	}
	
	private int diff(int i, int j) {
		
		if (x.charAt(i-1) == y.charAt(j-1))
			return 0;
		return 1;
	}
	
	public void backtrack() {
		
		int i = m;
		int j = n;
		String x0 = "";
		String y0 = "";
		while (i > 0 && j > 0) {
			
			
			if ( E[i-1][j-1] + diff(i, j) == 
					min(E[i-1][j] + 1, E[i][j-1] + 1,
						E[i-1][j-1] + diff(i, j)) ) {
				
				x0 = x.substring(i-1, i) + x0;
				y0 = y.substring(j-1, j) + y0;
				i--;
				j--;
			}
			else if ( E[i-1][j] + 1 == 
					min(E[i-1][j] + 1, E[i][j-1] + 1,
						E[i-1][j-1] + diff(i, j)) ) {
				x0 = x.substring(i-1, i) + x0;
				y0 = "_" + y0;
				i--;
			}
			else {
				x0 = "_" + x0;
				y0 = y.substring(j-1, j) + y0;
				j--;
			}

		}
		System.out.println(i + " " + j);
		for (int k = Math.max(i, j); k > 0; k--) {
			if (i == 0) {
				x0 = "_" + x0;
				y0 = y.substring(k-1, k) + y0;
			}
			else {
				x0 = x.substring(k-1, k) + x0;
				y0 = "_" + y0;
			}
		}
		
		System.out.println(x0);
		System.out.println(y0);
		
		
	}


	
	public static void main(String[] args) {

		LCS1 lcs = new LCS1();
		lcs.solve();
		
		for (int i = 0; i < lcs.E.length; i++) {
			System.out.println(Arrays.toString(lcs.E[i]));
		}
		
		lcs.backtrack();
		
	}

}














