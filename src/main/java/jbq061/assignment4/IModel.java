package jbq061.assignment4;

public class IModel {
	private double worldRotation;
	private double worldRotationVelocity;

	public IModel(){
		worldRotation = 0;
		worldRotationVelocity = 0.0001;
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
}
