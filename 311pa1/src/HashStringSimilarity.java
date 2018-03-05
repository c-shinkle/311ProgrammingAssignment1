/**
 * 
 * @author Christian Shinkle
 * @author Alec Harrison
 * @author Benjamin Trettin
 */
public class HashStringSimilarity {
	float size1, size2;
	float similarity;
	HashTable table1,table2;
	int sLength;
	public HashStringSimilarity(String s1, String s2, int sLength) {
		this.sLength=sLength;
		int sizeS1, sizeS2;
		sizeS1 = s1.length()-sLength+1;
		sizeS2 = s2.length()-sLength+1;
		table1 = new HashTable(sizeS1);
		table2 = new HashTable(sizeS1);
		
	}
	
	public float lengthOfS1() {
		String sample = this.s1;
		double squaredval=0; 
		return (float) java.lang.Math.sqrt(squaredval);
	}
	
	public float lengthOfS2() {
		String sample = this.s1;
		double squaredval=0;
		return (float) java.lang.Math.sqrt(squaredval);
	}
	
	public float similarity() {
		return 0;
	}
}
