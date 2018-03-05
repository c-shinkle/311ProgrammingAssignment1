import java.util.ArrayList;

/**
 * 
 * @author Christian Shinkle
 * @author Alec Harrison
 * @author Benjamin Trettin
 */
public class HashStringSimilarity {
	private float size1, size2;
	private float similarity;
	private HashTable table1,table2;
	private int shingleLength;
	private ArrayList<Integer> keys;
	public HashStringSimilarity(String s1, String s2, int sLength) {
		keys = new ArrayList<>();
		shingleLength=sLength;
		table1 = new HashTable(s1.length()-sLength+1);
		table2 = new HashTable(s2.length()-sLength+1);
		fillTable(table1, s1);
		fillTable(table2, s2);
	}

	public float lengthOfS1() {
		return length(true);
	}

	public float lengthOfS2() {
		return length(false);
	}
	
	public float similarity() {
		return 0;
	}
	
	private float length(boolean isS1) {
		HashTable table = (isS1) ? table1 : table2;
		double result = 0;
		for (Integer key : keys) {
			ArrayList<Tuple> bin = table.search(key);
			boolean[] hasChecked = new boolean[bin.size()];
			for (int i = 0; i < bin.size(); i++) {
				if (!hasChecked[i]) {
					String shingle = bin.get(i).getValue(); 
					int count = 1;
					hasChecked[i] = true;
					for (int j = i+1; j < bin.size();j++) {
						if (!hasChecked[j] && shingle.equals(bin.get(i).getValue())) {
							hasChecked[j] = true;
							count++;
						}
					}
					count *= count;
					result += count;
				}
			}
		}
		return (float) Math.sqrt(result);
	}
	
	private void fillTable(HashTable table, String s) {
		for (int i = 0; i < s.length()-shingleLength+1;i++) {
			String sub = s.substring(i, i+shingleLength);
			//
			int result = 0;
			for (int j = 0; j < shingleLength; j++)
				result += sub.charAt(j);
			if (!tableContains(table, result))
				keys.add(result);
			table.add(new Tuple(result, sub));
		}
	}
	
	private boolean tableContains(HashTable table, int result) {
		return table.search(result).size()==0;
	}
}
