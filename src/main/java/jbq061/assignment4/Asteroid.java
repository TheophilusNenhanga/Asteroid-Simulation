package jbq061.assignment4;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.Random;

public class Asteroid {
	private double[] coordinates;
	private double xVelocity;
	private double yVelocity;
	private final double aVelocity;
	private double angle;
	private final double[] xPoints;
	private final double[] yPoints;
	private boolean selected;

	Random random = new Random();
	double canvasWidth, canvasHeight;
	PixelReader pixelReader;
	Canvas canvas;
	private int zOrder;

	public Asteroid(double normalizedX, double normalizedY, double canvasWidth, double canvasHeight) {
		this.coordinates = new double[]{normalizedX, normalizedY};
		this.xPoints = generateAsteroidPoints()[0];
		this.yPoints = generateAsteroidPoints()[1];
		this.angle = random.nextDouble(0, 2 * Math.PI);
		this.xVelocity = random.nextDouble(0.001);
		this.yVelocity = random.nextDouble(0.001);
		this.aVelocity = random.nextDouble(1.8);
		this.canvasWidth = canvasWidth;
		this.canvasHeight = canvasHeight;
		this.selected = false;
		this.zOrder = 0;

		canvas = new Canvas(canvasWidth, canvasHeight);
		GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
		graphicsContext.setFill(Color.WHITE);
		graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		graphicsContext.setFill(Color.BLACK);
		graphicsContext.translate(coordinates[0] * canvas.getWidth(), coordinates[1] * canvas.getHeight());
		graphicsContext.scale(canvas.getWidth(), canvas.getHeight());
		graphicsContext.fillPolygon(xPoints, yPoints, xPoints.length);
		WritableImage snapshot = canvas.snapshot(null, new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight()));
		pixelReader = snapshot.getPixelReader();
	}

	public double[] getCoordinates() {
		return coordinates;
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

	public double getAVelocity() {
		return aVelocity;
	}

	public void setAngle(double newAngle) {
		this.angle = newAngle;
	}

	public boolean contains(double mouseX, double mouseY) {
		int px = (int) mouseX;
		int py = (int) mouseY;
		if (px >= 0 && px < canvas.getWidth() && py >= 0 && py < canvas.getHeight()) {
			return pixelReader.getColor(px, py).equals(Color.BLACK);
		}
		return false;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public int getzOrder() {
		return zOrder;
	}

	public void setzOrder(int zOrder) {
		this.zOrder = zOrder;
	}

	public void setxVelocity(double xVelocity) {
		this.xVelocity = xVelocity;
	}

	public void setyVelocity(double yVelocity) {
		this.yVelocity = yVelocity;
	}

	public void setCoordinates(double[] coordinates) {
		this.coordinates = coordinates;
	}
}
