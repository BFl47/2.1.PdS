
public class Collegata implements Cloneable {
	int x;
	
	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			//e.printStackTrace();
			return null;
		}
	}
	
}
