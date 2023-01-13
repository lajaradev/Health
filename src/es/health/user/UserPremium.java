package es.health.user;

import java.time.LocalDateTime;
import java.util.Date;

import es.health.database.IShow;
import es.health.user.UserPremium.Duration;

public class UserPremium extends User {
	
	private static final String SP              = "-----------------------";
	private static final String VALIDPASSWORD   = "Valid Password";
	private static final String NOTCONTAIN  	= "Your password does not contain the following: ";
	private static final String MINLOWERCASE  	= "Minimum lowercase letters (1)";
	private static final String MINUPPERCASE   	= "Minimum uppercase letters (1)";
	private static final String MINNUMBER   	= "Minimum number of numeric digits (1)";
	private static final String MINSPECIAL   	= "Minium special characters (1)";
	private static final String PASSWORDMIN   	= "Atleast 8 Characters";
	
	private String passwordPremium;
	private Date DatePremiumRegistration;
	
	private Duration duration;
	
	public enum Duration { 
		MONTHLY, ANNUAL, INDEFINITE // list of named constants that define a new data type
	}
	
	public UserPremium() {
		super();
	}

	public UserPremium(User user, String passwordPremium, Date DatePremiumRegistration, Duration duration) {
		
		super(user.getAlias(), user.getPassword(), user.getName(), user.getLastName(), user.getBirthdayDate(), user.getAge(), user.getSex(), user.getWeight(), user.getHeight(), user.getActivity());
		this.passwordPremium = passwordPremium;
		user.setPassword(passwordPremium);
		this.DatePremiumRegistration = DatePremiumRegistration;
		this.duration = duration;
		
	}
	
	public String ShowInformation() {
		
		String informationUser = super.ShowInformation();
		informationUser += SP;
		informationUser += DatePremiumRegistration.toString()  ;
		informationUser += duration ;
		
		return informationUser;
	}
	
	public static Duration validateDuration(String strDuration) {
		
		Duration duration = null;
	
		switch(strDuration.charAt(0)) {
		
			case 'M':
				duration = Duration.MONTHLY;
			break;
			
			case 'A':
				duration = Duration.ANNUAL;
			break;
			
			case 'I':
				duration = Duration.INDEFINITE;
			break;
			
			default:
				duration = Duration.MONTHLY;
			break;
			
		}
		
		return duration;
		
	}

	public static int validateDayDuration(Duration durDuration) {
		
		if(durDuration == Duration.MONTHLY) return 31;
		else if(durDuration == Duration.ANNUAL) return 365;
		else return 99999;
			
	}
	
	public static Boolean validatePasswordPremium(String strPasswordPremium) {	
		
        final int MIN = 8; 			 // Specify the minimum characters in a password		
		final int MIN_Uppercase = 1; // Specifying the number of uppercase letters in password
        final int MIN_Lowercase = 1; // Specifying the minimum lowercase letters in password
        final int NUM_Digits = 1;    // Specifying the number of digits in a password
        final int Special = 1;  	 // Specify the minimum number of special case letters
		
		int uppercaseCounter = 0; 	// Count number of uppercase letters in a password
		int lowercaseCounter = 0; 	// Counter lowercase letters in a password
        int digitCounter = 0; 		// Count digits in a password
        int specialCounter = 0;     // Count special case letters in a password
		
		
		for(int i = 0; i < strPasswordPremium.length(); i ++) {
				
			char c = strPasswordPremium.charAt(i);
				 
            if(Character.isUpperCase(c)) 
            	uppercaseCounter ++;
            else if(Character.isLowerCase(c)) 
            	lowercaseCounter ++;
            else if(Character.isDigit(c)) 
                digitCounter ++;     
            else if(c >= 33 && c <= 46 || c == 64)
            	specialCounter ++;
            
		}	
		
		if(strPasswordPremium.length() >= MIN && uppercaseCounter >= MIN_Uppercase && lowercaseCounter >= MIN_Lowercase && digitCounter >= NUM_Digits && specialCounter >= Special) {
			
			System.out.println(VALIDPASSWORD);
			return true;
	
		}
	
		else {
			
			System.out.println(NOTCONTAIN);
			
			if(strPasswordPremium.length() < MIN)
				System.out.println(PASSWORDMIN);
			
			if(lowercaseCounter < MIN_Lowercase) 
				System.out.println(MINLOWERCASE);
			
			if(uppercaseCounter < MIN_Uppercase) 
				System.out.println(MINUPPERCASE);
			
			if(digitCounter < NUM_Digits) 
				System.out.println(MINNUMBER);
			
			if(specialCounter < Special)
				System.out.println(MINSPECIAL);
			
			return false;
					                    
		}
					             
	}
	
	public Date getDatePremiumRegistration() {
		return DatePremiumRegistration;
	}

	public void setDatePremiumRegistration(Date datePremiumRegistration) {
		DatePremiumRegistration = datePremiumRegistration;
	}
	
	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	public String getPasswordPremium() {
		return passwordPremium;
	}

	public void setPasswordPremium(String passwordPremium) {
		this.passwordPremium = passwordPremium;
	}

}
