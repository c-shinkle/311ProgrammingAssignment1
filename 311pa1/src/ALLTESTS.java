import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import org.junit.Test;
public class ALLTESTS {

	public static final String testString1 = "FlexboxFlexboxFlexboxFlexbox";
	public static final String testString2 = "isaflexboxtounguetwister";
	public static final String testString3 = "fitnessgrampacertest";
	
	public static String similarityString1 = "aroseisaroseisarose";
	public static String similarityString2 = "aroseisaflowerwhichisarose";

	public static String similarityString3 = "thehotthotshotheho";
	public static String similarityString4 = "thethotthatshothehoishot";

	public static int shingleLength = 4;
	public static int shingleLength2 = 3;

	public static float vectorAnswer1 = (float) Math.sqrt(24);
	public static float vectorAnswer2 = (float) Math.sqrt(17);

	public static float vectorAnswer3 = (float) Math.sqrt(21);
	public static float vectorAnswer4 = (float) Math.sqrt(13);

	public static float similarityAnswer1 = (float) (22 / (Math.sqrt(38) * Math.sqrt(27)));
	public static float similarityAnswer2 = (float) (21 / (Math.sqrt(21) * Math.sqrt(27)));

	public final double EPSILON = .0000001;
	public final double ep = .001;
	public static String textFile1 = null;
	public static String textFile2 = null;
		//BRUTE FORCE
		@Test
		public void testBruteForceSimilarity() {
			BruteForceSimilarity a = new BruteForceSimilarity("asd","asd",1);
			assertEquals(a.s1,"asd");
			assertEquals(a.s2,"asd");
			assertEquals(a.sLength,1);
		}

		@Test
		public void testLengthOfS1() {
			BruteForceSimilarity a = new BruteForceSimilarity("1268264612","asd",1);
			
			assertEquals(a.lengthOfS1(),java.lang.Math.sqrt(24),ep);
			//fail("So this is the exact same value not sure why they fail");
		}

		@Test
		public void testLengthOfS2() {
			//fail("So this is the exact same value not sure why they fail");
			BruteForceSimilarity a = new BruteForceSimilarity("asd","251188438",1);
			
			assertEquals(a.lengthOfS2(),java.lang.Math.sqrt(17),ep);
			
		
		}
	//
		@Test
		public void testSimilarity() {
			BruteForceSimilarity a = new BruteForceSimilarity("1268264612","251188438",1);
			assertSame((int)a.lengthOfS2(), (int) java.lang.Math.sqrt(17));
			BruteForceSimilarity b = new BruteForceSimilarity("1268264612","251188438",1);
			assertEquals((int)b.lengthOfS1(), (int) java.lang.Math.sqrt(24));
			BruteForceSimilarity c = new BruteForceSimilarity("1268264612","251188438",1);
			
			assertEquals(c.similarity(),.544,ep);
		}
		public void thisisrosetest() {
			BruteForceSimilarity a = new BruteForceSimilarity(similarityString1,similarityString2,4);
			
			
			assertEquals(a.similarity(),similarityAnswer1,ep);
		}
		
		
		
		
		
		//HASH STRING
		public void fileScan() throws FileNotFoundException {

			File test1file = new File("../test1.txt");
			Scanner sc = new Scanner(test1file);
			while (sc.hasNext()) {
				textFile1 = sc.next();
			}
			sc.close();

			File test2file = new File("../test2.txt");
			Scanner sc2 = new Scanner(test2file);
			while (sc2.hasNext()) {
				textFile2 = sc2.next();
			}
			sc2.close();
		}

		@Test
		public void hashStringVectorLength01() {
			HashStringSimilarity bfs = new HashStringSimilarity(similarityString1, similarityString2, shingleLength);
			bfs.similarity();
			int expected = 38;
			assertEquals(expected,bfs.vectorLength1Squared);
		}
		@Test
		public void hashStringVectorLength02() {
			HashStringSimilarity bfs = new HashStringSimilarity(similarityString1, similarityString2, shingleLength);
			bfs.similarity();
			int expected = 27;
			assertEquals(expected,bfs.vectorLength2Squared);
		}

