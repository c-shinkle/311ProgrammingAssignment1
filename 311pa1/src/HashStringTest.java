import static org.junit.Assert.*;

import org.junit.Test;

public class HashStringTest
{
	//An idiot wrote these cases and that makes me sad :'( 
    public static String vectorString1 = "ABFHBFDFAB";
    public static String vectorString2 = "BEAAHHDCH";
    public static String similarityString1 = "Aroseisaroseisarose";
    public static String similarityString2 = "Aroseisaflowerwhichisarose";

    public static int vectorShingleLength = 1;
    public static int similarityShingleLength = 4;

    public static float vectorAnswer1 = (float) Math.sqrt(24);
    public static float vectorAnswer2 = (float) Math.sqrt(17);
    public static float similarityAnswer = (float) (22 / (Math.sqrt(38) * Math.sqrt(27)));
    
    public final double EPSILON = .001;

    @Test
    public void bruteForceVectorLength()
    {
        BruteForceSimilarity bfs = new BruteForceSimilarity(vectorString1, vectorString2, vectorShingleLength);
        assertEquals(bfs.lengthOfS1(), vectorAnswer1, EPSILON);
        assertEquals(bfs.lengthOfS2(), vectorAnswer2, EPSILON);
    }

    @Test
    public void bruteForceSimilarity()
    {
        BruteForceSimilarity bfs = new BruteForceSimilarity(similarityString1, similarityString2, similarityShingleLength);
        assertEquals(bfs.similarity(), similarityAnswer, EPSILON);
    }

    @Test
    public void hashStringVectorLength()
    {
        HashStringSimilarity bfs = new HashStringSimilarity(
                vectorString1, vectorString2, vectorShingleLength);
        //bfs.vectorCounts("ve ewr wr");
        assertEquals(bfs.lengthOfS1(), vectorAnswer1, EPSILON );
        assertEquals(bfs.lengthOfS2() == vectorAnswer2,EPSILON);
    }

    @Test
    public void hashStringSimilarity()
    {
        HashStringSimilarity bfs = new HashStringSimilarity(
                similarityString1, similarityString2, similarityShingleLength);
        assertEquals(bfs.similarity(), similarityAnswer, EPSILON);
    }

    @Test
    public void hashCodeVectorLength()
    {
        HashCodeSimilarity bfs = new HashCodeSimilarity(
                vectorString1, vectorString2, vectorShingleLength);
        assertEquals(bfs.lengthOfS1(), vectorAnswer1, EPSILON);
        assertEquals(bfs.lengthOfS2(), vectorAnswer2, EPSILON);
    }

    @Test
    public void hashCodeSimilarity()
    {
        HashCodeSimilarity bfs = new HashCodeSimilarity(
                similarityString1, similarityString2, similarityShingleLength);
        assertEquals(bfs.similarity(), similarityAnswer, EPSILON);
    }
} 