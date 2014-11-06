package part2.week3;

import java.util.Arrays;


public class LCS {

    public static void main(String[] args) {
    	
        String x = "ggcaccacg"; //StdIn.readString();
        String y = "acggcggatacg"; //StdIn.readString();
        int M = x.length();
        int N = y.length();

        // opt[i][j] = length of LCS of x[i..M] and y[j..N]
        int[][] opt = new int[M+1][N+1];

        // compute length of LCS and all subproblems via dynamic programming
        for (int j = N-1; j >= 0; j--) {
            for (int i = M-1; i >= 0; i--) {
                if (x.charAt(i) == y.charAt(j))
                    opt[i][j] = opt[i+1][j+1] + 1;
                else 
                    opt[i][j] = Math.max(opt[i+1][j], opt[i][j+1]);
            }
        }
        
        for (int i = 0; i < opt.length; i++) {
			System.out.println(Arrays.toString(opt[i]));
		}

        // recover LCS itself and print it to standard output
        int i = 0, j = 0;
        while(i < M && j < N) {
            if (x.charAt(i) == y.charAt(j)) {
                System.out.print(x.charAt(i));
                i++;
                j++;
            }
            else if (opt[i+1][j] >= opt[i][j+1]) i++;
            else                                 j++;
        }
        System.out.println();

    }

}
