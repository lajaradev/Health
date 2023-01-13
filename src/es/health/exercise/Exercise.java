package es.health.exercise;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import es.health.database.IShow;
import es.health.user.User.ActivityFactor;

public class Exercise implements IShow{
	
	private static final String STARTTIME     = "\nStart Time: ";
	private static final String ENDTIME       = "\nEnd Time: ";
	private static final String DURATION 	  = "\nDuration: ";
	private static final String DISTANCE 	  = "\nDistance: ";
	private static final String KCALCONSUMED  = "\nKCal Consumed: ";
	private static final String FCMAX	 	  = "\nFC MAX: ";
	private static final String FCMED 		  = "\nFC MED: ";
	private static final String FCMIN 		  = "\nFC MIN: ";
	private static final String KCAL	 	  = " KCal ";
	private static final String KM	  		  = " Km ";
	private static final String HHFORMAT	  = "HH:mm";
	
	protected Date startTime;
	protected Date endTime;
	protected Date duration; 
	protected int distance;
	protected Double kCalConsumed;
	protected int fcMax;
	protected int fcMed;
	protected int fcMin;
	
	public Exercise() {
		
	}
	
	public Exercise(Date startTime, Date endTime, Date duration, int distance, Double kCalConsumed, int fcMax, int fcMed, int fcMin) {
		
		this.startTime = startTime;
		this.endTime = endTime;
		this.duration = duration;
		this.distance = distance;
		this.kCalConsumed = kCalConsumed;
		this.fcMax = fcMax;
		this.fcMed = fcMed;
		this.fcMin = fcMin;
		
	}
	
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}


	
	public Date getDuration() {
		return duration;
	}
	
	public void setDuration(Date duration) {
		this.duration = duration;
	}
	
	public int getDistance() {
		return distance;
	}
	
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	public Double getkCalConsumed() {
		return kCalConsumed;
	}
	
	public void setkCalConsumed(String sex, int age, int weight) {
		
		if(sex == "M") {
			
			kCalConsumed = ( (age * 0.2017) - (weight * 0.09036) + (fcMed * 0.6309) - 55.0969) * (duration.getTime() / 60000) / 4.184; // getTime gives me the value in milliseconds and pass it to minutes / 60,000
			
		}
		
		else {
			
			kCalConsumed = ( (age * 0.0740) - (weight * 0.05741) + (fcMed * 0.4472) - 20.4022) * (duration.getTime() / 60000) / 4.184;
			
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
	
	public void setFcMed() {
		this.fcMed = (this.fcMax + this.fcMin) / 2;
	}
	
	public int getFcMin() {
		return fcMin;
	}
	
	public void setFcMin(int fcMin) {
		this.fcMin = fcMin;
	}

	public String ShowInformation() {
		
		Format formatTime = new SimpleDateFormat(HHFORMAT);
		String informationUser = "";
		informationUser += STARTTIME 	+ formatTime.format(startTime);
		informationUser += ENDTIME 		+ formatTime.format(endTime);
		informationUser += DURATION 	+ formatTime.format(duration);
		informationUser += DISTANCE 	+ distance + KM;
		informationUser += KCALCONSUMED + kCalConsumed + KCAL;
		informationUser += FCMAX 		+ fcMax ;
		informationUser += FCMED 		+ fcMed ;
		informationUser += FCMIN 		+ fcMin ;
		
				
		return informationUser;
	}


	
	

}
