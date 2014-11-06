package part2.ass4_1;

import edu.princeton.cs.algs4.DijkstraSP;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.introcs.In;

public class Johnson {
	
	String filename1;
	String filename2;
	EdgeWeightedDigraph G;
	int n; // 1..n
	double[] p;
	
	public Johnson(String filename1, String filename2) {
		
		this.filename1 = filename1;
		this.filename2 = filename2;
		
		In in = new In(filename1);
		G = new EdgeWeightedDigraph(in);
		in.close();
		
		fillP();
	}
	
	private double solve(int u) {
		
		DijkstraSP d = new DijkstraSP(G, u);
		
		double min = Double.POSITIVE_INFINITY;
		double tmp;
		for (int v = 2; v <= n; v++) {
				if (d.hasPathTo(v)) {
					tmp = d.distTo(v) - p[u] + p[v];
					if (tmp < min)
						min = tmp;
				}
			}
		return min;
	}
	
	
	private void fillP() {
		
		In in = new In(filename2);
		n = in.readInt();
		p = new double[n+1];
		for (int v = 1; v <= n; v++) {
			int tmp = in.readInt();
			p[v] = in.readDouble();
		}
		in.close();
		
	}

	public static void main(String[] args) {

		Johnson j = new Johnson("data/ass4/large2.txt", 
				"data/ass4/largep.txt");
		double min = j.solve(1);
		double tmp;
		for (int i = 2; i <= 1000; i++) {
			System.out.println(i);
			tmp = j.solve(i);
			if (tmp < min)
				min = tmp;
		}
		System.out.println(min);
		
	}

}







