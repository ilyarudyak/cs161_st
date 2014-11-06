package part2.ass2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * read clustering.txt into hash table. 
 * key = bit string converted into integer.
 * value = node number
 * Example:
 * key 111000001101001111001111 = 14734287
 * value = 1 
 * @author IRudyak
 *
 */
public class HammingMap {
	
	Map<Integer, List<Integer>> map;
	int N; // number of nodes
	int b; // number of bits
	
	public HammingMap(){
		N = 0;
		b = 0;
		map = new HashMap<>();
	}
	
	public HammingMap(String filename) {
		
		int key;
		int nodeNumber = 0;
		map = new HashMap<>();
		
		try (Scanner in = new Scanner(new File(filename))){
			
			N = in.nextInt();
			b = in.nextInt();
			List<Integer> list;
			
			in.nextLine();
			while(in.hasNextLine()){
				
				key = stringToInt(in.nextLine().trim());
				if (!map.containsKey(key))
					list = new ArrayList<>();
				else list = map.get(key);
				
				list.add(nodeNumber++);
				map.put(key, list);
			}
		}
		catch (IOException e) {}
	}
	

	
	// convert 1 1 1 0 0 0 0 0 1 1 0 1 0 0 1 1 1 1 0 0 1 1 1 1
	// to 14734287
	public int stringToInt(String s) {
		
		return Integer.parseInt(s.replaceAll("\\s+", ""), 2);
	}

}

















