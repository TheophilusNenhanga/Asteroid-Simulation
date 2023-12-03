package jbq061.assignment4;

import javafx.scene.input.MouseEvent;

import java.util.List;

public interface Subscriber {
	void receiveNotification(String channelName, List<Asteroid> asteroids, List<Star> stars, double worldRotation);

	void receiveNotification(String channelName, List<Asteroid> asteroids, List<Star> stars, double worldRotation, MouseEvent event);
}
