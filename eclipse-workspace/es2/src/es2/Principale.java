package es2;

import java.util.LinkedList;
import java.util.Random;

public class Principale {
	//generare array di figure geom casuali
	public static FiguraGeometrica[] creaFigura(int n) {
		FiguraGeometrica[] a = new FiguraGeometrica[n];
		
		for (int i = 0; i < a.length; i++) {
			Random r = new Random();
			int tipo = r.nextInt(5);
			switch (tipo) {
			case 0:
				a[i] = new Rettangolo(r.nextDouble(10), r.nextDouble(10));
				a[i].descrizione = "Rettangolo";
				break;
			case 1:
				a[i] = new Quadrato(r.nextDouble(10));
				a[i].descrizione = "Quadrato";
				break;
			case 2:
				a[i] = new Triangolo(r.nextDouble(10), r.nextDouble(10), r.nextDouble(10));
				a[i].descrizione = "Triangolo";
				break;
			case 3:
				a[i] = new Ellisse(r.nextDouble(10), r.nextDouble(10));
				a[i].descrizione = "Ellisse";
			case 4:
				a[i] = new Cerchio(r.nextDouble(10));
				a[i].descrizione = "Cerchio";
				break;
			}
		}
		return a;
	}
	
	public static double sommaAree(LinkedList<FiguraGeometrica> l) {
		double s = 0;
		for (FiguraGeometrica f : l) {
			s += f.area();
		}
		return s;
	}
	
	public static double sommaAree(FiguraGeometrica[] a) {
		double s = 0;
		for (FiguraGeometrica f: a) {
			s += f.area();
		}
		return s;
	}
 
	public static void main(String[] args) {
		Rettangolo r = new Rettangolo(2, 3);
		r.descrizione = "Rettangolo";
		
		Quadrato q = new Quadrato(3);
		q.descrizione = "Quadrato";		
		//FiguraGeometrica g = new FiguraGeometrica();
		//System.out.println(g.area());
		
		Triangolo t = new Triangolo(3, 4, 5);
		t.descrizione = "Triangolo";
		
		Ellisse e = new Ellisse(3, 4);
		e.descrizione = "Ellisse";
		
		FiguraGeometrica c = new Cerchio(2);
		//c.raggio = 3;
		c.descrizione = "Cerchio"; 
		
		LinkedList<FiguraGeometrica> l = new LinkedList<FiguraGeometrica>();
		l.add(r);
		l.add(q);
		l.add(t);
		l.add(e);
		l.add(c);
		System.out.println(l);
		System.out.println("somma aree ll: " + sommaAree(l));
		
		FiguraGeometrica[] a = {r, q, t, e, c};
		System.out.println("somma aree array: " + sommaAree(a));
		
		// crea oggetto Random
        Random random = new Random();
        // genera double casuale tra 0 e 4
        double number = random.nextDouble(10);
        // genera intero casuale tra 0 e 4
        int n = random.nextInt(5);
        System.out.println(number + " " + n);
        
        FiguraGeometrica[] a1 = creaFigura(5);
        for (int i = 0; i < a1.length; i++) {
        	System.out.println(a1[i]);
        }
        
        System.out.println(sommaAree(a1));
        
        
	}

}
