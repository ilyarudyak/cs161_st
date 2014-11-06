package part2.ass3;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class KnapsackFast {
	
	int W; // maximum weight of knapsack
	int N; // number of items
	
	int[] profit;
    int[] weight;
	
	// max profit of packing items 1..n with weight limit w
    int[][] opt;
    
    public KnapsackFast(String filename) {
    	
    	try(Scanner in = new Scanner(new File(filename))) {
	    	
	    	W = in.nextInt();
	    	N = in.nextInt();
	    	in.nextLine();
	    	
	    	profit = new int[N+1];
	    	weight = new int[N+1];
	    	
	    	opt = new int[2][W+1];
	    	
	        for (int n = 1; n <= N; n++) {
	            profit[n] = in.nextInt();
	            weight[n] = in.nextInt();
	            if (n < N) in.nextLine();
	        }
    	}
    	catch(IOException e) {}
    	
    }
    
    public void solve() {
    	
    	int n0, n1;
        for (int n = 1; n <= N; n++) {
        	
        	if (n % 2 != 0) { n0 = 0; n1 = 1; } 
        	else { n0 = 1; n1 =0; }
        	
            for (int w = 1; w <= W; w++) {

                // don't take item n
                int option1 = opt[n0][w];

                // take item n
                int option2 = Integer.MIN_VALUE;
                if (weight[n] <= w) option2 = 
                		profit[n] + opt[n0][w-weight[n]];

                // select better of two options
                opt[n1][w] = Math.max(option1, option2);
            }
        }
    }

	public static void main(String[] args) {
		
		String filename = "data/knapsack/knapsack_big.txt";
		KnapsackFast k = new KnapsackFast(filename);
		k.solve();
		System.out.println(k.opt[0][k.W]);
//		System.out.println(k.profit[k.N] + " " + k.weight[k.N]);
	}

}








