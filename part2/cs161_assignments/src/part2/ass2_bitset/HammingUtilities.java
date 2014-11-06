package part2.ass2_bitset;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.Scanner;

public class HammingUtilities {
	
	public static final int BIT_SIZE = 24;

	public static void main(String[] args) {
		
		HammingUtilities hu = new HammingUtilities();

		String s = "1 1 1 0 0 0 0 0 1 1 0 1 0 0 1 1 1 1 0 0 1 1 1 1";
		BitSet bset = hu.stringToBitSet(s);
		System.out.println(hu.closeAndDuplicateToFirstNode(bset, 
				"data/big.txt"));
		/*System.out.println(bset);
		System.out.println(bset.cardinality());
		System.out.println(Arrays.toString(bset.toByteArray()));
		
		String s0 = "0 1 1 0 0 1 1 0 0 1 0 1 1 1 1 1 1 0 1 0 1 1 0 1";
		String s1 = "0 1 0 0 0 1 0 0 0 1 0 1 1 1 1 1 1 0 1 0 0 1 0 1";
		System.out.println(hu.distance(
				hu.stringToBitSet(s0),
				hu.stringToBitSet(s1)));
		
		List<BitSet> list = hu.flip(bset);
		System.out.println(list.size());
		for (BitSet bitSet : list) {
			System.out.print(hu.distance(bset, bitSet) + " ");
		}
		System.out.println();*/
	}
	
	// convert string "0 1 1 0 ..." with space between 
	// symbols to bitset
	public BitSet stringToBitSet(String s) {
		
		BitSet bset = new BitSet(BIT_SIZE);
		String[] a = s.split("\\s+");
		int bitIndex = 0;
		for (String bs : a) {
			if (bs.equals("1")) 
				bset.set(bitIndex);
			bitIndex++;
		}
		return bset;
	}
	
	public int distance(final BitSet a, final BitSet b) {
		int d = 0;
		for (int i = 0; i < BIT_SIZE; i++) {
			if (a.get(i) != b.get(i)) 
				d++;
		}
		return d;
	}
	
	// list of all bit sets where 1 or 2 bits flipped
	public List<BitSet> flip(final BitSet a) {
		
		List<BitSet> list = new ArrayList<>();
		
		// add bit sets with only 1 bit flipped
		for (int i = 0; i < BIT_SIZE; i++) {
			BitSet b = (BitSet) a.clone();
			b.flip(i);
			list.add(b);
		}
		
		// add arrays with 2 bits flipped
		for (int i = 0; i < BIT_SIZE; i++) {
			for (int j = i + 1; j < BIT_SIZE; j++) {
				BitSet b = (BitSet) a.clone();
				b.flip(i);
				b.flip(j);
				list.add(b);
			}
		}
		
		return list;
	}
	
	// result exclude the node itself
	public int closeAndDuplicateToFirstNode(BitSet bset, String filename) {
		
		int count = 0;
		BitSet b;
		try (Scanner in = new Scanner(new File(filename))){

			in.nextLine();
			in.nextLine(); // skip first node
			while(in.hasNextLine()){
				
				b = stringToBitSet(in.nextLine().trim());
				if (distance(bset, b) <= 2)
					count++;
			}
		}
		catch (IOException e) {}
		return count;
	}

}















