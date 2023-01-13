package es.health.exercise;

import java.time.LocalDateTime;
import java.util.Date;

import es.health.user.User;
import es.health.user.UserPremium.Duration;

public class Running extends Exercise{

	private Date swing; 
	private int elevation; 
	private int numberSteps;
	private int cadence;
	public Object user;
	
	/*public Running() {
		super();
	}
	
	public Running(Exercise exercise, Date swing, int elevation, int numberSteps, int cadence) {
		
		super(exercise.getStartTime(), exercise.getEndTime(), exercise.getDuration(), exercise.getDistance(), exercise.getkCalConsumed(), exercise.getFcMax(), exercise.getFcMed(), exercise.getFcMin() );
		this.swing = swing;
		this.elevation = elevation;
		this.numberSteps = numberSteps;
		this.cadence = cadence;
		
	}*/
	
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