		@Test
		public void numberatorhashStringSimilarity() {
			HashStringSimilarity bfs = new HashStringSimilarity(similarityString1, similarityString2, shingleLength);
			bfs.similarity();
			int expected = 22;
			assertEquals(expected,bfs.numerator);
		}
		@Test
		public void hashStringVectorLength01part2() {
			HashStringSimilarity bfs = new HashStringSimilarity(similarityString3, similarityString4, shingleLength2);
			bfs.similarity();
			int expected = 27;
			assertEquals(expected,bfs.vectorLength1Squared);
		}
		@Test
		public void hashStringVectorLength02part2() {
			HashStringSimilarity bfs = new HashStringSimilarity(similarityString3, similarityString4, shingleLength2);
			bfs.similarity();
			int expected = 32;
			assertEquals(expected,bfs.vectorLength2Squared);
		}

		@Test
		public void numberatorhashStringSimilaritypart2() {
			HashStringSimilarity bfs = new HashStringSimilarity(similarityString3, similarityString4, shingleLength2);
			bfs.similarity();
			int expected = 24;
			assertEquals(expected,bfs.numerator);
		}
		
		// @Test
		// public void testAllSimilarities() {
		// HashStringSimilarity stringSimilarity = new
		// HashStringSimilarity(textFile1, textFile2, shingleLength);
		// HashCodeSimilarity codeSimilarity = new HashCodeSimilarity(textFile1,
		// textFile2, shingleLength);
		// BruteForceSimilarity forceSimilarity = new
		// BruteForceSimilarity(textFile1, textFile2, shingleLength);
		// float stringSim = stringSimilarity.similarity();
		// float CodeSim = codeSimilarity.similarity();
		// float BruteSim = forceSimilarity.similarity();
		// assertEquals(stringSim,CodeSim,EPSILON);
		// assertEquals(stringSim,BruteSim,EPSILON);
		// assertEquals(CodeSim,BruteSim,EPSILON);
		// }
		
		
		
		//HASH TABLE TESTS
		
		
		
		  public static HashTable allSameHashTable()
		    {
		        HashTable ht = new HashTable(1024);
		        ht.add(new Tuple(23, testString1));
		        ht.add(new Tuple(23, testString2));
		        return ht;
		    }

		    public static HashTable allUniqueHashTable()
		    {
		        HashTable ht = new HashTable(1024);
		        ht.add(new Tuple(23, testString1));
		        ht.add(new Tuple(25, testString2));
		        return ht;
		    }

		    public static HashTable mixedHashTable()
		    {
		        HashTable ht = new HashTable(1024);
		        ht.add(new Tuple(23, testString1));
		        ht.add(new Tuple(23, testString2));
		        ht.add(new Tuple(25, testString3));
		        return ht;
		    }

		    @Test
		    public void addAndLookup()
		    {
		        HashTable ht = new HashTable(1024);
		        Tuple a = new Tuple(23, testString1);
		        Tuple b = new Tuple(2435, testString2);
		        ht.add(a);
		        ht.add(b);
		        assert (ht.search(a.getKey()).contains(a));
		        assert (ht.search(a.getKey()).get(0).getValue() == a.getValue());
		        assert (ht.search(b.getKey()).contains(b));
		        assert (ht.search(b.getKey()).get(0).getValue() == b.getValue());
		    }

		    @Test
		    public void addCollisions()
		    {
		        HashTable ht = new HashTable(1024);
		        Tuple a = new Tuple(23, testString1);
		        Tuple b = new Tuple(23, testString2);
		        ht.add(a);
		        ht.add(b);
		        ArrayList<Tuple> list = ht.search(a.getKey());
		        assert (list.contains(a));
		        assert (list.contains(b));
		    }

