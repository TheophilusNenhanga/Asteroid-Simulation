package jbq061.assignment4;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ControlPanelView extends VBox {

	Slider slider;
	CheckBox asteroidMovement;
	CheckBox asteroidSpin;

	SpaceController controller;

	public ControlPanelView() {
		Label label = new Label("Rotation Speed");
		label.setTextFill(Color.WHITE);
		slider = new Slider(0, 0.15 * Math.PI, 0.075 * Math.PI);
		asteroidMovement = new CheckBox("Asteroid Movement");
		asteroidMovement.setSelected(true);
		asteroidMovement.setTextFill(Color.WHITE);
		asteroidSpin = new CheckBox("Asteroid Spin");
		asteroidSpin.setSelected(true);
		asteroidSpin.setTextFill(Color.WHITE);
		this.getChildren().addAll(label, slider, asteroidMovement, asteroidSpin);
		this.setStyle("-fx-background-color: rgba(0, 0, 0, 0)");

		slider.valueProperty().addListener(event -> {
			controller.changeRotationVelocity(slider.getValue());
		});
		asteroidMovement.selectedProperty().addListener(event -> controller.toggleAsteroidMovement());
		asteroidSpin.selectedProperty().addListener(event -> controller.toggleAsteroidRotation());
	}

	public void setController(SpaceController controller) {
		this.controller = controller;
	}
}
