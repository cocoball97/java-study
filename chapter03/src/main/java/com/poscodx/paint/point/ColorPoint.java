package com.poscodx.paint.point;

public class ColorPoint extends Point {
	private String color;
	
	public ColorPoint(int x, int y, String color) {
//		setX(x);
//		setY(y);
//		부모생성자가 대신 역할. 항상 부모가 먼저
		super(x,y);
		this.color = color;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public void show() {
		System.out.println("Point[x=" + getX() + ", y="+ getY() + ", color=" + color + "]를 그렸습니다.");
		super.show();
	}
	
	@Override
	public void draw() {
		show();
	}
}
