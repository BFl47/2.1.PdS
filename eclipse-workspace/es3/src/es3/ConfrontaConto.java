package es3;

import java.util.Comparator;

public class ConfrontaConto implements Comparator<ContoCorrente> {
	@Override
	//confronto per codice  (ordine alfabetico) 
	public int compare(ContoCorrente c1, ContoCorrente c2) {
		return c1.codice.compareToIgnoreCase(c2.codice);
	}
}
