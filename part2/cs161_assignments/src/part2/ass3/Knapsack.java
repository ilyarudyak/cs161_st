package part2.ass3;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Knapsack {
	
	int W; // maximum weight of knapsack
	int N; // number of items
	
	int[] profit;
    int[] weight;
	
	// max profit of packing items 1..n with weight limit w
    int[][] opt;
    
    // does opt solution include item n?
    boolean[][] sol;
    
    public Knapsack(String filename) {
    	
    	try(Scanner in = new Scanner(new File(filename))) {
	    	
	    	W = in.nextInt();
	    	N = in.nextInt();
	    	in.nextLine();
	    	
	    	profit = new int[N+1];
	    	weight = new int[N+1];
	    	
	    	opt = new int[N+1][W+1];
	    	sol = new boolean[N+1][W+1];
	    	
	        for (int n = 1; n <= N; n++) {
	            profit[n] = in.nextInt();
	            weight[n] = in.nextInt();
	            if (n < N) in.nextLine();
	        }
    	}
    	catch(IOException e) {}
    	
    }
    
    public void solve() {
    	
        for (int n = 1; n <= N; n++) {
            for (int w = 1; w <= W; w++) {

                // don't take item n
                int option1 = opt[n-1][w];

                // take item n
                int option2 = Integer.MIN_VALUE;
                if (weight[n] <= w) option2 = 
                		profit[n] + opt[n-1][w-weight[n]];

                // select better of two options
                opt[n][w] = Math.max(option1, option2);
                sol[n][w] = (option2 > option1);
            }
        }
    }

	public static void main(String[] args) {
		
		String filename = "data/knapsack/knapsack.txt";
		Knapsack k = new Knapsack(filename);
		k.solve();
		System.out.println(k.opt[k.N][k.W]);
//		System.out.println(k.profit[k.N] + " " + k.weight[k.N]);
	}

}








