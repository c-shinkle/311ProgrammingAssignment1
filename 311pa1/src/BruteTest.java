import static org.junit.Assert.*;

import org.junit.Test;

public class BruteTest {

	private final double ep = .001;
	@Test
	public void testBruteForceSimilarity() {
		BruteForceSimilarity a = new BruteForceSimilarity("asd","asd",2);
		assertEquals(a.s1,"asd");
		assertEquals(a.s2,"asd");
		assertEquals(a.sLength,2);
	}

	@Test
	public void testLengthOfS1() {
		BruteForceSimilarity a = new BruteForceSimilarity("1268264612","asd",2);
		
		assertEquals(a.lengthOfS1(),java.lang.Math.sqrt(24),ep);
		//fail("So this is the exact same value not sure why they fail");
	}

	@Test
	public void testLengthOfS2() {
		//fail("So this is the exact same value not sure why they fail");
		BruteForceSimilarity a = new BruteForceSimilarity("asd","251188438",2);
		
		assertEquals(a.lengthOfS2(),java.lang.Math.sqrt(17),ep);
		
	
	}
//
	@Test
	public void testSimilarity() {
		BruteForceSimilarity a = new BruteForceSimilarity("1268264612","251188438",2);
		assertSame((int)a.lengthOfS2(), (int) java.lang.Math.sqrt(17));
		BruteForceSimilarity b = new BruteForceSimilarity("1268264612","251188438",2);
		assertEquals((int)b.lengthOfS1(), (int) java.lang.Math.sqrt(24));
		BruteForceSimilarity c = new BruteForceSimilarity("1268264612","251188438",2);
		
		assertEquals(c.similarity(),.544,ep);
	}

}
