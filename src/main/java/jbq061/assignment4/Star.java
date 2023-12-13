package jbq061.assignment4;

public class Star {
	private final double[] coordinates;

	private final double radius;

	public Star(double normalizedX, double normalizedY, double radius) {
		coordinates = new double[]{normalizedX, normalizedY};
		this.radius = radius;
	}

	public double[] getCoordinates() {
		return coordinates;
	}

	public double getRadius() {
		return radius;
	}
}
