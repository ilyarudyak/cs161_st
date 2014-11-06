package part2.dp;

import java.util.Arrays;


public class Multiplication {
	
	private String s = "bbbbac";
	private String[][] E;
	
	private static String[][] t = {
		
		{"b", "b", "a"},
		{"c", "b", "a"},
		{"a", "c", "c"}
		
	};
	
	public Multiplication() {
		
		E = new String[s.length()][s.length()];
		for (int i = 0; i < E.length; i++) {
				E[i][i] = s.substring(i, i+1);
				
				if (i < E.length-1)
					E[i][i+1] = mult(s.substring(i, i+1),
						s.substring(i+1, i+2));
						
		}
	}
	
	private String mult(String s, String t) {
		
		if 		(s.equals("a") && t.equals("a")) return "b";
		else if (s.equals("a") && t.equals("b")) return "b";
		else if (s.equals("a") && t.equals("c")) return "a";
		
		else if (s.equals("b") && t.equals("a")) return "c";
		else if (s.equals("b") && t.equals("b")) return "b";
		else if (s.equals("b") && t.equals("c")) return "a";
		
		else if (s.equals("c") && t.equals("a")) return "a";
		else if (s.equals("c") && t.equals("b")) return "c";
		else if (s.equals("c") && t.equals("c")) return "c";
		
		else throw new IllegalArgumentException();
	}
	
	private String mult2(String s, String t) {
		
		String res = "";
		String tmp = "";
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < t.length(); j++) {
				tmp = mult(s.substring(i, i+1), t.substring(j, j+1));
				res = add(res, tmp); 
			}
		}
		return res;
	}
	
	public String add(String s, String t) {
		
		for (int i = 0; i < t.length(); i++) {
			if(!s.contains(t.substring(i, i+1)))
				s += t.substring(i, i+1);
		}
		char[] a = s.toCharArray();
		
		Arrays.sort(a);
		s = Arrays.toString(a)
				.replaceAll("\\,", "")
				.replaceAll("\\[", "")
				.replaceAll("\\]", "")
				.replaceAll("\\s+", "");
		return s;
	}
	
	public void solve() {
		
		String tmp = "";
		for (int d = 2; d < E.length; d++) {
			for (int i = 0; i + d < E.length; i++) {
				for (int j = i; j < i+d; j++) {
					tmp = add(tmp, mult2(E[i][j], E[j+1][i+d]));
//					System.out.println(tmp);
				}
				E[i][i+d] = tmp;
				tmp = "";
			}
		}
	}

	public static void main(String[] args) {

		Multiplication m = new Multiplication();
		m.solve();
//		System.out.println(m.mult2("abc", "c"));
		
		
		
		for (int i = 0; i < m.E.length; i++) {
			for (int j = 0; j < m.E.length; j++) {
				if (m.E[i][j] == null)
					System.out.printf("%3s ", "-");
				else
					System.out.printf("%3s ", m.E[i][j]);
			}
			System.out.println();
		}
		
	}

}


















