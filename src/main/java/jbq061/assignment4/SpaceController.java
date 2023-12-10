package jbq061.assignment4;

import javafx.scene.input.MouseEvent;

import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class SpaceController {
	private Random random;
	private SpaceModel model;
	private IModel iModel;

	private PublishSubscribe publishSubscribe;
	private MouseEvent lastEvent;
	boolean asteroidMovement, asteroidRotation;

	public SpaceController(PublishSubscribe publishSubscribe) {
		this.random = new Random(345654566); // Adding a random seed - its random but fixed between executions.
		this.publishSubscribe = publishSubscribe;
		asteroidRotation = true;
		asteroidMovement = true;
	}

	public void setModel(SpaceModel model) {
		this.model = model;
	}

	public void setIModel(IModel iModel) {
		this.iModel = iModel;
	}

	public void startSystem() {
		// Make stars
		for (int i = 0; i < 200; i++) {
			model.createStar(random.nextDouble(1), random.nextDouble(1), 1);
		}
		// Make Asteroids
		for (int i = 0; i < 10; i++) {
			model.createAsteroid(random.nextDouble(1), random.nextDouble(1));
		}
	}

	public void moveAsteroids(Asteroid asteroid) {
		asteroid.getCoordinates()[0] += asteroid.getXVelocity();
		if (asteroid.getCoordinates()[0] >= 1) asteroid.getCoordinates()[0] = 0;

		asteroid.getCoordinates()[1] += asteroid.getYVelocity();
		if (asteroid.getCoordinates()[1] >= 1) asteroid.getCoordinates()[1] = 0;

	}

	public void spinAsteroids(Asteroid asteroid) {
		asteroid.setAngle(asteroid.getAngle() + asteroid.getAVelocity());
	}

	public void handleTimerTick() {
		// iModel.setWorldRotation(iModel.getWorldRotation() + iModel.getWorldRotationVelocity());
		iModel.setWorldRotation(iModel.getWorldRotationVelocity());
		// if (iModel.getWorldRotation() >= 0.2) iModel.setWorldRotation(0.1);
		// When the world gets back to its original position rest the world rotation.

		for (Asteroid asteroid : model.getAsteroids()) {
			if (asteroidMovement) moveAsteroids(asteroid);

			if (asteroidRotation) spinAsteroids(asteroid);
		}
		// System.out.println("World rotation: " + iModel.getWorldRotation() + "World Rotation Velocity: " + iModel.getWorldRotationVelocity());
		publishSubscribe.publish("animate", model.getAsteroids(), model.getStars(), iModel.getWorldRotation());
		if (lastEvent != null)
			publishSubscribe.publish("show", model.getAsteroids(), model.getStars(), iModel.getWorldRotation(), lastEvent);
	}

	public void handleMouseMoved(MouseEvent event) {
		publishSubscribe.publish("show", model.getAsteroids(), model.getStars(), iModel.getWorldRotation(), event);
		this.lastEvent = event;
	}

	public void handleMousePressed(MouseEvent event) {

		double mouseX = event.getX();
		double mouseY = event.getY();

		// Transform mouse location based on the world rotation
		double rotatedMouseX = rotateX(mouseX, mouseY, -iModel.getWorldRotation());
		double rotatedMouseY = rotateY(mouseX, mouseY, -iModel.getWorldRotation());

		System.out.println("unrotated: " + mouseX + " " + mouseY);
		System.out.println("rotated: " + rotatedMouseX + " " + rotatedMouseY);
		System.out.println(iModel.getWorldRotation());

		model.getAsteroids().forEach(asteroid -> {
			if (asteroid.contains(event.getX(), event.getY())) {
			//if (asteroid.contains(rotatedMouseX, rotatedMouseY)) { // regardless of whether I use the rotated or un-rotated values the selection is still incorrect.
				asteroid.setSelected(true);
				asteroid.setzOrder(asteroid.getzOrder() + 1);
				if (!(iModel.getSelected().contains(asteroid))) iModel.getSelected().add(asteroid);

			}
		});
		model.getAsteroids().sort(Comparator.comparingInt(Asteroid::getzOrder));
	}

	public void handleMouseDragged(MouseEvent event) {

	}

	public void handleMouseReleased(MouseEvent event) {

	}

	public void changeRotationVelocity(double rotationVelocity) {
		this.iModel.setWorldRotationVelocity(rotationVelocity);
	}

	public void toggleAsteroidMovement() {
		asteroidMovement = !asteroidMovement;
	}

	public void toggleAsteroidRotation() {
		asteroidRotation = !asteroidRotation;
	}

	private double rotateX(double x, double y, double radians) {
		return Math.cos(radians) * x - Math.sin(radians) * y;
	}

	private double rotateY(double x, double y, double radians) {
		return Math.sin(radians) * x + Math.cos(radians) * y;
	}
}

// what if there was one invisible view that all asteroids would share. That view would be updated at the same time as the visible view.
//This view would be used to check mouse presses
