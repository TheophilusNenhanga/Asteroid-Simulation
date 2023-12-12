package jbq061.assignment4;

import java.util.ArrayList;
import java.util.List;

public class IModel {
	private double worldRotation;
	private double worldRotationVelocity;

	private List<Asteroid> selected;
	private final double areaCursorMin;
	private final double areaCursorMax;
	private double currentAreaCursor;

	private boolean areaCursorOn;

	public IModel() {
		worldRotation = 0;
		worldRotationVelocity = 0.05 * Math.PI;
		selected = new ArrayList<>();
		this.areaCursorMin = 25;
		this.areaCursorMax = 150;
		this.areaCursorOn = false;
		this.currentAreaCursor = 50;
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

	public double getAreaCursorMin() {
		return areaCursorMin;
	}

	public double getAreaCursorMax() {
		return areaCursorMax;
	}

	public double getCurrentAreaCursor() {
		return currentAreaCursor;
	}

	public void setCurrentAreaCursor(double currentAreaCursor) {
		this.currentAreaCursor = currentAreaCursor;
	}

	public boolean isAreaCursorOn() {
		return areaCursorOn;
	}

	public void setAreaCursorOn(boolean areaCursorOn) {
		this.areaCursorOn = areaCursorOn;
	}

	public List<Asteroid> getSelected() {
		return selected;
	}

	public void clearSelection(){
		this.selected.forEach((asteroid -> {
			asteroid.setSelected(false);
		}));
		this.selected.clear();
	}
}
