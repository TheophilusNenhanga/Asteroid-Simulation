package jbq061.assignment4;

import java.util.ArrayList;

public class SpaceModel {
	private final ArrayList<Subscriber> subscribers;
	private final ArrayList<Asteroid> asteroids;
	private final ArrayList<Star> stars;
	private final PublishSubscribe publishSubscribe;

	public SpaceModel(PublishSubscribe publishSubscribe) {
		this.subscribers = new ArrayList<>();
		this.asteroids = new ArrayList<>();
		this.stars = new ArrayList<>();
		this.publishSubscribe = publishSubscribe;
	}

	public void addSubscriber(Subscriber sub) {
		subscribers.add(sub);
	}

	public void createAsteroid(double normalizedX, double normalizedY) {
		asteroids.add(new Asteroid(normalizedX, normalizedY, MainUI.spaceCanvasWidth, MainUI.spaceCanvasWidth));
		publishSubscribe.publish("create", this.asteroids, this.stars, 0);
	}

	public void createStar(double normalizedX, double normalizedY, double radius) {
		stars.add(new Star(normalizedX, normalizedY, radius));
		publishSubscribe.publish("create", this.asteroids, this.stars, 0);
	}

	public ArrayList<Asteroid> getAsteroids() {
		return asteroids;
	}

	public ArrayList<Star> getStars() {
		return stars;
	}
}
