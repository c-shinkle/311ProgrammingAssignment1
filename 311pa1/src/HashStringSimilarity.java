import java.util.ArrayList;

/**
 * 
 * @author Christian Shinkle
 * @author Alec Harrison
 * @author Benjamin Trettin
 */
public class HashStringSimilarity {

	private HashTable table1, table2;

	private int shingleLength;

	private ArrayList<Tuple> tuples;
	
	//TODO
	public int numerator, vectorLength1Squared, vectorLength2Squared;

	public HashStringSimilarity(String s1, String s2, int sLength) {
		numerator = vectorLength1Squared = vectorLength2Squared = 0;
		tuples = new ArrayList<>();
		shingleLength = sLength;
		table1 = new HashTable(s1.length() - sLength + 1);
		table2 = new HashTable(s2.length() - sLength + 1);
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
		numerator = findAllDuplicates();
		float denominator = lengthOfS1() + lengthOfS2();
		return numerator / denominator;
	}

	private int findAllDuplicates() {
		int sum = 0;
		for (Tuple key : tuples) {
			int bin1Count = table1.search(key);
			int bin2Count = table2.search(key);
			if (bin1Count > 0 && bin2Count > 0) {
				sum += bin1Count * bin2Count;
			}
		}
		return sum;
	}

	private float length(boolean isS1) {
		HashTable table = (isS1) ? table1 : table2;
		int result = 0;
		for (Tuple key : tuples) {
			result += table.search(key);
		}
		if (isS1)
			vectorLength1Squared = result;
		else 
			vectorLength2Squared = result;
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
		final int alpha = 31, mod = 2147483647;
		int hash = 0, power = 1;

		for (int i = shingleLength - 1; i >= 0; i--) {
			int c = s.charAt(i);
			hash = (hash + c * power) % mod;
			power *= alpha;
		}

		power /= alpha;

		for (int i = 0; i < s.length() - shingleLength + 1; i++) {
			Tuple tmp = new Tuple(hash, substring(s, i, i + shingleLength));
			if (!tableContains(table1, tmp) && !tableContains(table2, tmp))
				tuples.add(tmp);
			table.add(tmp);
			if (i < s.length() - shingleLength)
				hash = rollingHashFromLecture(hash, s, i, power, alpha, mod);
		}
	}

	private int rollingHashFromLecture(int hash, String s, int index, int power, int alpha, int mod) {
		int oldchar = s.charAt(index);
		int newchar = s.charAt(index + shingleLength);
		hash = ((hash - (oldchar * power)) * alpha + newchar) % mod;

		return hash;
	}

	private String substring(String s, int start, int end) {
		char[] arr = new char[end - start];
		for (int i = 0; start + i < end; i++)
			arr[i] = s.charAt(start + i);
		return new String(arr);
	}

	private boolean tableContains(HashTable table, Tuple key) {
		return table.search(key) > 0;
	}

}