package part2.ass2_bitset;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * read clustering.txt into hash table. 
 * we don't have node# in the file so we use 0..NODE_NUMBER - 1
 * key = bitset from string (using HammingConverter)
 * value = list of node# (we may have several nodes with the same key)
 * @author IRudyak
 *
 */
public class HammingMap {
	
	Map<BitSet, List<Integer>> map;
	int NODE_NUMBER;
	int duplicates;
	
	public static void main(String[] args) {
		
		HammingMap hm = new HammingMap("data/big.txt");
		System.out.println(hm.map.size());
		System.out.println(hm.duplicates);

	}
	
	public HammingMap(){
		map = new HashMap<>();
	}
	
	public HammingMap(String filename) {
		
		map = new HashMap<>();
		duplicates = 0;
		
		BitSet key;
		int nodeNumber = 0;
		
		HammingUtilities hu = new HammingUtilities();
		
		try (Scanner in = new Scanner(new File(filename))){
			
			List<Integer> list;
			NODE_NUMBER = in.nextInt();
			in.nextLine();
			while(in.hasNextLine()){
				
				key = hu.stringToBitSet(in.nextLine().trim());
				if (!map.containsKey(key))
					list = new ArrayList<>();
				else list = map.get(key);
				
				list.add(nodeNumber++);
				map.put(key, list);
			}
			countDuplicates();
		}
		catch (IOException e) {}
	}
	
	// count duplicates in the file (nodes with same bit set)
	public void countDuplicates() {
		
		for (BitSet key : map.keySet()) {
			if (map.get(key).size() > 1)
				duplicates += map.get(key).size() - 1;
		}
	}
	

	


}


















