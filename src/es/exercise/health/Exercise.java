package es.exercise.health;

import java.util.Date;

public class Exercise {
	
	private Date startDate;
	private Date endDate;
	private long duration; 
	private int distance;
	private int kCalConsumed;
	private int fcMax;
	private int fcMed;
	private int fcMin;
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long dateDuration) {
		this.duration = dateDuration;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public int getkCalConsumed() {
		return kCalConsumed;
	}
	public void setkCalConsumed(String sex, int age, int weight) {
		if(sex == "M") {
			
		}
	}
	public int getFcMax() {
		return fcMax;
	}
	public void setFcMax(int fcMax) {
		this.fcMax = fcMax;
	}
	public int getFcMed() {
		return fcMed;
	}
	public void setFcMed(int fcMed) {
		this.fcMed = fcMed;
	}
	public int getFcMin() {
		return fcMin;
	}
	public void setFcMin(int fcMin) {
		this.fcMin = fcMin;
	}
	

}
