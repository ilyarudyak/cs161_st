package part2.ass2;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ReaderTest {
	
	HammingMap m;
	HammingMap mf;

	@Before
	public void setUp() {
		m = new HammingMap();
		mf = new HammingMap("data/small.txt");
	}

	@Test
	public void stringToInt() {
		
		String s = "1 1 1 0 0 0 0 0 1 1 0 1 0 0 1 1 1 1 0 0 1 1 1 1";
		assertEquals(14734287, m.stringToInt(s));
		
		String s1 = "0 1 1 0 0 1 1 0 0 1 0 1 1 1 1 1 1 0 1 0 1 1 0 1";
		assertEquals(6709165, m.stringToInt(s1));
		
		String s2 = "1 1 1 1 0 1 0 0 1 1 1 1 1 0 1 1 1 0 1 0 1 1 1 0";
		assertEquals(16055214, m.stringToInt(s2));
	}
	
	@Test
	public void testConstructorWithFile(){
		
		assertTrue(mf.map.containsKey(14734287));
		assertTrue(mf.map.containsKey(6709165));
		assertTrue(mf.map.containsKey(16055214));
		
		assertEquals(0, (int) mf.map.get(14734287).get(0));
		assertEquals(1, (int) mf.map.get(6709165).get(0));
		assertEquals(96, (int) mf.map.get(16055214).get(0));
		
		//System.out.println(rf.map);
	}

}











