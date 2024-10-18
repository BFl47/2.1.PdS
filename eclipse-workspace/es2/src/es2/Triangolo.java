package es2;

public class Triangolo extends FiguraGeometrica {
	double latoA;
	double latoB;
	double latoC;
	
	public Triangolo(double a, double b, double c) {
		this.latoA = a;
		this.latoB = b;
		this.latoC = c;
	}
	
	public double area() {
		double p = this.perimetro() / 2;
		return Math.sqrt(p * (p-this.latoA) * (p-this.latoB) * (p-this.latoC));
	}
	
	public double perimetro() {
		return this.latoA + this.latoB + this.latoC;
	}
	public String toString() {
		return super.toString() + " " + this.latoA + " " + this.latoB + " " + this.latoC;
	}

}
