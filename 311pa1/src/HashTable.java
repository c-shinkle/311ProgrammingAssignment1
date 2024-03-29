import java.util.ArrayList;
/**
 * 
 * @author Christian Shinkle
 * @author Alec Harrison
 * @author Benjamin Trettin
 */
public class HashTable {

	private int tableSize, maxLoad, numberOfUniqueElements;

	private ArrayList<Tuple>[] bucketArray;

	private ArrayList<Tuple> all = new ArrayList<>();
	
	private HashFunction hashFunction;

	@SuppressWarnings("unchecked")
	public HashTable(int size) {
		this.tableSize = smallestPrimeBiggerThan(size);
		this.hashFunction = new HashFunction(tableSize);
		bucketArray = new ArrayList[tableSize];
		maxLoad = 0;
	}
	
	

	public int maxLoad() {
		return maxLoad;
	}
	
	public float averageLoad() {
		return (float) ((sumOfAllBuckets() * 1.0) / tableSize);
	}

	public int size() {
		return tableSize;
	}

	public int numElements() {
		return numberOfUniqueElements;
	}
	
	public float loadFactor() {
		return (float) ((numElements() * 1.0) / size());
	}
	
	public void add(Tuple t) {
		int index = hashFunction.hash(t.getKey());
		if (bucketArray[index] == null) {
			bucketArray[index] = new ArrayList<>();
			numberOfUniqueElements++;
			all.add(t);
		}
		else if (!contains(bucketArray[index], t))
			numberOfUniqueElements++;
		
		bucketArray[index].add(t);
		
		if (bucketArray[index].size() == maxLoad+1)
			maxLoad++;
		if (loadFactor() > 0.7) {
			resizeTable();
		}
	}

	public ArrayList<Tuple> search(int k) {
		ArrayList<Tuple> foundTuples = new ArrayList<Tuple>();
		int index = hashFunction.hash(k);
		if(bucketArray[index] == null)
			return foundTuples;
		for (int i = 0; i < bucketArray[index].size(); i++) {
			if (bucketArray[index].get(i).getKey() == k) {
				foundTuples.add(bucketArray[index].get(i));
			}
		}
		return foundTuples;
	}

	public int search(Tuple t) {
		int numOfTuples = 0;
		int index = hashFunction.hash(t.getKey());
		if(bucketArray[index] == null)
			return 0;

		for (int i = 0; i < bucketArray[index].size(); i++) {
			if (bucketArray[index].get(i).equals(t)) {
				numOfTuples++;
			}
		}
	
		return numOfTuples;
	}

	public void remove(Tuple t) {
		int index = hashFunction.hash(t.getKey());
		ArrayList<Tuple> bucketList = bucketArray[index];
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
	

	
	@SuppressWarnings("unchecked")
	private void resizeTable() {
		tableSize = smallestPrimeBiggerThan(tableSize * 2);
		hashFunction = new HashFunction(tableSize);
		maxLoad = numberOfUniqueElements = 0;



		bucketArray = new ArrayList[tableSize];
		//prepareBuckets();
		for(int i=0; i<all.size(); i++)
			add(all.get(i));

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
			if (al != null)
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

	private boolean contains(ArrayList<Tuple> arr, Tuple t) {
		for (Tuple s : arr)
			if (t.equals(s))	
				return true;
		return false;
	}
	//public void array
	//bucket i = new arraylist tuple 
	
	
} 