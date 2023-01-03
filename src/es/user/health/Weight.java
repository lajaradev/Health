package es.user.health;

import java.util.Date;

import es.database.health.IShow;

public class Weight implements IShow {

		private Date dateWeight;
		private int intWeight;
;
		public Date getDateWeight() {
			return dateWeight;
		}
		
		public void setDateWeight(Date dateWeight) {
			this.dateWeight = dateWeight;
		}
		
		public int getIntWeight() {
			return intWeight;
		}
		
		public void setIntWeight(int intWeight) {
			this.intWeight = intWeight;
		}

		public String ShowInformation() {
			
			String informationUser = "";
			informationUser += " Date: " + dateWeight.toString();
			informationUser += " Weight: " + intWeight;
			
			return informationUser;
		}
		
}
