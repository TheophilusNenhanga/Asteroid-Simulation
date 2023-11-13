package jbq061.assignment4;

public class Asteroid {
	private double radius;
	private double[] coordinates;

	public Asteroid(){
		coordinates = new double[2];
	}

	public Asteroid(double normalizedX, double normalizedY, double radius){
		this.coordinates = new double[]{normalizedX, normalizedY};
		this.radius = radius;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public double[] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(double[] coordinates) {
		this.coordinates = coordinates;
	}
}
