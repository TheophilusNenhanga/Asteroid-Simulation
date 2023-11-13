package jbq061.assignment4;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;

public class SpaceView extends StackPane implements Subscriber {

	Canvas canvas;
	GraphicsContext graphicsContext;

	public SpaceView(double canvasWidth){
		this.canvas = new Canvas(canvasWidth, canvasWidth);
		graphicsContext = canvas.getGraphicsContext2D();
		this.setStyle("-fx-background-color: black");
		this.getChildren().add(canvas);
	}

	public void setupEvents(SpaceController controller){

	}

	public void draw(){

	}


	@Override
	public void receiveNotification(String channelName, Object changedState) {

	}
}
