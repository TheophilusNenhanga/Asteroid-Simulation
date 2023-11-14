package jbq061.assignment4;

public class Star {
	private double[] coordinates;

	private double radius;

	public Star(double normalizedX, double normalizedY, double radius) {
		coordinates = new double[]{normalizedX, normalizedY};
		this.radius = radius;
	}

	public double[] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(double[] coordinates) {
		this.coordinates = coordinates;
	}

	public double getRadius() {
		return radius;
	}
}
