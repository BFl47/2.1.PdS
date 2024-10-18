package lz9;

public class Principale1_1 {
	
	public static int trovalinea() {
		boolean datoassente = true;
		
		if (datoassente) {
			//2
			//lancia un'eccezione 
			//se non viene catturata il programma termina
			throw new RuntimeException("errore trovalinea");
		/*
		 	//1
			//stampa -1 	
			return -1;
			
			//stampa errore
			System.out.println("errore");
			System.exit(-1);
		*/
		}
		return 12;
	}
	
	public static int trovasezione() {
		boolean datoassente = true;
		
		if (datoassente) 
			throw new RuntimeException("errore trovasezione");
		
		int x = trovalinea();
		return x;
		
	}
	
	public static int leggidafile(String nomefile) {
		try {
			int x = trovasezione();
			System.out.println("leggidafile eseguito");
			return x;
		}
		
		catch(RuntimeException e) {
			System.out.println(e.getMessage());
			System.out.println("errore gestito in leggidafile");
			return 0;
		}
	}

	public static void main(String[] args) {
		String nomefile = "abc";
		int dato;
	/*
		//1
		dato = leggidafile(nomefile);
	*/
		//eseguito se non c'è try catch in leggidafile
		try {
			dato = leggidafile(nomefile);
		}
		catch(RuntimeException e) {
			System.out.println(e.getMessage());
			System.out.println("gestisci errore nel main");
			return;
		}
		
		System.out.println(dato);
	}
	
}
