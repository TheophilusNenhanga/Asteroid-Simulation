package jbq061.assignment4;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;

import java.util.List;

public class CursorView extends StackPane implements Subscriber {
	Canvas canvas;
	GraphicsContext graphicsContext;
	public CursorView(double width){
		this.canvas = new Canvas(width, width);
		graphicsContext = canvas.getGraphicsContext2D();
		this.setStyle("-fx-background-color: black; -fx-border-color: #565656");
		this.getChildren().add(canvas);
	}
	@Override
	public void receiveNotification(String channelName, List<Asteroid> asteroids, List<Star> stars, double worldRotation) {

	}
}
