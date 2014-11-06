package part2.ass4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;



public class Graph {

	int[][] W; 
	int n;
	int m;

	public Graph(String filename) throws FileNotFoundException{
		
		Scanner in = new Scanner(new File(filename));
		
		n = in.nextInt();
		m = in.nextInt();
		
		//+1 - because vertices are numbered from 1 to n (Not from 0)
		W = new int[n + 1][n + 1];
		
		int u, v, w;
		while(in.hasNextInt()){
			
			u = in.nextInt();
			v = in.nextInt();
			w = in.nextInt();

			W[u][v] = w;
		}
		
		in.close();
		addInfinity();
	}

	public void addInfinity(){

		for(int i = 1; i < n + 1; i++){
			for(int j = 1; j < n + 1; j++){
				if(i != j){
					if(W[i][j] == 0)
						W[i][j] = 1000000;
				}
			}
		}
	}


	public static void main(String[] args) {
		
		try {
			
			Graph g = new Graph("data/ass4/g1.txt");
			System.out.println("n=" + g.n + " m=" + g.m);
			System.out.println(g.W[1][14]);
			System.out.println(g.W[1000][1]);
			System.out.println(g.W[1000][988]);
			
		}
		catch (IOException e) {
			System.out.println("error");
		}

	}

}


















