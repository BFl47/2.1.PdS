package lz11;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Scrittura {

	public static void main(String[] args) throws IOException {
		FileWriter w = new FileWriter("prova1.txt");
		//w.write("abc");
		//w.close();
		
		PrintWriter p = new PrintWriter(w);
		p.println("abcd");
		
		p.flush();
		//...
		p.close();
		
		System.out.println("scritto");
		

	}

}
