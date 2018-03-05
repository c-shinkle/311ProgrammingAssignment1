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
		ArrayList<String> duplicates = new ArrayList<String>();
		int i = 0;
		while(i<sample.length()){
			counter =0;
			if(duplicates.contains(substring(i,sample))==false){
				while(j<sample.length()){
					if(substring(i,sample).equals(substring(j,sample))){
						counter ++;
					}
					j++;
				}
				j=0;
				squaredval = squaredval + java.lang.Math.pow(counter,2);
				duplicates.add(substring(i,sample));
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
		ArrayList<String> duplicates = new ArrayList<String>();
		int i = 0;
		while(i<sample.length()){
			counter =0;
			if(duplicates.contains(substring(i,sample))==false){
				while(j<sample.length()){
					if(substring(i,sample).equals(substring(j,sample))){
						counter ++;
					}
					j++;
				}
				j=0;
				squaredval = squaredval + java.lang.Math.pow(counter,2);
				duplicates.add(substring(i,sample));
			}
			i++;
		}
		
		return (float) java.lang.Math.sqrt(squaredval);
	}
	
	public float similarity() {
		String sample1= this.s1;
		String sample2=this.s2;
		ArrayList<String> arr = new ArrayList<String>();
		int total = 0;
		int ins1, ins2;
		int j;
		
			for(j=0;j<sample1.length();j++){
				if(!arr.contains(substring(j,sample1))){
					arr.add((substring(j,sample1)));
					ins1 = occurance(sample1,substring(j,sample1));
					ins2 = occurance(sample2,substring(j,sample1));
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
	public int occurance(String s, String i){
		int counter=0;
		for(int k = 0; k<s.length();k++){
			String holder = substring(k,s);
			if(i.equals(holder)){
				counter++;
			}
		}
		return counter;
	}
	public String substring(int startingindex, String s){
		String sub = "";
		if(startingindex+sLength>s.length()){
			return sub;
		}
		else{
			for(int i = 0; i<this.sLength;i++){
				sub = sub + s.charAt(startingindex+i);
			}
		}
		return sub;
	}
}
