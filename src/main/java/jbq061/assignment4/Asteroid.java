package jbq061.assignment4;

import java.util.Random;

public class Asteroid {
	private double[] coordinates;
	private final double xVelocity;
	private final double yVelocity;
	private final double aVelocity;
	private double angle;
	private double[] xPoints;
	private double[] yPoints;

	Random random = new Random();

	public Asteroid(double normalizedX, double normalizedY) {
		this.coordinates = new double[]{normalizedX, normalizedY};
		this.xPoints = generateAsteroidPoints()[0];
		this.yPoints = generateAsteroidPoints()[1];
		this.angle = random.nextInt(0, 360);
		this.xVelocity = random.nextDouble(0.001);
		this.yVelocity = random.nextDouble(0.001);
		this.aVelocity = random.nextDouble(1.8);
	}

	public double[] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(double[] coordinates) {
		this.coordinates = coordinates;
	}


	private double[][] generateAsteroidPoints() {
		double[][] points = new double[2][8];
		double[] xPoints = new double[8];
		double[] yPoints = new double[8];

		for (int i = 0; i < 8; i++) {
			double a = i * (Math.PI / 4);
			double _radius = random.nextDouble(0.035, 0.075);
			double x = _radius * Math.cos(a);
			double y = _radius * Math.sin(a);
			xPoints[i] = x;
			yPoints[i] = y;
		}
		points[0] = xPoints;
		points[1] = yPoints;
		return points;
	}

	public double[] getXPoints() {
		return xPoints;
	}

	public double[] getYPoints() {
		return yPoints;
	}

	public double getAngle() {
		return angle;
	}

	public double getXVelocity() {
		return xVelocity;
	}

	public double getYVelocity() {
		return yVelocity;
	}

	public double getAVelocity(){
		return aVelocity;
	}

	public void setAngle(double newAngle) {
		this.angle = newAngle;
	}
}
