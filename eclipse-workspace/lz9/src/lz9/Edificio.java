package lz9;

public class Edificio implements haLunghezza {
	int lunghezza = 10;
	
	@Override
	public String toString() {
		return "edificio";
	}

	@Override
	public int getLunghezza() {
		return this.lunghezza;
	}
}
