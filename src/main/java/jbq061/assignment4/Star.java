package jbq061.assignment4;

public class Star {
	private double[] coordinates;

	public Star(double normalizedX, double normalizedY){
		coordinates = new double[] {normalizedX, normalizedY};
	}

	public double[] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(double[] coordinates) {
		this.coordinates = coordinates;
	}
}
