import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HashTableTest {
	
	private HashTable table1;
	
	private final double EPSILON = .0001;
	@Before
	public void setup() {
		table1 = new HashTable(12);
		table1.add(new Tuple(0, "A"));
		table1.add(new Tuple(1, "B"));
		table1.add(new Tuple(2, "C"));
	}
	
	@Test
	public void accessorTest() {
		assertEquals(table1.maxLoad(), 1);
		assertEquals(table1.averageLoad(), 3/13, EPSILON);
		assertEquals(table1.size(), 13);
		assertEquals(table1.numElements(), 3);
		assertEquals(table1.loadFactor(), 3/13, EPSILON);
	}
	
	@Test
	public void addTable1Test1() {
		table1.add(new Tuple(3, "D"));
		
	}
}
