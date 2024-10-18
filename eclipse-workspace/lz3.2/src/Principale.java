
public class Principale {

	public static void main(String[] args) {
		
		Automobile a;
		a = new Automobile("Panda", "verde");
		System.out.println(a);
		System.out.println(a.getModello());
		
		a.setTarga("AB123CD");
		System.out.println(a);
		//a.setTarga("ZZ123CD");
		
		Persona p;
		p = new Persona("Mario", "Rossi");
		a.proprietario = p;
		System.out.println(a);
	}

}
