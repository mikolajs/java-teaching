
public class Circle {
	double r;

	public Circle(double R) {
		r = R;
	}
	
	public double area() {
		return Math.PI * r * r;
	}
	
	public double circuit() {
		return 2.0 * Math.PI * r;
	}

}
