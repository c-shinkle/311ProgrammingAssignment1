/**
 * 
 * @author Christian Shinkle
 * @author Alec Harrison
 * @author Benjamin Trettin
 */
public class Tuple {

	private int key;
	private String value;

	Tuple(int keyP, String valueP) {
		this.key = keyP;
		this.value = valueP;
	}

	public int getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public boolean equals(Tuple t) {
		if (this.key == t.key && this.value.equals(t.value)) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "(" + key + ", " + value + ")";
	}
}
