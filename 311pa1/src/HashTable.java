import java.util.ArrayList;
/**
 * 
 * @author Christian Shinkle
 * @author Alec Harrison
 * @author Benjamin Trettin
 */
public class HashTable {

	private int tableSize, maxLoad, numberOfUniqueElements;

	private ArrayList<ArrayList<Tuple>> bucketArray;

	private HashFunction hashFunction;

	public HashTable(int size) {
		this.tableSize = smallestPrimeBiggerThan(size);
		this.hashFunction = new HashFunction(tableSize);
		bucketArray = new ArrayList<ArrayList<Tuple>>(tableSize);
		maxLoad = 0;
	}
	
	public int maxLoad() {
		return maxLoad;
	}
	
	public float averageLoad() {
		return sumOfAllBuckets() /tableSize;
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
		if (bucketArray.get(index) == null) {
			bucketArray.add(new ArrayList<>());
			numberOfUniqueElements++;
		}
		else if (!bucketArray.get(index).contains(t))
			numberOfUniqueElements++;
		bucketArray.get(index).add(t);
		if (bucketArray.get(index).size() == maxLoad+1)
			maxLoad++;
		if (loadFactor() > 0.7)
			resizeTable();
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
		ArrayList<Tuple> bucketList = bucketArray.get(index);
		if (bucketList != null) {
			if (bucketList.remove(t)) {
				tableSize--;
				if (!bucketList.contains(t))
					numberOfUniqueElements--;
				if (bucketList.size() != maxLoad-1)
					maxLoad = findLargestBucketList();
			}
		}
	}
	
	private void resizeTable() {
		tableSize = smallestPrimeBiggerThan(tableSize * 2);
		hashFunction = new HashFunction(tableSize);
		maxLoad = numberOfUniqueElements = 0;
		
		ArrayList<Tuple> allTuples = new ArrayList<>();
		for (ArrayList<Tuple> al : bucketArray)
			for (Tuple t : al)
				allTuples.add(t);
		
		bucketArray = new ArrayList<ArrayList<Tuple>>(tableSize);
		
		for (Tuple t : allTuples) 
			add(t);
	}
	

	private int findLargestBucketList() {
		int result = -1;
		for (ArrayList<Tuple> al : bucketArray)
			Math.max(result, al.size());
		return result;
	}
	
	private int sumOfAllBuckets() {
		int result = 0;
		for (ArrayList<Tuple> al : bucketArray)
			result += al.size();
		return result;
	}
	// helper method for finding prime number
	
	private boolean isPrime(int n) {
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
