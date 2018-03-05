import java.util.ArrayList;

/**
 * 
 * @author Christian Shinkle
 * @author Alec Harrison
 * @author Benjamin Trettin
 */
public class BruteForceSimilarity {
	float size1, size2;
	float similarity;
	String s1, s2;
	int sLength;
	public BruteForceSimilarity(String s1, String s2, int sLength) {
		//Assuming slength is the length of s2
		this.s1=s1;
		this.s2=s2;
		this.sLength=sLength;
		
	}
	
	public float lengthOfS1() {
		String sample = this.s1;
		double squaredval=0;
		int counter=0;
		int j = 0;
		ArrayList<Character> duplicates = new ArrayList<Character>();
		int i = 0;
		while(i<sample.length()){
			counter =0;
			if(duplicates.contains(sample.charAt(i))==false){
				while(j<sample.length()){
					if(sample.charAt(i)==sample.charAt(j)){
						counter ++;
					}
					j++;
				}
				j=0;
				squaredval = squaredval + java.lang.Math.pow(counter,2);
				duplicates.add(sample.charAt(i));
			}
			i++;
		}
		
		return (float) java.lang.Math.sqrt(squaredval);
	}
	
	public float lengthOfS2() {
		String sample = this.s2;
		double squaredval=0;
		int counter=0;
		int j = 0;
		ArrayList<Character> duplicates = new ArrayList<Character>();
		int i = 0;
		while(i<sample.length()){
			counter =0;
			if(duplicates.contains(sample.charAt(i))==false){
				while(j<sample.length()){
					if(sample.charAt(i)==sample.charAt(j)){
						counter ++;
					}
					j++;
				}
				j=0;
				squaredval = squaredval + java.lang.Math.pow(counter,2);
				duplicates.add(sample.charAt(i));
			}
			i++;
		}
		
		return (float) java.lang.Math.sqrt(squaredval);
	}
	
	public float similarity() {
		String sample1= this.s1;
		String sample2=this.s2;
		ArrayList<Character> arr = new ArrayList<Character>();
		int total = 0;
		int ins1, ins2;
		int j;
		
			for(j=0;j<sample1.length();j++){
				if(!arr.contains(sample1.charAt(j))){
					arr.add(sample1.charAt(j));
					ins1 = occurance(sample1,sample1.charAt(j));
					ins2 = occurance(sample2,sample1.charAt(j));
					if(ins2==0){
						
					}
					else{
						total = total + (ins1*ins2);
					}
				}
			}
		
		
		float end =0;
		end = (total/(lengthOfS2()*lengthOfS1()));
		return end;
	}
	public int occurance(String s, char i){
		int counter=0;
		for(int k = 0; k<s.length();k++){
			if(i==s.charAt(k)){
				counter++;
			}
		}
		return counter;
	}
}
