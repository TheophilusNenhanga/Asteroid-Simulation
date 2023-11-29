package jbq061.assignment4;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ControlPanelView extends VBox {
	public ControlPanelView(){
		Label label = new Label("Rotation Speed");
		label.setTextFill(Color.WHITE);
		Slider slider = new Slider(0, 1, 0.5);
		CheckBox asteroidMovement = new CheckBox("Asteroid Movement");
		asteroidMovement.setTextFill(Color.WHITE);
		CheckBox asteroidSpin = new CheckBox("Asteroid Spin");
		asteroidSpin.setTextFill(Color.WHITE);
		this.getChildren().addAll(label, slider, asteroidMovement, asteroidSpin);
		this.setStyle("-fx-background-color: rgba(0, 0, 0, 0)");
	}
}
