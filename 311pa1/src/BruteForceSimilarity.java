import java.util.ArrayList;
import java.lang.Math;

/**
 * 
 * @author Christian Shinkle
 * @author Alec Harrison
 * @author Benjamin Trettin
 */
public class BruteForceSimilarity {
	float size1, size2;
	float similarity;
	float s1leng, s2leng;
	String s1, s2;
	int sLength;
	public int numerator;
	public BruteForceSimilarity(String s1, String s2, int sLength) {
		//Assuming slength is the length of s2
		this.s1=s1;
		this.s2=s2;
	
		this.sLength=sLength;
		s1leng=lengthOfS1();

		s2leng=lengthOfS2();
	
		
	}
	
	public float lengthOfS1() {
		String sample = this.s1;
		float squaredval=(float) 0.0;
		int counter=0;
		int j = 0;
		ArrayList<String> stringHolder = new ArrayList<String>();
		int i = 0;
		while(i<sample.length()-sLength+1){
			stringHolder.add(substring(i,sample));
			i++;
		}
		i=0;
		
		while(stringHolder.size()!=0){
			String holder = stringHolder.get(0);
			counter = 0;
			while(j<stringHolder.size()){
				if(holder.equals(stringHolder.get(j))){
					stringHolder.remove(j);
					counter ++;
					
				}
				else{
				j++;
				}
			}
			squaredval = squaredval + (counter*counter);
			i++;
			j=0;
		}
		
		return (float) Math.sqrt(squaredval);
	}
	
	public float lengthOfS2() {
		String sample = this.s2;
		double squaredval=0;
		int counter=0;
		int j = 0;
		ArrayList<String> stringHolder = new ArrayList<String>();
		int i = 0;
		while(i<sample.length()-sLength+1){
			stringHolder.add(substring(i,sample));
			i++;
		}
		i=0;
		
		while(stringHolder.size()!=0){
			String holder = stringHolder.get(0);
			counter = 0;
			while(j<stringHolder.size()){
				if(holder.equals(stringHolder.get(j))){
					stringHolder.remove(j);
					counter ++;
					
				}
				else{
				j++;
				}
			}
			squaredval = squaredval + (counter*counter);
			i++;
			j=0;
		}
		
		return (float) java.lang.Math.sqrt(squaredval);
	}
	
	public float similarity() {
		String sample1= this.s1;
		String sample2=this.s2;
		ArrayList<String> arr1 = new ArrayList<String>();
		ArrayList<String> arr2 = new ArrayList<String>();
		int total = 0;
		int ins1, ins2;
		ins1=0;
		ins2=0;
		int i=0;
		int j=0;
		while(i<sample1.length()-sLength+1){
			arr1.add(substring(i,sample1));
			i++;
		}
		while(j<sample1.length()-sLength+1){
			arr2.add(substring(j,sample2));
			j++;
		}
		
		String holder;
			while(arr1.size()!=0){
				i=0;
				j=0;
				ins1=0;
				ins2=0;
				holder=arr1.get(0);
				while(i<arr1.size()){
					if(holder.equals(arr1.get(i))){
						arr1.remove(i);
						ins1 ++;
						
					}
					else{
					i++;
					}
				}
				while(j<arr2.size()){
					if(holder.equals(arr2.get(j))){
						arr2.remove(j);
						ins2 ++;
						
					}
					else{
					j++;
					}
				}
				total = total + (ins1*ins2);
				
		
			}
		float end =0;
		numerator=total;
		end = (total/(s2leng*s1leng));
		return end;
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
