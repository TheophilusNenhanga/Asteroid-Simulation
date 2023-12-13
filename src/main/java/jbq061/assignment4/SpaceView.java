package jbq061.assignment4;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.List;

public class SpaceView extends StackPane implements Subscriber {

	double[] coordinates;

	Canvas canvas;
	GraphicsContext graphicsContext;

	private boolean areaCursor;

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
		this.setOnScroll(controller::handleWheel);
	}

	public void draw(List<Asteroid> asteroids, List<Star> stars, double worldRotationVelocity, double[] areaCursorDimensions) {
		graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());


		{
			// translate and rotate before asteroids are drawn
			// Rotate the world
			graphicsContext.translate(canvas.getWidth() / 2, canvas.getHeight() / 2); // Translate to teh centre of the screen
			graphicsContext.rotate(worldRotationVelocity);
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

		if (areaCursor && areaCursorDimensions == null) throw new RuntimeException("area cursor dimensions expected");
		if (areaCursor) {
			{
//				System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
//				System.out.println(Arrays.toString(areaCursorDimensions));
				graphicsContext.setFill(Color.rgb(50, 50, 50, 0.5));
				graphicsContext.fillOval(areaCursorDimensions[0], areaCursorDimensions[1], areaCursorDimensions[2], areaCursorDimensions[2]);
			}
		}
	}

	@Override
	public void receiveNotification(String channelName, List<Asteroid> asteroids, List<Star> stars,
	                                double worldRotation) {
		if (channelName.equals("create")) {
			draw(asteroids, stars, worldRotation, null);
		}
		if (channelName.equals("animate")) {
			draw(asteroids, stars, worldRotation, null);
		}
	}

	@Override
	public void receiveNotification(String channelName, List<Asteroid> asteroids, List<Star> stars, double worldRotation, MouseEvent event) {
		if (channelName.equals("create")) {
			draw(asteroids, stars, worldRotation, null);
		}
		if (channelName.equals("animate")) {
			draw(asteroids, stars, worldRotation, null);
		}
	}

	@Override
	public void receiveNotification(String channelName, List<Asteroid> asteroids, List<Star> stars, double worldRotation, InputEvent event, double areaCursorRadius) {

		if (event instanceof MouseEvent) {
			if (channelName.equals("areaCursor")) {
				double[] areaCursorDims = new double[]{((MouseEvent) event).getX(), ((MouseEvent) event).getY(), areaCursorRadius};
				draw(asteroids, stars, worldRotation, areaCursorDims);
				this.areaCursor = true;
			}
		}
		if (event instanceof ScrollEvent) {
			if (channelName.equals("areaCursor")) {
				double[] areaCursorDims = new double[]{((ScrollEvent) event).getX(), ((ScrollEvent) event).getY(), areaCursorRadius};
				draw(asteroids, stars, worldRotation, areaCursorDims);
				this.areaCursor = true;
			}
		}
	}

	@Override
	public void receiveNotification(String channelName) {
		if (channelName.equals("areaCursor-off")) {
			this.areaCursor = false;
		}
	}
}
