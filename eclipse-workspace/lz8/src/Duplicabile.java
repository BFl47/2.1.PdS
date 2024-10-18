
public class Duplicabile extends Object implements Cloneable {
	Collegata c;
	
	@Override
	/*
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
	 */
	public Object clone() {
		try {
			Duplicabile n = (Duplicabile) super.clone();
			n.c = (Collegata) this.c.clone();
			return n;
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
