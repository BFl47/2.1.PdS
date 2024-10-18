
public class Sotto extends Duplicabile {
	Collegata a;
	
	@Override
	public Object clone() {
		Sotto s = (Sotto) super.clone();
		s.a = (Collegata) this.a.clone();
		return s;
	}
	
}
