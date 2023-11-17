package jbq061.assignment4;

import java.util.Random;

public class SpaceController {
	private Random random;
	private SpaceModel model;
	private IModel iModel;

	private PublishSubscribe publishSubscribe;

	public SpaceController(PublishSubscribe publishSubscribe) {
		this.random = new Random(345654566); // Adding a random seed - its random but fixed between executions.
		this.publishSubscribe = publishSubscribe;
	}

	public void setModel(SpaceModel model) {
		this.model = model;
	}

	public void setIModel(IModel iModel) {
		this.iModel = iModel;
	}

	public void startSystem() {
		// Make stars
		for (int i = 0; i < 400; i++) {
			model.createStar(random.nextDouble(1), random.nextDouble(1), 2.5);
		}
		// Make Asteroids
		for (int i = 0; i < 10; i++) {
			model.createAsteroid(random.nextDouble(1), random.nextDouble(1));
		}
	}

	public void handleTimerTick() {
		iModel.setWorldRotation(iModel.getWorldRotation() + iModel.getWorldRotationVelocity());
		if (iModel.getWorldRotation() >= 360) iModel.setWorldRotation(0);

		for (Asteroid asteroid : model.getAsteroids()) {
			asteroid.getCoordinates()[0] += asteroid.getXVelocity();
			if (asteroid.getCoordinates()[0] > 1) asteroid.getCoordinates()[0] = 0;

			asteroid.getCoordinates()[1] += asteroid.getYVelocity();
			if (asteroid.getCoordinates()[1] > 1) asteroid.getCoordinates()[1] = 0;

			asteroid.setAngle(asteroid.getAngle() + asteroid.getAVelocity());
		}
		publishSubscribe.publish("animate", model.getAsteroids(), model.getStars(), iModel.getWorldRotation());
	}
}
