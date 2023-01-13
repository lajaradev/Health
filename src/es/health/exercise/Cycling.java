package es.health.exercise;

import es.health.user.User;

public class Cycling extends Exercise {
	
	private int cadence;
	public User user;

	public int getCadence() {
		return cadence;
	}

	public void setCadence(int cadence) {
		this.cadence = cadence;
	}
	
}
 