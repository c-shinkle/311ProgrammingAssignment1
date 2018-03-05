import static org.junit.Assert.*;

import org.junit.Test;

public class HashStringTest
{
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
    
    public static float similarityAnswer = (float) (22 / (Math.sqrt(38) * Math.sqrt(27)));
    
    public final double EPSILON = .001;

    @Test
    public void hashStringVectorLength()
    {
    	 HashStringSimilarity bfs = new HashStringSimilarity(
                 similarityString1, similarityString2, shingleLength);
    	 float actual1 = bfs.lengthOfS1();
    	 float actual2 = bfs.lengthOfS2();
        assertEquals(vectorAnswer1, actual1, EPSILON );
        assertEquals(vectorAnswer2, actual2, EPSILON);
    }
    @Test
    public void hashStringVectorLength2()
    {
    	 HashStringSimilarity bfs2 = new HashStringSimilarity(
                 similarityString1, similarityString2, shingleLength);
    	 float actual1 = bfs2.lengthOfS1();
    	 float actual2 = bfs2.lengthOfS2();
        assertEquals(vectorAnswer3, actual1, EPSILON );
        assertEquals(vectorAnswer4, actual2, EPSILON);
    }
} 