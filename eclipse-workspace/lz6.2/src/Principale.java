import javax.swing.JOptionPane;

public class Principale {

	public static void main(String[] args) {
		Object a = new Object();
		Object o = new Persona();
		
		System.out.println(a);
		System.out.println(o);
		
		Object o1;
		String s = JOptionPane.showInputDialog("Oggetto o persona?");
		if (s.equalsIgnoreCase("persona"))
			o1 = new Persona();
		else
			o1 = new Object();
		
		System.out.println(o1);

	}

}
