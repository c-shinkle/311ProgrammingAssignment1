import java.util.ArrayList;

/**
 * 
 * @author Christian Shinkle
 * @author Alec Harrison
 * @author Benjamin Trettin
 */
public class HashStringSimilarity {
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
			ArrayList<Integer> count = countNumberOfEachTypeOfTuple(table.search(key));
			for (Integer i : count) {
				result += (i*i);
			}
		}
		return (float) Math.sqrt(result);
	}
	
	private ArrayList<Integer> countNumberOfEachTypeOfTuple(ArrayList<Tuple> bin) {
		ArrayList<Integer> result = new ArrayList<>();
		boolean[] hasChecked = new boolean[bin.size()];
		for (int i = 0; i < bin.size(); i++) {
			if (!hasChecked[i]) {
				Tuple shingle = bin.get(i);
				int count = 1;
				hasChecked[i] = true;
				for (int j = i + 1; j < bin.size(); j++) {
					if (!hasChecked[j] && shingle.equals(bin.get(j))) {
						hasChecked[j] = true;
						count++;
					}
				}
				result.add(count);
			}
		}
		return result;
	}
	
	private void fillTable(HashTable table, String s) {
		for (int i = 0; i < s.length()-shingleLength+1;i++) {
			String sub = substring(s, i, i+shingleLength);
			int hash = stringHash(sub);
			if (!tableContains(table, hash))
				keys.add(hash);
			table.add(new Tuple(hash, sub));
		}
	}
	
	private int stringHash(String sub) {
		int result = 0;
		for (int j = 0; j < shingleLength; j++)
			result += sub.charAt(j);
		return result;
		//return sub.hashCode();
	}

	private String substring(String s, int start, int end) {
		char[] arr = new char[end-start];
		for (int i = 0; start + i < end; i++)
			arr[i] = s.charAt(start+i);
		return new String(arr);
	}

	private boolean tableContains(HashTable table, int key) {
		return table.search(key).size()!=0;
	}
}
