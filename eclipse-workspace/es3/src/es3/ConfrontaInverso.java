package es3;

import java.util.Comparator;

public class ConfrontaInverso implements Comparator<ContoCorrente> {
	int x;
	@Override
	public int compare(ContoCorrente c1, ContoCorrente c2) {
		if (c1.getSaldo() == x)
			return -1;
		if (c2.getSaldo() == x)
			return 1;
		return c2.compareTo(c1);
	}
}
