
import java.util.Arrays;

public class TwoSumOptimizedSolver {
	
	private final long[] numbers;
	private final boolean[] solutions;
	private int nSolutions;
	private final int lowerBound;
	private final int upperBound;

	
	/**
	 * Find all count of all such t's that there exist x,y 
     * from input such that x + y = t, x != y and lowBound <= t <= upperBound 
	 */
	public TwoSumOptimizedSolver(long[] input, int lowerBound, int upperBound) {
		final long[] copy = Arrays.copyOf(input, input.length);
		Arrays.sort(copy);
		this.numbers = copy;
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		this.solutions = new boolean[upperBound - lowerBound +1];
		this.nSolutions = 0;
	}

	public int solve() {
		long[] numbers = this.numbers;
		
		int right = numbers.length - 1;
		for (int left = 0; left < numbers.length; left++) {
            
            //System.out.println(right);

			long x = numbers[left];

			for (int j = right+1; j < numbers.length; j++) {
				long y = numbers[j];
				long sum = x + y; 
				if (sum > upperBound) {
					break;
				} else if (sum >= lowerBound /*&& x != y*/) {
					mark(sum);
				}
			}

			for (int j = right; j > 0/*left*/; j--) {
				long y = numbers[j];
				long sum = x + y;
				if (sum < lowerBound) {
					right = j;
					break;
				} else if (sum <= upperBound /*&& x != y*/)  {
					mark(sum);
				} 
			}
		}
		return nSolutions;
	}
	
	private void mark(long sum) {
		final int idx = (int) (sum-lowerBound);
		if (!solutions[idx]) {
			solutions[idx] = true;
			nSolutions++;
		}
	}
}
