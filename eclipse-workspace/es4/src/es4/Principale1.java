package es4;

import javax.swing.JOptionPane;

public class Principale1 {

	public static String convertiM(String s) {
		String a = "";
		
		for (int i = 0; i<s.length(); i++) {
			String c = "" + s.charAt(i);
			if (i % 2 == 0) 
				a += c.toLowerCase();
			
			else 
				a += c.toUpperCase();		
		}
		
		return a;
	}
	
	public static void stampaSomma() {
		String s = JOptionPane.showInputDialog("stringa: ");
		int somma = 0;
		
		for (int i = 0; i < s.length(); i++) {
			somma += s.codePointAt(i);
		}
		
		System.out.println(somma);
	}
	
	public static void main(String[] args) {
		
		String s = "aBcd";
		String s1 = convertiM(s);
		System.out.println(s1);
		stampaSomma();

	}


}
