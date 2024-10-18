package lz11;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.zip.ZipInputStream;
//import javax.crypto.CipherInputStream;

public class Lettura {

	public static void main(String[] args) throws IOException {
		System.out.println("ok");
		//char c = (char) System.in.read();
		//System.out.println(c);
		
		System.out.println(args[0]);
		
		Scanner sc = new Scanner(System.in);		//attende utente
		String s;
		s = sc.next();
		System.out.println("ho letto da console: " + s);
		sc.close();
		
		FileInputStream r;
		r = new FileInputStream("prova.txt");
		Scanner sc1 = new Scanner(r);
		
		//s = sc1.next();
		//System.out.println("ho letto: " + s);		//parola
		
		if (sc1.hasNextLine()) {
			s = sc1.nextLine();						//riga
			System.out.println("ho letto da file: " + s);
		}	
		r.close();
		sc1.close();
		
		Scanner sc2 = new Scanner("abc xyz");
		s = sc2.next();
		System.out.println("ho letto: " + s);
		sc2.close();
		
		FileInputStream r1 = new FileInputStream("prova.zip");
		ZipInputStream z = new ZipInputStream(r1);
		z.getNextEntry();
		
		Scanner sc3 = new Scanner(z);
		s = sc3.nextLine();
		System.out.println("ho letto da zip: " + s);
		//CipherInputStream c = new CipherInputStream(z, null);
		sc3.close();
		
		FileInputStream r2 = new FileInputStream("prova.txt");
		MioInputStream m = new MioInputStream(r2);
		
		Scanner sc4 = new Scanner(m);
		s = sc4.next();
		System.out.println("ho letto da mio: " + s);
		sc4.close();
		
	}

}
