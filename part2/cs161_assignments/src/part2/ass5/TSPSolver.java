package part2.ass5;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.google.common.collect.Sets;

public class TSPSolver {
	
	private int n;
	private Point2D.Double[] cities;
	private List<Set<Set<Integer>>> subsets;
	
	public TSPSolver() {
		
		try(Scanner in = new Scanner(new File ("data/tsp/tsp1.txt"))) {
			
			n = in.nextInt();
			cities = new Point2D.Double[n];
			
			subsets = new ArrayList<>();
			for (int i = 0; i < n + 1; i++) {
				subsets.add(new HashSet<Set<Integer>>());
			}
			
			int index = 0;
			
			while(in.hasNextDouble()) {
				cities[index++] = new Point2D.Double(
						in.nextDouble(), in.nextDouble());
			}
			
			buildSubsets();
		}
		catch (IOException e) {
			System.out.println("error" + e.getStackTrace());
		}
	}
	
	private void buildSubsets() {
		
		Integer[] a = new Integer[n];
		for (int i = 0; i < n; i++) {
			a[i] = i + 1;
		}
		
		Set<Integer> set = new HashSet<>(Arrays.asList(a));
		Set<Set<Integer>> pset = Sets.powerSet(set);
		
		int count = 0;
		for (Set<Integer> s : pset) {
			if (s.contains(1)) {
					subsets.get(s.size()).add(s);
			}
		}
	}
	
	public int solve() {
		return 0;
	}
	
	
	

	public static void main(String[] args) {

		TSPSolver s = new TSPSolver();
		System.out.println(s.subsets.get(2));
		
	}

}






















