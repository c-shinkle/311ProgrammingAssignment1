import java.util.ArrayList;

/**
 * 
 * @author Christian Shinkle
 * @author Alec Harrison
 * @author Benjamin Trettin
 */
public class HashTable {

	private int tableSize, numberOfUniqueElements;

	private ArrayList<ArrayList<Tuple>> bucketArray;

	private HashFunction hashFunction;

	public HashTable(int size) {
		int p = smallestPrimeBiggerThan(size);
		this.tableSize = p;
		this.hashFunction = new HashFunction(p);
		bucketArray = new ArrayList<ArrayList<Tuple>>(p);
	}

	public int maxLoad() {
		return tableSize;
	}

	public float averageLoad() {
		return numberOfUniqueElements /tableSize;
	}

	public int size() {
		return tableSize;
	}

	public int numElements() {
		return numberOfUniqueElements;
	}

	public float loadFactor() {
		return numElements() / size();
	}

	public void add(Tuple t) {
		int index = hashFunction.hash(t.key);
		if (bucketArray.get(index) == null)
			bucketArray.add(new ArrayList<>());
		bucketArray.get(index).add(t);
	}

	public ArrayList<Tuple> search(int k) {
		ArrayList<Tuple> foundTuples = new ArrayList<Tuple>();
		int index = hashFunction.hash(k);
		if (bucketArray.get(index) != null) {
			for (int i = 0; i < bucketArray.get(index).size(); i++) {
				if (bucketArray.get(index).get(i).key == k) {
					foundTuples.add(bucketArray.get(index).get(i));
				}
			}
		}
		return foundTuples;
	}

	public int search(Tuple t) {
		int numOfTuples = 0;
		int index = hashFunction.hash(t.key);
		if (bucketArray.get(index) != null) {
			for (int i = 0; i < bucketArray.get(index).size(); i++) {
				if (bucketArray.get(index).get(i).equals(t)) {
					numOfTuples++;
				}
			}
		}
		return numOfTuples;
	}

	public void remove(Tuple t) {
		int index = hashFunction.hash(t.key);
		if (bucketArray.get(index) != null) {
				bucketArray.get(index).remove(t);
		}
	}

	// helper method for finding prime number
	private  boolean isPrime(int n) {
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
	private int smallestPrimeBiggerThan(int num) {
		while (!isPrime(num)) {
			++num;
		}
		return num;
	}
}
