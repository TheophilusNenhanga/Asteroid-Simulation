package jbq061.assignment4;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.List;

public class CursorView extends StackPane implements Subscriber {
	Canvas canvas;
	GraphicsContext graphicsContext;

	public CursorView(double width) {
		this.canvas = new Canvas(width, width);
		graphicsContext = canvas.getGraphicsContext2D();
		this.setStyle("-fx-background-color: black; -fx-border-color: #565656");
		this.getChildren().add(canvas);
	}

	@Override
	public void receiveNotification(String channelName, List<Asteroid> asteroids, List<Star> stars, double worldRotation) {

	}

	@Override
	public void receiveNotification(String channelName, List<Asteroid> asteroids, List<Star> stars, double worldRotation, MouseEvent event) {
		if (channelName.equals("show")) {
			draw(asteroids, stars, event);
		}
	}

	@Override
	public void receiveNotification(String channelName, List<Asteroid> asteroids, List<Star> stars, double worldRotation, InputEvent event, double areaCursorRadius) {
		// The cursor view does not show the area cursor
	}

	@Override
	public void receiveNotification(String channelName) {

	}

	public void draw(List<Asteroid> asteroids, List<Star> stars, MouseEvent event) {
		graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

		double translateX = (canvas.getWidth() / 2) - (event.getX() / 2);
		double translateY = (canvas.getHeight() / 2) - (event.getY() / 2);


		graphicsContext.translate(translateX, translateY);

		graphicsContext.scale(2, 2);


		{
			graphicsContext.save();
			// Draw the stars
			graphicsContext.setFill(Color.WHITE);
			for (Star star : stars) {
				double[] coordinates = star.getCoordinates();
				graphicsContext.fillOval(coordinates[0] * canvas.getWidth() * 2, coordinates[1] * canvas.getHeight() * 2,
						star.getRadius() * 2, star.getRadius() * 2);
			}
			graphicsContext.restore();
		}

		graphicsContext.setFill(Color.GRAY);
		for (Asteroid asteroid : asteroids) {
			double[] coordinates = asteroid.getCoordinates();
			double[] xPoints = asteroid.getXPoints();
			double[] yPoints = asteroid.getYPoints();

			graphicsContext.save();
			{
				graphicsContext.translate(coordinates[0] * canvas.getWidth(), coordinates[1] * canvas.getHeight());
				graphicsContext.scale(canvas.getWidth(), canvas.getHeight());
				graphicsContext.rotate(asteroid.getAngle());
				graphicsContext.fillPolygon(xPoints, yPoints, xPoints.length);
			}
			graphicsContext.restore();
		}
		graphicsContext.scale(0.5, 0.5);

		graphicsContext.translate(-translateX, -translateY);

	}
}
