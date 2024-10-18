package lz9;

public class Principale1_2 {
	
	public static class ErroreLinea extends RuntimeException {
		int x;
		
		public ErroreLinea(int x) {
			super("errore linea");
			this.x = x;
		}
	}
	
	public static class ErroreSezione extends RuntimeException {
		public ErroreSezione(String msg) {
			super(msg);
		}
	}

	public static int trovalinea() {
		boolean datoassente = true;
		
		if (datoassente) {
			throw new ErroreLinea(12);
		}
		
		return 12;
	}
	
	public static int trovasezione() {
		boolean datoassente = true;
		
		if (datoassente) 
			throw new ErroreSezione("errore sezione");
		
		int x = trovalinea();
		return x;
		
	}
	
	public static int leggidafile(String nomefile) {
		try {
			int x = trovasezione();
			return x;
		}
		catch(ErroreLinea e) {
			System.out.println(e.getMessage());
			System.out.println("errore gestito in leggidafile " + e.x);
			return 0;
		}
	}

	public static void main(String[] args) {
		String nomefile = "abc";
		int dato;
		
		try {
			dato = leggidafile(nomefile);
			//System.out.println(dato);
		}
		catch(ErroreSezione e) {
			System.out.println(e.getMessage());
			System.out.println("gestito errore sezione nel main");
		}
		
		RuntimeException r = new RuntimeException("abcd");
		System.out.println(r.getMessage());
		
		ErroreSezione l = new ErroreSezione("abc");
		throw l;
		
	}
}
