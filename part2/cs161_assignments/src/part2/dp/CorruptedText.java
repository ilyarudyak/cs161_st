package part2.dp;

import java.util.Arrays;

public class CorruptedText {
	
	private String s = "itwasthebestoftimes";
	private int[] E = new int[s.length()+1];
	private boolean[] d = new boolean[s.length() + 1];
	private int[] c = new int[s.length()+1];
	
	public boolean dict(String word) {
		
		if (word.equals("it")    ||
			word.equals("was")   ||	
			word.equals("the")   ||
			word.equals("best")  ||
			word.equals("of")    ||
			word.equals("times"))
			return true;
		else return false;
	}
	
	public void solve() {
		
		E[0] = 0;
		System.out.println(s.length());
		for (int i = 1; i <= s.length(); i++) {
			
			E[i] = dict(s.substring(E[i-1], i)) ? i : E[i-1];
			System.out.println(s.substring(E[i-1], i));
			
			
		}
	}
	
	
	public void solve2() {
		
		d[0] = true;
		String phrase = "";
		for (int i = 1; i <= s.length(); i++) {
			for (int j = 0; j < i; j++) {
				
				if (d[j] && dict(s.substring(j, i))) {
					phrase += s.substring(j, i) + " ";
					d[i] = true;
					break;
				}
				d[i] = false;
			}
		}
		System.out.println(phrase);
	}

	public static void main(String[] args) {
		
		CorruptedText ct = new CorruptedText();
		ct.solve2();
		System.out.println(Arrays.toString(ct.d));
	}

}

























