package jbq061.assignment4;

import java.util.ArrayList;
import java.util.List;

public class IModel {
	private double worldRotation;
	private double worldRotationVelocity;

	private List<Asteroid> selected;

	public IModel() {
		worldRotation = 0;
		worldRotationVelocity = 0.5;
		selected = new ArrayList<>();
	}

	public double getWorldRotation() {
		return worldRotation;
	}

	public void setWorldRotation(double worldRotation) {
		this.worldRotation = worldRotation;
	}

	public double getWorldRotationVelocity() {
		return worldRotationVelocity;
	}

	public void setWorldRotationVelocity(double worldRotationVelocity) {
		this.worldRotationVelocity = worldRotationVelocity;
	}

	public List<Asteroid> getSelected() {
		return selected;
	}
}
