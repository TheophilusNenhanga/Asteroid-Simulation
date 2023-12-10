package jbq061.assignment4;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.List;

public class SpaceView extends StackPane implements Subscriber {

	double[] coordinates;

	Canvas canvas;
	GraphicsContext graphicsContext;

	public SpaceView(double normalizedX, double normalizedY, double canvasWidth) {
		this.coordinates = new double[]{normalizedX, normalizedY};
		this.canvas = new Canvas(canvasWidth, canvasWidth);
		graphicsContext = canvas.getGraphicsContext2D();
		this.setStyle("-fx-background-color: black; -fx-border-color: #565656");
		this.getChildren().add(canvas);
	}

	public void setupEvents(SpaceController controller) {
		this.setOnMouseMoved(controller::handleMouseMoved);
		this.setOnMousePressed(controller::handleMousePressed);
		this.setOnMouseDragged(controller::handleMouseDragged);
		this.setOnMouseReleased(controller::handleMouseReleased);
	}

	public void draw(List<Asteroid> asteroids, List<Star> stars, double worldRotation) {
		graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());


		{
			// translate and rotate before asteroids are drawn
			// Rotate the world
			graphicsContext.translate(canvas.getWidth() / 2, canvas.getHeight() / 2); // Translate to teh centre of the screen
			graphicsContext.rotate(worldRotation);
			graphicsContext.translate(-canvas.getWidth() / 2, -canvas.getHeight() / 2); // Translate back to the origin point
		}


		{
			graphicsContext.save();
			// Draw the stars
			graphicsContext.setFill(Color.WHITE);
			for (Star star : stars) {
				double[] coordinates = star.getCoordinates();
				graphicsContext.fillOval(coordinates[0] * canvas.getWidth(), coordinates[1] * canvas.getHeight(),
						star.getRadius(), star.getRadius());
			}
			graphicsContext.restore();
		}


		for (Asteroid asteroid : asteroids) {
			double[] coordinates = asteroid.getCoordinates();
			double[] xPoints = asteroid.getXPoints();
			double[] yPoints = asteroid.getYPoints();

			// Draw white outline
			graphicsContext.save();
			graphicsContext.setFill(Color.WHITE);
			graphicsContext.translate(coordinates[0] * canvas.getWidth(), coordinates[1] * canvas.getHeight());
			graphicsContext.scale(canvas.getWidth() + 25, canvas.getHeight() + 25);
			graphicsContext.rotate(asteroid.getAngle());
			graphicsContext.fillPolygon(xPoints, yPoints, xPoints.length);
			graphicsContext.restore();

			// Draw the actual asteroid
			graphicsContext.save();
			graphicsContext.setFill(asteroid.isSelected() ? Color.YELLOW : Color.GRAY);
			graphicsContext.translate(coordinates[0] * canvas.getWidth(), coordinates[1] * canvas.getHeight());
			graphicsContext.scale(canvas.getWidth(), canvas.getHeight());
			graphicsContext.rotate(asteroid.getAngle());
			graphicsContext.fillPolygon(xPoints, yPoints, xPoints.length);
			graphicsContext.restore();
		}
	}

	@Override
	public void receiveNotification(String channelName, List<Asteroid> asteroids, List<Star> stars,
	                                double worldRotation) {
		if (channelName.equals("create")) {
			draw(asteroids, stars, worldRotation);
		}
		if (channelName.equals("animate")) {
			draw(asteroids, stars, worldRotation);
		}
	}

	@Override
	public void receiveNotification(String channelName, List<Asteroid> asteroids, List<Star> stars, double worldRotation, MouseEvent event) {
		if (channelName.equals("create")) {
			draw(asteroids, stars, worldRotation);
		}
		if (channelName.equals("animate")) {
			draw(asteroids, stars, worldRotation);
		}
	}
}
