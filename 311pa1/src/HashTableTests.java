import java.util.ArrayList;
import java.util.Random;
 
public class HashTableTests {
 
    public static void main(String[] args) {
        StressTests();
    }
   
    public static void StressTests() {
       
        int n = 1000000;
        HashTable table = new HashTable(2);
       
        Random r = new Random(389776423);
        ArrayList<Tuple> arr = new ArrayList<>();
        for(int i = 0; i < n; i++)
            arr.add(new Tuple(
                        r.nextInt(),    
                        randomString(r, 12)));
       
       
       
        System.out.println("Adding " + n + " elements into table");
        long nanoStart = System.currentTimeMillis();
        for(Tuple t : arr)
            table.add(t);
        long nanoStop = System.currentTimeMillis();
        System.out.println("\tExecution time: " + (nanoStop - nanoStart) + "ms\n");
       
       
       
        System.out.println("Searching to make sure all elements exist");
        nanoStart = System.currentTimeMillis();
        int elementsFound = 0;
        for(Tuple t : arr)
            elementsFound += table.search(t);
        nanoStop = System.currentTimeMillis();
        System.out.println("\tExecution time: " + (nanoStop - nanoStart) + "ms");
        System.out.println("\tRetained " + elementsFound + "/" + arr.size() + " elements.\n");
       
        System.out.println("Maximum load: " + table.maxLoad());
        System.out.println("Average load: " + table.averageLoad());
        //System.out.println("Filled cells: " + table.nonEmpty);
        System.out.println("Table size: " + table.size());
        System.out.println("Hash table load histogram:");
//        int load[] = table.loadHistogram();
//        for(int i = 0; i < load.length; i++)
//          System.out.println("\t" + i + ":\t" + load[i]);
 
    }
   
    private static String randomString(Random r, int length) {
        String s = "";
       
        for(int i = 0; i < length; i++)
            s = s + ('a' + r.nextInt(26));
       
        return s;
    }
}