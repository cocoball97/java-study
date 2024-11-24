package prob05;

public class RectTriangle extends Shape {
	public RectTriangle (double width, double height) {
		this.width = width;
		this.height = heigth;
	}

	@Override
	public double getArea() {
		return (width * height)/2;
	}

	@Override
	public double getPerimerter() {
		return (width + height + Math.sqrt(width * width + height * height));
	}

}
