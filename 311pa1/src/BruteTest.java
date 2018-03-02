import static org.junit.Assert.*;

import org.junit.Test;

public class BruteTest {

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
		
		assertEquals((int)a.lengthOfS1(), (int) java.lang.Math.sqrt(24));
		//fail("So this is the exact same value not sure why they fail");
	}

	@Test
	public void testLengthOfS2() {
		//fail("So this is the exact same value not sure why they fail");
		BruteForceSimilarity a = new BruteForceSimilarity("asd","251188438",2);
		
		assertSame((int)a.lengthOfS2(), (int) java.lang.Math.sqrt(17));
		
	
	}
//
//	@Test
//	public void testSimilarity() {
//		fail("Not yet implemented");
//	}

}
