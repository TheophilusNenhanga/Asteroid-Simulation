package jbq061.assignment4;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.List;

public class SpaceView extends StackPane implements Subscriber {

	Canvas canvas;
	GraphicsContext graphicsContext;

	public SpaceView(double canvasWidth) {
		this.canvas = new Canvas(canvasWidth, canvasWidth);
		graphicsContext = canvas.getGraphicsContext2D();
		this.setStyle("-fx-background-color: black");
		this.getChildren().add(canvas);
	}

	public void setupEvents(SpaceController controller) {
	}

	public void draw(List<Asteroid> asteroids, List<Star> stars, double worldRotation) {
		graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

		graphicsContext.setFill(Color.WHITE);
		for (Star star : stars) {
			double[] coordinates = star.getCoordinates();
			graphicsContext.fillOval(coordinates[0] * canvas.getWidth(), coordinates[1] * canvas.getHeight(), star.getRadius(), star.getRadius());
		}

		graphicsContext.setFill(Color.GRAY);
		for (Asteroid asteroid : asteroids) {
			double[] coordinates = asteroid.getCoordinates();
			double[] xPoints = asteroid.getXPoints();
			double[] yPoints = asteroid.getYPoints();

			graphicsContext.save();

			graphicsContext.translate(coordinates[0] * canvas.getWidth(), coordinates[1] * canvas.getHeight());
			graphicsContext.scale(canvas.getWidth(), canvas.getHeight());
			graphicsContext.rotate(asteroid.getAngle());
			graphicsContext.fillPolygon(xPoints, yPoints, 8);

			graphicsContext.restore();
		}
		// graphicsContext.rotate(worldRotation);
	}


	@Override
	public void receiveNotification(String channelName, List<Asteroid> asteroids, List<Star> stars, double worldRotation) {
		if (channelName.equals("create")) {
			draw(asteroids, stars,worldRotation);
		}
		if (channelName.equals("animate")) {
			draw(asteroids, stars, worldRotation);
		}
	}
}
