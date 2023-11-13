package jbq061.assignment4;

import java.util.ArrayList;

public class SpaceModel {
	private final ArrayList<Subscriber> subscribers;
	private final ArrayList<Asteroid> asteroids;

	public SpaceModel(){
		this.subscribers = new ArrayList<>();
		this.asteroids = new ArrayList<>();
	}

	public void addSubscriber(Subscriber sub) {
		subscribers.add(sub);
	}

	public void createAsteroid(){
		asteroids.add(new Asteroid());
	}
}
