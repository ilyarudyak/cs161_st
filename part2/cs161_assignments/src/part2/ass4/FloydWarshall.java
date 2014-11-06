package part2.ass4;

import java.io.FileNotFoundException;

public class FloydWarshall {
	
	private Graph graph;
	private int n;
	private int[][][] D;
	
	public FloydWarshall(String filename) 
			throws FileNotFoundException {
		
		graph = new Graph(filename);
		n = graph.n;
		D = new int[n+1][n+1][n+1];
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
		
		for(int k=1;k<n+1;k++){
			
			for(int i=1;i<n+1;i++){
				for(int j=1;j<n+1;j++){
					
					D[k][i][j] = Math.min(D[k-1][i][j], 
										  sum(D[k-1][i][k], D[k-1][k][j]));
				}
			}
		}
		shortest();
	}
	
	private void shortest() {
		
		int tmp = Integer.MAX_VALUE;
		for(int i=1;i<n+1;i++){
			
			if (D[n][i][i] < 0) {
				System.out.println("negative cycle");
				break;
			}
			
			for(int j=1;j<n+1;j++){
				
				if (D[n][i][j] < tmp)
					tmp = D[n][i][j];
			}
		}
		System.out.println(tmp);
	}
	
	
	private int sum(int a, int b) {
		
		if (a == Integer.MAX_VALUE || b == Integer.MAX_VALUE)
			return Integer.MAX_VALUE;
		else 
			return a + b;
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
				if (D[k][i][j] == 1000000)
					sb.append(" - ");
				else
					sb.append(String.format("%2s ", D[k][i][j]));
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	public static void main(String[] args) {

		try {
			FloydWarshall fw = new FloydWarshall("data/ass4/t2.txt");
			System.out.println(fw.toStringIter(0));
			
			fw.solve();
			System.out.println(fw.toStringIter(1));
			System.out.println(fw.toStringIter(2));
			System.out.println(fw.toStringIter(3));
			System.out.println(fw.toStringIter(4));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}















