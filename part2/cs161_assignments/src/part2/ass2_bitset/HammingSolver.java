package part2.ass2_bitset;

import java.io.File;
import java.io.IOException;
import java.util.BitSet;
import java.util.List;
import java.util.Scanner;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class HammingSolver {
	
	HammingMap hm;
	WeightedQuickUnionUF uf;
	String filename;

	public static void main(String[] args) {
		
//		HammingUtilities hu = new HammingUtilities();
//
//		String s = "1 1 1 0 0 0 0 0 1 1 0 1 0 0 1 1 1 1 0 0 1 1 1 1";
//		BitSet bset = hu.stringToBitSet(s);
//		
//		String s1 = "0 1 1 0 0 1 1 0 0 1 0 1 1 1 1 1 1 0 1 0 1 1 0 1";
//		BitSet bset1 = hu.stringToBitSet(s);
		
		HammingSolver solver = new HammingSolver("data/medium_10000.txt");
		solver.solve();
		System.out.println(solver.uf.count());

	}
	
	public HammingSolver(String fname) {
		filename = fname;
		hm = new HammingMap(filename);
		uf = new WeightedQuickUnionUF(hm.NODE_NUMBER);
	}
	
	// iterate through file and look up all: 1) duplicates and 2) 1-2 flips
	// into hash table. if found - add nodes# to union find.
	// in other words - add all nodes that have distance 0, 1 or 2 
	// with the current node.
	public void solve() {
		
		HammingUtilities hu = new HammingUtilities();
		int nodeNumber = 0;
		try (Scanner in = new Scanner(new File(filename))){

			in.nextLine();
			while(in.hasNextLine()){
				BitSet bset = hu.stringToBitSet(
						in.nextLine().trim());
				solveNode(nodeNumber, bset, hu);
				nodeNumber++;
			}
		}
		catch (IOException e) {}
	}
	
	public void solveNode(int node, BitSet bset, 
			HammingUtilities hu){
		
		
		// create list of 1-2 permutations of node
		List<BitSet> flips = hu.flip(bset);
		
		// check map and duplicate nodes
		if (hm.map.containsKey(bset)){
			List<Integer> duplicateNodes = hm.map.get(bset);
			for (Integer duplicateNode : duplicateNodes) 
				uf.union(node, duplicateNode);
		}
		
		// check map and add nodes with distance 1, 2 to union find
		for (BitSet key : flips) {
			if (hm.map.containsKey(key)){
				List<Integer> closeNodes = hm.map.get(key);
				for (Integer closeNode : closeNodes) 
					uf.union(node, closeNode);
			}
		}
	}
}





















/*package part2.ass2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class HammingSolver {
	
	HammingMap hm;
	boolean[] closeNodes;


	public HammingSolver(){
		hm = null;
		closeNodes = null;
	}
	
	public HammingSolver(String filename) {
		hm = new HammingMap(filename);
		closeNodes = new boolean[hm.N];
	}
	
	public int numberOfMarkedNodes() {
		int count = 0;
		for (int i = 0; i < closeNodes.length; i++) {
			if(closeNodes[i])
				count++;
		}
		return count;
	}
	
	public void markAllCloseNodes() {
		try(Scanner in = new Scanner(new File("data/clustering_big.txt"))) {
		in.nextLine();
		int node = 0;
		while(in.hasNextLine()){
			markCloseNodes(node, stringToInt(
					in.nextLine()
					));
			node++;
			}
		}catch (IOException e) {}
	}
	
	
	public void markCloseNodes(int node, int bit){
		
		// create list of 1-2 permutations of node
		List<byte[]> permutations = permute(intToByteArray(bit));
		
		// check map and mark close nodes
		int key;
		for (byte[] b : permutations) {
			key = byteArrayToInt(b);
			if (hm.map.containsKey(key)){
				List<Integer> nodes = hm.map.get(key);
				for (Integer n : nodes) {
					if (n != node)
						closeNodes[n] = true;
				}
			}
		}
		
		
	}
	
	
	private int distance(byte[] a, byte[] b) {
		int d = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] != b[i]) 
				d++;
		}
		return d;
	}
	
	private List<byte[]> permute(final byte[] a) {
		
		List<byte[]> list = new ArrayList<>();
		
		// add a itself
		list.add(Arrays.copyOf(a, a.length));
		
		// add arrays with only 1 bit flipped
		for (int i = 0; i < a.length; i++) {
			list.add(flip(a, i));
		}
		
		// add arrays with 2 bits flipped
		for (int i = 0; i < a.length; i++) {
			for (int j = i + 1; j < a.length; j++) {
				list.add(flip(a, i, j));
			}
		}
		return list;
	}
	
	// flip a[i] 0 -> 1 or 1 -> 0
	private byte[] flip (final byte[] a, int i) {
		
		byte[] b = Arrays.copyOf(a, a.length);
		if (a[i] == 0) b[i] = 1;
		else b[i] = 0;
		return b;
	}
	
	private byte[] flip (final byte[] a, int i, int j) {
		
		byte[] b = Arrays.copyOf(a, a.length);
		if (a[i] == 0) b[i] = 1;
		else b[i] = 0;
		if (a[j] == 0) b[j] = 1;
		else b[j] = 0;
		return b;
	}
	
	private byte[] stringToByteArray(String s) {
		
		String[] a = s.split("\\s+");
		byte[] b = new byte[a.length];
		for (int i = 0; i < a.length; i++) {
			b[i] = Byte.parseByte(a[i]);
		}
		return b;
	}
	
	private byte[] intToByteArray(int n) {
		
		String s = Integer.toBinaryString(n);
		byte[] b = new byte[s.length()];
		for (int i = 0; i < s.length(); i++) {
			b[i] = Byte.parseByte(s.substring(i, i + 1));
		}
		return b;
	}
	
	private int byteArrayToInt(byte[] b) {
		String s = "";
		for (int i = 0; i < b.length; i++) {
			s += Byte.toString(b[i]);
		}
		return Integer.parseInt(s, 2);
	}
	
	public int stringToInt(String s) {
		
		return Integer.parseInt(s.replaceAll("\\s+", ""), 2);
	}
	
	public static void main(String[] args) {
		

		
		HammingSolver hf = new HammingSolver("data/clustering_big.txt");
		//hf.markCloseNodes(0,14734287);
		hf.markAllCloseNodes();
		//System.out.println(Arrays.toString(hf.closeNodes));
		System.out.println(hf.numberOfMarkedNodes());
		
	}
}












*/
