package part2.ass4;

import java.io.FileNotFoundException;

public class FloydWarshall2 {
	private Graph graph;
	private int n;
	private int[][][] D;
	
	public FloydWarshall2(String filename) 
			throws FileNotFoundException {
		
		graph = new Graph(filename);
		n = graph.n;
		D = new int[2][n+1][n+1];
		fillBound();
		
	}
	
	private void fillBound() {
		
		for(int i=1;i<n+1;i++){
			for(int j=1;j<n+1;j++){
				D[0][i][j] = graph.W[i][j];
			}
		}
	}
	
	public void solve() {
		
		int k1, k2;
		for(int k=1;k<n+1;k++){
			k1 = k % 2;
			k2 = k1 == 1 ? 0 : 1;
			
			for(int i=1;i<n+1;i++){
				for(int j=1;j<n+1;j++){
					
					
					D[k1][i][j] = Math.min(D[k2][i][j], 
										   D[k2][i][k] + D[k2][k][j]);
					
					if (i == j) {
						if (D[k1][i][j] < 0) {
							System.out.println("negative cycle");
							return;
						}
					}
				}
			}
			System.out.println(toStringIter(k));
		}
		shortest();
	}
	
	private void shortest() {
		
		int min = 0;
		for(int i=1;i<n+1;i++){
			for(int j=1;j<n+1;j++){
//				if (i != j) 
				min = Math.min(min, D[n%2][i][j]);
			}
		}
		System.out.println(min);
	}
	
	
	public String toString() {
	
		StringBuilder sb = new StringBuilder();
		for(int i=1;i<n+1;i++){
			for(int j=1;j<n+1;j++){
				sb.append(D[n][i][j] + " ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
	
	public String toStringIter(int k) {
		
		StringBuilder sb = new StringBuilder();
		for(int i=1;i<n+1;i++){
			for(int j=1;j<n+1;j++){
				if (D[k%2][i][j] == 1000000)
					sb.append(" - ");
				else
					sb.append(String.format("%2s ", D[k%2][i][j]));
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public static void main(String[] args) {

		try {
			FloydWarshall2 fw = new FloydWarshall2("data/ass4/t1.txt");
			System.out.println(fw.toStringIter(0));
			
			fw.solve();
			System.out.println(fw.toStringIter(0));
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}








