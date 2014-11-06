package part2.ass4;

import java.io.FileNotFoundException;

public class FloydWarshall3 {
	private Graph graph;
	private int n;
	private int[][] D;
	
	public FloydWarshall3(String filename) 
			throws FileNotFoundException {
		
		graph = new Graph(filename);
		n = graph.n;
		D = new int[n+1][n+1];
		fillBound();
		
	}
	
	private void fillBound() {
		
		for(int i=1;i<n+1;i++){
			for(int j=1;j<n+1;j++){
				D[i][j] = graph.W[i][j];
			}
		}
	}
	
	public void solve() {
		
		for(int k=0;k<n;k++){

			for(int i=1;i<n+1;i++){
				for(int j=1;j<n+1;j++){
					
					
					D[i][j] = Math.min(D[i][j], 
										   D[i][k] + D[k][j]);
					
					if (i == j) {
						if (D[i][j] < 0) {
							System.out.println("negative cycle");
							return;
						}
					}
				}
			}
		}
		shortest();
	}
	
	private void shortest() {
		
		int min = 0;
		for(int i=1;i<n+1;i++){
			for(int j=1;j<n+1;j++){
//				if (i != j) 
				min = Math.min(min, D[i][j]);
			}
		}
		System.out.println(min);
	}
	
	
	public String toString() {
	
		StringBuilder sb = new StringBuilder();
		for(int i=1;i<n+1;i++){
			for(int j=1;j<n+1;j++){
				sb.append(D[i][j] + " ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public String toStringIter(int k) {
		
		StringBuilder sb = new StringBuilder();
		for(int i=1;i<n+1;i++){
			for(int j=1;j<n+1;j++){
				if (D[i][j] == Integer.MAX_VALUE)
					sb.append(" - ");
				else
					sb.append(String.format("%2s ", D[i][j]));
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public static void main(String[] args) {

		try {
			FloydWarshall3 fw = new FloydWarshall3("data/ass4/g3.txt");
			fw.solve();
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}









