package es2;

public class Ellisse extends FiguraGeometrica {
	double sAsseMagg;
	double sAsseMin;
	
	public Ellisse(double a, double b) {
		this.sAsseMagg = a;
		this.sAsseMin = b;
	}
	
	public double area() {
		return Math.PI * this.sAsseMagg * this.sAsseMin;
	}
	
	public double perimetro() {
		return 2 * Math.PI * 
				Math.sqrt((Math.pow(this.sAsseMagg, 2) + Math.pow(this.sAsseMin, 2))/2);
	}
	
	public String toString() {
		return super.toString() + " " + this.sAsseMagg + " " + this.sAsseMin;
	}
}
