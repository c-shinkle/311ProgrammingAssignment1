import java.util.ArrayList;

/**
 * 
 * @author Christian Shinkle
 * @author Alec Harrison
 * @author Benjamin Trettin
 */
public class HashCodeSimilarity {

	private HashTable table1, table2;

	private int shingleLength;

	private ArrayList<Integer> keys;
	
	

	public HashCodeSimilarity(String s1, String s2, int sLength) {
		keys = new ArrayList<>();
		shingleLength = sLength;
		table1 = new HashTable(s1.length() - sLength + 1);
		table2 = new HashTable(s2.length() - sLength + 1);
		fillTable(true, s1);
		fillTable(false, s2);
	}

	public float lengthOfS1() {
		return length(true);
	}

	public float lengthOfS2() {
		return length(false);
	}

	public float similarity() {
		int numerator = calcNumerator();
		float denominator = lengthOfS1() * lengthOfS2();
		return numerator / denominator;
	}

	private int calcNumerator() {
		int sum = 0;
		
		for (Integer key : keys) {
			
			ArrayList<Tuple> bin1 = table1.search(key);
			ArrayList<Tuple> bin2 = table2.search(key);
			
			if ((bin1 != null && bin2 != null) && (bin1.size() > 0 && bin2.size() > 0)) {
				int count1 = count(bin1, key);
				int count2 = count(bin2, key);
				
				
				sum += count1 * count2;
			}
		}
		return sum;
	}

	private int count(ArrayList<Tuple> bin, int key) {
		int count = 0;
		for (Tuple t : bin) {
			if (t.getKey() == key)
				count++;
			
		}
		return count;
	}

	private float length(boolean isS1) {
		HashTable table = (isS1) ? table1 : table2;
		int result = 0;
		for (Integer key : keys) {
			ArrayList<Integer> count = countNumberOfEachTypeOfTuple(table.search(key));
			for (Integer i : count) {
				result += (i * i);
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

	private void fillTable(boolean table, String s) {
		final int alpha = 11;
		int hash = 0, power = 1;
	
		for (int i = shingleLength - 1; i >= 0; i--) {
			int c = s.charAt(i);
			hash = (hash + c * power) ;
			power *= alpha;
		}

		power /= alpha;
		for (int i = 0; i < s.length() - shingleLength + 1; i++) {
			if (!tableContains(table1, hash) && !tableContains(table2, hash)){
				keys.add(hash);
			}
			String sub = substring(s, i, i + shingleLength);
			if(table){
			table1.add(new Tuple(hash, sub));
			}
			else{
				table2.add(new Tuple(hash, sub));
				}
			if (i < s.length() - shingleLength)
				hash = rollingHashFromLecture(hash, s, i, power, alpha);
		}
		

	}

	private String substring(String s, int start, int end) {
		char[] arr = new char[end - start];
		for (int i = 0; start + i < end; i++)
			arr[i] = s.charAt(start + i);
		return new String(arr);
	}

	private int rollingHashFromLecture(int hash, String s, int index, int power, int alpha) {
		int oldchar = s.charAt(index);
		int newchar = s.charAt(index + shingleLength);
		hash = ((hash - (oldchar * power)) * alpha + newchar) ;

		return hash;
	}

	private boolean tableContains(HashTable table, int key) {
		return table.search(key).size() != 0;
	}

}