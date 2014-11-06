package part2.ass4;

import java.io.FileNotFoundException;

public class FloydWarshall4 {
	private Graph2 graph;
	private int n;
	private int[][] D;
	
	public FloydWarshall4(String filename) 
			throws FileNotFoundException {
		
		graph = new Graph2(filename);
		n = graph.n;
		D = new int[n][n];
		fillBound();
		
	}
	
	private void fillBound() {
		
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				D[i][j] = graph.W[i][j];
			}
		}
	}
	
	public void solve() {
		
		for(int k=0;k<n;k++){

			for(int i=0;i<n;i++){
				for(int j=0;j<n;j++){
					if (D[i][k] + D[k][j] < D[i][j])
						D[i][j] = D[i][k] + D[k][j];
				}
			}
		}
		shortest();
	}
	
	private void shortest() {
		
		int min = 0;
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if (min > D[i][j])
					min = D[i][j];
			}
		}
		System.out.println(min);
	}
	


	public static void main(String[] args) {

		try {
			FloydWarshall4 fw = new FloydWarshall4("data/ass4/g3.txt");
			fw.solve();
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}









