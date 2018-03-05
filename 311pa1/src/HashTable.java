import java.util.ArrayList;

/**
 * 
 * @author Christian Shinkle
 * @author Alec Harrison
 * @author Benjamin Trettin
 */
public class HashTable {

	int tableSize;

	ArrayList<Tuple>[] bucketArray;

	HashFunction hashFunction;

	@SuppressWarnings("unchecked")
	HashTable(int size) {
		int p = smallestPrimeBiggerThan(size);

		this.tableSize = p;
		this.hashFunction = new HashFunction(p);
		bucketArray = new ArrayList[p];
	}

	public int maxLoad() {
		return 1;
	}

	public float averageLoad() {
		return 1;
	}

	public int size() {
		return tableSize;
	}

	public int numElements() {
		int numOfElements = 0;
		for (int i = 0; i < bucketArray.length; i++) {
			if (bucketArray[i] != null) {
				// Compare
			}
		}
		return numOfElements;
	}

	public float loadFactor() {
		return numElements() / size();
	}

	public void add(Tuple t) {
		int index = hashFunction.hash(t.key);
		if (bucketArray[index] == null) {
			ArrayList<Tuple> newList = new ArrayList<Tuple>();
			bucketArray[index] = newList;
			newList.add(t);
		} else {
			bucketArray[index].add(t);
		}
	}

	public ArrayList<Tuple> search(int k) {
		ArrayList<Tuple> foundTuples = new ArrayList<Tuple>();
		int index = hashFunction.hash(k);
		if (bucketArray[index] != null) {
			for (int i = 0; i < bucketArray[index].size(); i++) {
				if (bucketArray[index].get(i).key == k) {
					foundTuples.add(bucketArray[index].get(i));
				}
			}
		}
		return foundTuples;
	}

	public int search(Tuple t) {
		int numOfTuples = 0;
		int index = hashFunction.hash(t.key);
		if (bucketArray[index] != null) {
			for (int i = 0; i < bucketArray[index].size(); i++) {
				if (bucketArray[index].get(i).equals(t)) {
					numOfTuples++;
				}
			}
		}
		return numOfTuples;
	}

	public void remove(Tuple t) {
		int index = hashFunction.hash(t.key);
		if (bucketArray[index] != null) {
				bucketArray[index].remove(t);
		}
	}

	// helper method for finding prime number
	public static boolean isPrime(int n) {
		if (n % 2 == 0) {
			return false;
		}
		for (int i = 3; i * i <= n; i += 2) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

	// helper method for finding prime number
	public static int smallestPrimeBiggerThan(int num) {
		while (!isPrime(num)) {
			++num;
		}
		return num;
	}
}
