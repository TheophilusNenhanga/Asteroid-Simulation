package jbq061.assignment4;

import java.util.Random;

public class SpaceController {
	private Random random;
	private SpaceModel model;
	private IModel iModel;

	public SpaceController() {
		random = new Random(346542131); // Adding a random seed - its random but fixed between executions.
	}

	public void setModel(SpaceModel model) {
		this.model = model;
	}

	public void setIModel(IModel iModel) {
		this.iModel = iModel;
	}

	public void startSystem() {
		// When the application starts:
		// Make stars
		for (int i = 0; i < 100; i++) {
			model.createStar(random.nextDouble(1), random.nextDouble(1), 5);
		}
		// Make Asteroids
		for (int i = 0; i < 10; i++) {
			model.createAsteroid(random.nextDouble(1), random.nextDouble(1), random.nextInt(20, 60));
		}
	}
}
