package part2.ass4_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import edu.princeton.cs.algs4.BellmanFordSP;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.Out;
import edu.princeton.cs.introcs.StdOut;


public class JohnsonPrep {
	
	String filename1;
	String filename2;
	String filename3;
	String filename4;
	int n;
	BellmanFordSP bf;
	
	public JohnsonPrep(String filename1, 
			String filename2, 
			String filename3,
			String filename4) {
		
		this.filename1 = filename1;
		this.filename2 = filename2;
		this.filename3 = filename3;
		this.filename4 = filename4;
		modifyFileAddZero();
		modifyFileReweight();
		savePToFile();
		
	}
	
	// add source 0 with 0 weight to ALL edges
	private void modifyFileAddZero() {
		
		try {
			Scanner in = new Scanner(new File(filename1));
			PrintWriter out = new PrintWriter(filename2);
			n = in.nextInt(); // excluding 0
			int m = in.nextInt();
			
			out.println((n+1) + " " + (m+n));
			for (int i = 1; i < n; i++) {
				out.println("0 " + i + " 0");
			}
			out.print("0 " + n + " 0");
			
			while(in.hasNextLine()) {
				out.println(in.nextLine());
			}
			in.close();
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	private void modifyFileReweight() {
		
		In in;
		in = new In(filename2);
		EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
		in.close();
		bf = new BellmanFordSP(G, 0);
		
		Out out = new Out(filename3);
		in = new In(filename1);
		int n = in.readInt();
		int m = in.readInt();
		out.println((n+1) + " " + m);
		
		
		int u, v;
		double w, wr;
		for (int i = 0; i < m; i++) {

			u = in.readInt();
			v = in.readInt();
			w = in.readDouble();
			
			wr = w + bf.distTo(u) - bf.distTo(v);
			out.println(u + " " + v + " " + wr);
		}
		in.close();
		out.close();
	}
	
	private void savePToFile() {
		
		Out out = new Out(filename4);
		out.println(n);
		for (int v = 1; v <= n; v++) {
			out.println(v + " " + bf.distTo(v));
			
		}
		out.close();
	}

	public static void main(String[] args) throws FileNotFoundException {


		JohnsonPrep j = new JohnsonPrep("data/ass4/large.txt", 
				"data/ass4/large1.txt", "data/ass4/large2.txt", "data/ass4/largep.txt");
		StdOut.println();
	}

}




















