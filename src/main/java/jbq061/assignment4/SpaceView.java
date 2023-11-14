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

	public void draw(List<Asteroid> asteroids, List<Star> stars) {
		graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		graphicsContext.setFill(Color.GRAY);
		for (Asteroid asteroid : asteroids) {
			graphicsContext.fillOval(asteroid.getCoordinates()[0] * canvas.getWidth(), asteroid.getCoordinates()[1] * canvas.getHeight(), asteroid.getRadius(), asteroid.getRadius());
		}
		graphicsContext.setFill(Color.WHITE);
		for (Star star : stars) {
			graphicsContext.fillOval(star.getCoordinates()[0] * canvas.getWidth(), star.getCoordinates()[1] * canvas.getHeight(), star.getRadius(), star.getRadius());
		}
	}


	@Override
	public void receiveNotification(String channelName, List<Asteroid> asteroids, List<Star> stars) {
		if (channelName.equals("create")) {
			draw(asteroids, stars);
		}
	}
}
