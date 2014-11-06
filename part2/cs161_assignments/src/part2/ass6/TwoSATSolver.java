package part2.ass6;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.KosarajuSharirSCC;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdOut;



public class TwoSATSolver {
	
	private int nvar = 0;
	
	private void convert(String infile, String outfile) {
		
		try(Scanner in = new Scanner(new File(infile));  
				PrintWriter out = new PrintWriter(outfile)){
			
			nvar = in.nextInt(); 
			int n = nvar * 2 + 1;
			out.println(n);
			out.println(nvar * 2);
			
			while(in.hasNextInt()) {
				int a = in.nextInt();
				int b = in.nextInt();
				
				out.println(norm(-a, n) + " " + norm(b, n));
				out.println(norm(-b, n) + " " + norm(a, n));
			}
		}catch (Exception e) {
			System.out.println("error");
		}
	}
	
	private int norm(int a, int n) {
		
		return a > 0 ? a : a + n; 
	}

	public static void main(String[] args) {

		TwoSATSolver ts = new TwoSATSolver();
		int f = 6;
		ts.convert("data/twosat/2sat" + f + ".txt", 
				"data/twosat/2sat" + f + "DG.txt");
		
		StdOut.println("counting scc...");
        In in = new In("data/twosat/2sat" + f + "DG.txt");
        Digraph G = new Digraph(in);
        KosarajuSharirSCC scc = new KosarajuSharirSCC(G);

        // number of connected components
        int M = scc.count();
        StdOut.println(M + " components");
        
        StdOut.println("searching error...");
        for (int v = 1; v <= ts.nvar; v++) {
            if (scc.id(v) == scc.id(ts.nvar * 2 + 1 - v))
            	System.out.println("error " + v);
        }

        System.out.println("ok");
        
        
	}// end of main

}




























