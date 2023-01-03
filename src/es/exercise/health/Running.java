package es.exercise.health;

import java.util.Date;

public class Running extends Exercise{

	private Date swing; 
	private int elevation; 
	private int numberSteps;
	private int cadence;
	
	public Date getSwing() {
		return swing;
	}
	
	public void setSwing(Date swing) {
		this.swing = swing;
	}
	
	public int getElevation() {
		return elevation;
	}
	
	public void setElevation(int elevation) {
		this.elevation = elevation;
	}
	
	public int getNumberSteps() {
		return numberSteps;
	}
	
	public void setNumberSteps(int numberSteps) {
		this.numberSteps = numberSteps;
	}
	
	public int getCadence() {
		return cadence;
	}
	
	public void setCadence(int cadence) {
		this.cadence = cadence;
	}
	
}
