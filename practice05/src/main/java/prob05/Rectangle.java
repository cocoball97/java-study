package prob05;

public class Rectangle extends Shape implements Resizable {
	public Rectangle (double width, double height) {
		this.width = width;
		this.height = heigth;
	}

	@Override
	public void resize(double rate) {
		width *= rate;
		height *= rate;
		
	}

	@Override
	public double getArea() {
		return width * height;
	}

	@Override
	public double getPerimerter() {
		return (width + height) * 2;
	}

}
