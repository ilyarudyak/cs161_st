package part2.ass4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;



public class Graph2 {
	
	public static final int INF = 10000000; 

	int[][] W; 
	int n;
	int m;

	public Graph2(String filename) throws FileNotFoundException{
		
		Scanner in = new Scanner(new File(filename));
		
		n = in.nextInt();
		m = in.nextInt();
		
		//+1 - because vertices are numbered from 1 to n (Not from 0)
		W = new int[n][n];
		
		int u, v, w;
		while(in.hasNextInt()){
			
			u = in.nextInt()-1;
			v = in.nextInt()-1;
			w = in.nextInt();

			W[u][v] = w;
		}
		
		in.close();
		addInfinity();
	}

	public void addInfinity(){

		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				if(i != j){
					if(W[i][j] == 0)
						W[i][j] = INF;
				}
			}
		}
	}


	public static void main(String[] args) {
		
		try {
			
			Graph2 g = new Graph2("data/ass4/t1.txt");
			for(int i = 0; i < g.n; i++){
				for(int j = 0; j < g.n; j++){
					if (g.W[i][j] == INF)
						System.out.print(" - ");
					else
						System.out.print(g.W[i][j] + " ");
				}
				System.out.println();
			}
			
		}
		catch (IOException e) {
			System.out.println("error");
		}

	}

}


