		    @Test
		    public void allSameNumLoaded()
		    {
		        assert (allSameHashTable().maxLoad() == 2);
		    }

		    @Test
		    public void allUniqueNumLoaded()
		    {
		        assert (allUniqueHashTable().maxLoad() == 1);
		    }

		    @Test
		    public void mixedNumLoaded()
		    {
		        assert (mixedHashTable().maxLoad() == 2);
		    }

		    @Test
		    public void allSameAverageLoad()
		    {
		        assert (allSameHashTable().averageLoad() == 2);
		    }

		    @Test
		    public void allSameAverageLoadRemove()
		    {
		        HashTable ht = allSameHashTable();
		        ht.remove(new Tuple(23, testString1));
		        assert (ht.averageLoad() == 1.0);
		    }

		    @Test
		    public void allUniqueAverageLoad()
		    {
		        assert (allUniqueHashTable().averageLoad() == 1);
		    }

		    @Test
		    public void allUniqueAverageLoadRemove()
		    {
		        HashTable ht = allUniqueHashTable();
		        ht.remove(new Tuple(23, testString1));
		        assert (ht.averageLoad() == 1);
		    }

		    @Test
		    public void mixedAverageLoad()
		    {
		        assert (mixedHashTable().averageLoad() == 1.5);
		    }

		    @Test
		    public void mixedAverageLoadRemove()
		    {
		        HashTable ht = mixedHashTable();
		        ht.remove(new Tuple(23, testString1));
		        assert (ht.averageLoad() == 1);

		    }

		    @Test
		    public void mixedAverageLoadRemove2()
		    {
		        HashTable ht = mixedHashTable();
		        ht.remove(new Tuple(25, testString3));
		        assert (ht.averageLoad() == 2);
		    }

		    @Test
		    public void allSameLoadFactor()
		    {
		        assert (allSameHashTable().loadFactor() == (float) 2 / 1031);
		    }

		    @Test
		    public void allUniqueLoadFactor()
		    {
		        assert (allUniqueHashTable().loadFactor() == (float) 2 / 1031);
		    }

		    @Test
		    public void mixedLoadFactor()
		    {
		        assert (mixedHashTable().loadFactor() == (float) 3 / 1031);
		    }

		    @Test
		    public void emptyAverageLoad()
		    {
		        assert (new HashTable(1024).averageLoad() == 0);
		    }

		    @Test
		    public void emptyMaxLoad()
		    {
		        assert (new HashTable(1024).maxLoad() == 0);
		    }

		    @Test
		    // due to spooky maths, small chance of false flags
		    public void resizes()
		    {
		        HashTable ht = new HashTable(11);
		        for (int i = 0; i < 15; i++)
		        {
		            ht.add(new Tuple(i, testString1));
		        }
		        assert (ht.size() > 11);
		    }

		    @Test
		    public void sizeDoubles()
		    {
		        int size = 23;
		        HashTable ht = new HashTable(size);
		        for (int i = 0; ht.size() == size; i++)
		        {
		            ht.add(new Tuple(i, testString1));
		        }
		        assert (ht.size() == 47);
		    }

		    @Test
		    public void resizeLosesNothing()
		    {
		        int size = 20;
		        HashTable ht = new HashTable(size);
		        LinkedList<Tuple> tuples = new LinkedList<Tuple>();
		        for (int i = 0; ht.size() == 23 && i < 1000; i++)
		        {
		            Tuple t = new Tuple(i, testString1);
		            ht.add(t);
		            tuples.add(t);
		        }
		        for (Tuple t : tuples)
		        {
		            assert (ht.search(t.key).contains(t));
		        }
		    }

		    @Test
		    public void sizeIsPrime()
		    {
		        HashTable ht = new HashTable(1024);
		        assert (ht.size() == 1031);
		    }
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}


