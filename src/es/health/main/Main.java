package es.health.main;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import es.health.database.DataBase;
import es.health.exercise.Cycling;
import es.health.exercise.Exercise;
import es.health.exercise.Running;
import es.health.exercise.Swimming;
import es.health.exercise.Swimming.TypeSwimming;
import es.health.persistence.Serialization;
import es.health.user.User;
import es.health.user.UserPremium;
import es.health.user.Weight;
import es.health.user.User.ActivityFactor;
import es.health.user.UserPremium.Duration;

public class Main {
	
	private static final String HEALTHAPP 	  = "HEALTH APP BY SAMUEL LAJARA";
	private static final String SEPARATOR     = "-------------------------------\n";
	private static final String MAINMENU      = "1. Sign in\n2. Log in\n3. Exit";
	private static final String USERMENU      = "1. Conversion to Premium\n2. Change of Password\n3. Enter Wight\n4. Activity Register\n5. Show Report\n6. Exit";
	private static final String ALIAS 		  = "Alias:";
	private static final String PASSWORD      = "Password:";
	private static final String OLDPASSWORD   = "Old Password:";
	private static final String NEWPASSWORD   = "New Password:";
	private static final String PASSWORDOK    = "The password has been changed successfully\n";
	private static final String NAME 		  = "Name:";
	private static final String LASTNAME 	  = "Last Name:";
	private static final String YEAR	 	  = "YEAR: (yyyy)";
	private static final String MONTH	      = "MONTH: (mm)";
	private static final String DAY			  = "DAY: (dd)";
	private static final String SEX 		  = "Sex: (M / W)";
	private static final String MEN 		  = "M";
	private static final String WOMEN 		  = "W";
	private static final String NOTVALID	  = "Not valid";
	private static final String WEIGHT 		  = "Weight:";
	private static final String HEIGHT 		  = "Height:";
	private static final String ACTIVITY 	  = "ACTIVITY:\n 'S' -> SEDENTARY, 'L' -> LIGHT, 'M' -> MODERATE, 'I' -> INTENSE, 'E' -> EXTREMELYHIGH";
	private static final String DEFAULT       = "Wrong Number";
	private static final String SPACE         = "";
	private static final String PASSWORDMIN   = "Atleast 6 Characters";
	private static final String NOTNULL  	  = "Not Null";
	private static final String CHECKNUMBER   = "You haven't entered a number. Try again: ";
	private static final String ERRORDATE2	  = "The date can't be later than the current date";
	private static final String EXERCISEMENU  = "1. Generic Exercise\n2. Running\n3. Cycling\n4. Swimming\n5. Return\n";
	private static final String STARTDATE	  = "Start Date -> HH:mm";
	private static final String ENDDATE	 	  = "End Date -> HH:mm";
	private static final String DATAFORMAT	  = "yyyy/mm/dd";
	private static final String HHFORMAT	  = "HH:mm";
	private static final String AGE	 		  = "Age: ";
	private static final String NEWWEIGHT	  = "New Weight: ";
	private static final String DISTANCE 	  = "\nDistance: ";
	private static final String DUR			  = "Duration: M / A / I";
	private static final String FCMAX	 	  = "\nFC MAX: ";
	private static final String FCMIN 		  = "\nFC MIN: ";
	private static final String VALIDPASS	  = "Valid Password";
	private static final String NOTCONTAIN    = "Your password does not contain the following: ";
	private static final String MINLOWERCASE  = "Minimum lowercase letters (1)";
	private static final String MINNUMBER     = "Minimum number of numeric digits (1)";
	private static final String SWING   	  = "Swing: (HH:MM in 1 Km) ";
	private static final String ELEVATION     = "Elevation: ";
	private static final String NUMBERSTEPS   = "Number Steps: ";
	private static final String CADENCE   	  = "Cadence";
	private static final String LENGHT   	  = "Number of Lenghts: ";
	private static final String RUTA_FICHERO = "./listUsers.txt";
	private static final String FILE 		 = "listUsers.txt";
	
	private static Scanner scanIn = new Scanner(System.in);
	
	
	/*
	 * Start APP
	 * */
	public static void main(String[] args) {
				
		System.out.println(HEALTHAPP);
		System.out.println(SEPARATOR);
		
		File f = new File(FILE);
		if(f.exists()) { 
			Serialization des = new Serialization();
			des.loadFromJson(RUTA_FICHERO);
		}
		
		menu();
		
		scanIn.close();	
	}
	
	/*
	 * Basic Menu
	 * */
	private static void menu() {
		
		int option = 0;
		boolean b = true;
		
		do {
				System.out.println(MAINMENU);
				
				try {
					option = Integer.parseInt(scanIn.nextLine());
				}catch(InputMismatchException e) {
					System.out.println(CHECKNUMBER);
					scanIn.nextLine();
				}
												
				switch(option) {
				
				case 1: // SIGN IN
					signInUser();
				
					Serialization sr = new Serialization();
					sr.persistToJson(RUTA_FICHERO);
					
				break;
					
				case 2: // LOG IN
					User user  = logInUser();
					if(user != null) {
						submenu(user);
					}
				break;
					
				case 3: // EXIT 
					System.exit(0);
				break;
									
				default:
					System.out.println(DEFAULT);
				break;
				} // SWITCH END	
			
		}while(b);
		
	}
	
	/*
	 * Menu for users who are already registered.
	 * */
	private static void submenu(User user) {
		
		ArrayList <User> listUser = DataBase.giveMeListUser();
		
		int option = 0;
		boolean b = true;
		
		do {
				System.out.println(USERMENU);
				
				try {
					option = Integer.parseInt(scanIn.nextLine());
				}catch(InputMismatchException e) {
					System.out.println(CHECKNUMBER);
					scanIn.nextLine();
				}
												
				switch(option) {
				
					case 1: // CONVERSION TO PREMIUM
						premiumConversion(user);
					break;
						
					case 2: // CHANGE OF PASSWORD
						changePassword(user);
					break;
						
					case 3: // ENTER WEIGHT 
						enterWeight(user);
					break;
					
					case 4: // EXERCISE REGISTER
						exerciseMenu(user);
					break;
					
					case 5: // SHOW REPORT
						System.out.println(user.ShowInformation());
						//ShowInformation();
					break;
					
					case 6: // EXIT
						b = false;
					break;
										
					case 8: // TESTS
						DataBase.showUser(listUser);
					break;
						
					default:
						System.out.println(DEFAULT);
					break;
				} // SWITCH END	
				
			
		}while(b);

	}
	
	/*
	 * Method to request the data on the screen.
	 * */
	private static String screen(String str) {
		
		System.out.println(str);
		return(scanIn.nextLine());
		
	}
	
	/* 
	 * Method to register a new user.
	 * Request the data and validate it.
	 * Then add the data to an array in another class.
	 * */
	private static void signInUser() { // COLLECT THE VALUE
	
		String strAlias, strPassword, strName, strLastName, strBirthday, strSex;
		int intAge, intWeight, intHeight;
		ActivityFactor actActivity;
		
		strAlias    = validateAlias(); 		  // ALIAS
		strPassword = validatePassword();     // PASSWORD 
		strName 	= (screen(NAME)); 		  // NAME 
		strLastName = (screen(LASTNAME)); 	  // LAST NAME 
		strBirthday = validateBirthdayDate(); // BIRTHDAY DATE
		intAge 		= calculateAge(strBirthday);
		strSex 		= validateSex(); 		  // SEX
		intWeight 	= Integer.parseInt( (screen(WEIGHT)) ); // WEIGHT
		intHeight 	= Integer.parseInt( (screen(HEIGHT)) ); // HEIGHT
		actActivity = validateActivity(); 	  // ACTIVITYFACTOR
		
		
		User user = new User();
		user.setAlias(strAlias);			  // ALIAS
		user.setPassword(strPassword);		  // PASSWORD
		user.setName(strName);				  // NAME
		user.setLastName(strLastName);		  // LAST NAME
		user.setBirthdayDate(strBirthday);	  // BIRTHDAY DATE
		user.setAge(intAge);                  // AGE
		user.setSex(strSex);				  // SEX
		user.setWeight(intWeight);			  // WEIGHT
		user.setHeight(intHeight);			  // HEIGHT
		user.setImc();   					  // IMC
		user.setActivity(actActivity);        // ACTIVITYFACTOR
		DataBase.addUser(user);	
		 
	}
	
	/*
	 * Calculates the age from the current year and the year of birth.
	 * */
	private static int calculateAge(String strBirthday) {
		
		int age = 0;
		
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(date);
		int dateYear = calendar.get(Calendar.YEAR);
		
		SimpleDateFormat sdf = new SimpleDateFormat(DATAFORMAT);
		try {
			calendar.setTime(sdf.parse(strBirthday));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		int dateBirth = calendar.get(Calendar.YEAR);
		
		age = dateYear - dateBirth;
		
		System.out.println(AGE + age);
		return age;
		
	}
	
	/* 
	 * Validate that the alias is not empty.
	 * */
	private static String validateAlias() {
		
		String strAlias;
		
		do{ // ALIAS 
			strAlias = screen(ALIAS);
			if(strAlias.compareTo(SPACE) == 0) {
				System.out.println(NOTNULL);
			}
		}while(strAlias.compareTo(SPACE) == 0);
		
		return strAlias;
	}
	
	/* 
	 * Validate that the password has more than 5 characters in addition to containing letters and numbers.
	 * */
	private static String validatePassword() {
		
		String strPassword;
		
		int lowercaseCounter = 0; 	// Counter lowercase letters in a password
        int digitCounter = 0; 		// Count digits in a password
        
		final int MIN = 6; 			 // Specify the minimum characters in a password		
		final int MIN_Lowercase = 1; // Specifying the minimum lowercase letters in password
	    final int NUM_Digits = 1;    // Specifying the number of digits in a password
	   
	    Boolean b = false;
	    
		do{ // PASSWORD 
			strPassword = screen(PASSWORD);
			
			for(int i = 0; i < strPassword.length(); i ++) {
				
				char c = strPassword.charAt(i);
					 
	            if(Character.isLowerCase(c)) 
	            	lowercaseCounter ++;
	            else if(Character.isDigit(c)) 
	                digitCounter ++;     
	          
			}	
			
			if(strPassword.length() >= MIN && lowercaseCounter >= MIN_Lowercase && digitCounter >= NUM_Digits) {
				
				System.out.println(VALIDPASS);
				b = true;
		
			}
			else {
				System.out.println(NOTCONTAIN);
				
				if(strPassword.length() < MIN)
					System.out.println(PASSWORDMIN);
				
				if(lowercaseCounter < MIN_Lowercase) 
					System.out.println(MINLOWERCASE);
				
				
				if(digitCounter < NUM_Digits) 
					System.out.println(MINNUMBER);
			}
			
		}while(b = false);
		
		return strPassword;
	}
	
	/*
	 * Method to collect the current full date.
	 * */
	private static Calendar currentDate(){
		
		Calendar calendar = Calendar.getInstance();	
		return calendar;
		
	}
	
	/* 
	 * Validate that the year is between 1900 and 2022.
	 * Validate that the month is between 1 and 12.
	 * Validate that the day is between 1 and 31.
	 * 
	 * And check that the date entered is not greater than the current one.
	 * */
	public static String validateBirthdayDate(){
		
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATAFORMAT);
		Date dateBirth;
		Date currentDate;
		
		String strCalendar;
		
		int year  = 0;
		int month = 0;
		int day   = 0;
		int maxDay;
		
		boolean b = true;
		
		do {
		
			do { // YEAR 
				try { 
					year  = Integer.parseInt(screen(YEAR));
				}
				catch(NumberFormatException e) {  
					System.out.println(CHECKNUMBER);
					scanIn.nextLine();
				}
			} while(year <= 1900 || year > calendar.get(Calendar.YEAR));
			
			do { // MONTH
				try {
					month = Integer.parseInt(screen(MONTH));
				}
				catch(NumberFormatException e) {  
					System.out.println(CHECKNUMBER);
					scanIn.nextLine();
				}
			} while(month <= 0 || month >= 13);
			
			if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
				maxDay = 32;
			}
			else if(month == 2) {
				maxDay = 29;
			}
			else {
				maxDay = 31;
			}
			
			do { // DAY
				try {
					day = Integer.parseInt(screen(DAY));
				}
				catch(NumberFormatException e) {  
					System.out.println(CHECKNUMBER);
					scanIn.nextLine();
				}
			} while(day <= 0 || day >= maxDay);
			
			calendar.set(year, month - 1, day);
			dateBirth = calendar.getTime();	
			currentDate = currentDate().getTime();	
			
			if(currentDate.compareTo(dateBirth) >= 0) {
				strCalendar = sdf.format(dateBirth);
				return strCalendar;
			}
			
			System.out.println(ERRORDATE2);
		
		}while(b);
		
		return null;
		
	}

	
	/*
	 * Check that the user enters a valid sex.
	 * */
	private static String validateSex() {
		
		String strSex;
		
		do{ // ALIAS 
			strSex = screen(SEX);
			
			if(strSex.compareTo(MEN) != 0 && strSex.compareTo(WOMEN) != 0) {
				System.out.println(NOTVALID);
			}
					
		}while(strSex.compareTo(MEN) != 0 && strSex.compareTo(WOMEN) != 0);
		
		return strSex ;
	}
	
	/*
	 * Collect the activity factor indicated by the user.
	 * */
	private static ActivityFactor validateActivity() {
		
		ActivityFactor actActivity = null;
	
		String op = screen(ACTIVITY);
		
		switch(op.charAt(0)) {
		
			case 'S':
				actActivity = ActivityFactor.SEDENTARY;
			break;
			
			case 'L':
				actActivity = ActivityFactor.LIGHT;
			break;
			
			case 'M':
				actActivity = ActivityFactor.MODERATE;
			break;
			
			case 'I':
				actActivity = ActivityFactor.INTENSE;
			break;
			
			case 'E':
				actActivity = ActivityFactor.EXTREMELYHIGH;
			break;
			
			default:
				actActivity = ActivityFactor.MODERATE;
			break;
		
		}
		
		return actActivity;
		
	}
	
	/*
	 * Pass the alias and password to another class to see if the user exists.
	 * */
	private static User logInUser() { 
		
		String strAlias, strPassword;
		
		strAlias = screen(ALIAS);
		strPassword = screen(PASSWORD);
	
		if(DataBase.userExists(strAlias, strPassword) != null) {
			return DataBase.userExists(strAlias, strPassword);
		}
		
		return null;
	}
	
	/* 
	 * Convert a basic user into a premium user, adding the corresponding attributes.
	 * */
	private static void premiumConversion(User user){
		
		String strPasswordPremium, strDuration;
		Boolean ok;
		Duration durDuration;
		int intDuration;
		
		do {
	
			strPasswordPremium = screen(PASSWORD);
			ok = UserPremium.validatePasswordPremium(strPasswordPremium);
			
		} while(ok == false);
		
		do {
			
			strDuration = screen(DUR);
			durDuration = UserPremium.validateDuration(strDuration);
		
		} while(durDuration == null);
		
		intDuration = UserPremium.validateDayDuration(durDuration);
		
		Date Todaysdate = new Date();
	       
	    Calendar c = Calendar.getInstance();
	    c.setTime(Todaysdate);
	    c.add(Calendar.DATE, intDuration);
	    Todaysdate = c.getTime();
	     
	
	    UserPremium userP = new UserPremium(user, strPasswordPremium, Todaysdate, durDuration);
	    DataBase.removeUser(user);
	    DataBase.addUser(userP);
	    
	}
	
	/*
	 * Method to change user password.
	 * */
	private static void changePassword(User user){
		
		String oldPassword, newPassword, strAlias = user.getAlias();
		
		oldPassword = screen(OLDPASSWORD);
		
		if(DataBase.passwordOld(strAlias, oldPassword) == true) {
			
			newPassword = screen(NEWPASSWORD);
			
			user.setPassword(newPassword);
			System.out.println(PASSWORDOK);
			
		}
		
	}
	
	/*
	 * Method to register a new weight.
	 * */
	private static void enterWeight(User user) {
		
		int newWeight;
		Calendar calendar = Calendar.getInstance();
		Date currentDate;
		
		newWeight = Integer.parseInt( screen(NEWWEIGHT) );
		currentDate = currentDate().getTime();	
		
		Weight weights = new Weight();
		weights.setDateWeight(currentDate);
		weights.setIntWeight(newWeight);
		user.addWeight(weights);
		
	}
	
	/*
	 * Menu to choose what type of exercise will be recorded.
	 * */
	private static void exerciseMenu(User user) {
		
		int option = 0;
		boolean b = true;
		
		do {
				System.out.println(EXERCISEMENU);
				
				try {
					option = Integer.parseInt(scanIn.nextLine());
				}catch(InputMismatchException e) {
					System.out.println(CHECKNUMBER);
					scanIn.nextLine();
				}
												
				switch(option) {
				
				case 1: // GENERIC EXERCISE
					genericExercise(user);
				break;
					
				case 2: // RUNNING
					running(user);
				break;
					
				case 3: // CYCLING
					cycling(user);
				break;
				
				case 4: // SWIMMING
					swimmimg(user);
				break;
				
				case 5: // RETURN
					b = false;
				break;
									
				default:
					System.out.println(DEFAULT);
				break;
				} // SWITCH END	
			
		}while(b);
		
	}
	
	/* 
	 * We register a new activity.
	 * */
	private static void genericExercise(User user) {
		
		Date startTime = null;
		Date endTime = null;
		Date durationTime;
		int intDistance, intFcMax, intFcMin;
		
		try {
			startTime = new SimpleDateFormat(HHFORMAT).parse(screen(STARTDATE));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		try {
			endTime = new SimpleDateFormat(HHFORMAT).parse(screen(ENDDATE));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		//System.out.println(startTime);
		//System.out.println(dateEnd);
		
		durationTime = new Date (endTime.getTime() - startTime.getTime() - 3600000);
		intDistance = Integer.parseInt(screen(DISTANCE));
		intFcMax = Integer.parseInt(screen(FCMAX));
		intFcMin = Integer.parseInt(screen(FCMIN));
		//System.out.println(durationTime);
		Exercise exercise = new Exercise();
		exercise.setStartTime(startTime);
		exercise.setEndTime(endTime);
		exercise.setDuration(durationTime);
		exercise.setDistance(intDistance);
		exercise.setFcMax(intFcMax);
		exercise.setFcMin(intFcMin);
		exercise.setkCalConsumed(user.getSex(), user.getAge(), user.getWeight());
		exercise.setFcMed();
		user.addExercise(exercise);	
		
	}
	
	/*
	 * Method to request the attributes of the parent class and the data of the 'running' child class.
	 * Create a new 'running' object that we insert into the exercises array.
	 * */
	private static void running(User user) {
		
		
		Date startTime = null;
		Date endTime = null;
		Date durationTime;
		int intDistance, intFcMax, intFcMin;
		
		Date swing = null; 
		int elevation; 
		int numberSteps;
		int cadence;
		
		try {
			startTime = new SimpleDateFormat(HHFORMAT).parse(screen(STARTDATE));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		try {
			endTime = new SimpleDateFormat(HHFORMAT).parse(screen(ENDDATE));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		try {
			swing = new SimpleDateFormat(HHFORMAT).parse(screen(SWING));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		durationTime = new Date (endTime.getTime() - startTime.getTime() - 3600000);
		intDistance = Integer.parseInt(screen(DISTANCE));
		intFcMax = Integer.parseInt(screen(FCMAX));
		intFcMin = Integer.parseInt(screen(FCMIN));
		elevation = Integer.parseInt(screen(ELEVATION));
		numberSteps = Integer.parseInt(screen(NUMBERSTEPS));
		cadence = Integer.parseInt(screen(CADENCE));
	
		Running running = new Running();
		running.setStartTime(startTime);
		running.setEndTime(endTime);
		running.setDuration(durationTime);
		running.setDistance(intDistance);
		running.setFcMax(intFcMax);
		running.setFcMin(intFcMin);
		running.setkCalConsumed(user.getSex(), user.getAge(), user.getWeight());
		running.setFcMed();
		running.setSwing(swing);
		running.setElevation(elevation);
		running.setCadence(cadence);
		running.setNumberSteps(numberSteps);
		user.addRunning(running);
					
	}
	
	/*
	 * Method to request the attributes of the parent class and the data of the 'cycling' child class.
	 * Create a new 'cycling' object that we insert into the exercises array.
	 * */
	private static void cycling(User user) {
		
		
		Date startTime = null;
		Date endTime = null;
		Date durationTime;
		int intDistance, intFcMax, intFcMin;
		int cadence;
		
		try {
			startTime = new SimpleDateFormat(HHFORMAT).parse(screen(STARTDATE));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		try {
			endTime = new SimpleDateFormat(HHFORMAT).parse(screen(ENDDATE));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		durationTime = new Date (endTime.getTime() - startTime.getTime() - 3600000);
		intDistance = Integer.parseInt(screen(DISTANCE));
		intFcMax = Integer.parseInt(screen(FCMAX));
		intFcMin = Integer.parseInt(screen(FCMIN));
		cadence = Integer.parseInt(screen(CADENCE));
	
		Cycling cycling = new Cycling();
		cycling.setStartTime(startTime);
		cycling.setEndTime(endTime);
		cycling.setDuration(durationTime);
		cycling.setDistance(intDistance);
		cycling.setFcMax(intFcMax);
		cycling.setFcMin(intFcMin);
		cycling.setkCalConsumed(user.getSex(), user.getAge(), user.getWeight());
		cycling.setFcMed();
		cycling.setCadence(cadence);
		user.addCycling(cycling);
					
			
	}
	
	/*
	 * Method to request the attributes of the parent class and the data of the 'swimmimg' child class.
	 * Create a new 'swimmimg' object that we insert into the exercises array.
	 * */
	private static void swimmimg(User user) {
		
		Date startTime = null;
		Date endTime = null;
		Date durationTime;
		int intDistance, intFcMax, intFcMin;
		
		int numberLengths;
		TypeSwimming typeSwimming;
				
		try {
			startTime = new SimpleDateFormat(HHFORMAT).parse(screen(STARTDATE));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		try {
			endTime = new SimpleDateFormat(HHFORMAT).parse(screen(ENDDATE));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		durationTime = new Date (endTime.getTime() - startTime.getTime() - 3600000);
		intDistance = Integer.parseInt(screen(DISTANCE));
		intFcMax = Integer.parseInt(screen(FCMAX));
		intFcMin = Integer.parseInt(screen(FCMIN));
		numberLengths = Integer.parseInt(screen(LENGHT));
		
	
		Swimming swimming = new Swimming();
		swimming.setStartTime(startTime);
		swimming.setEndTime(endTime);
		swimming.setDuration(durationTime);
		swimming.setDistance(intDistance);
		swimming.setFcMax(intFcMax);
		swimming.setFcMin(intFcMin);
		swimming.setkCalConsumed(user.getSex(), user.getAge(), user.getWeight());
		swimming.setFcMed();
		swimming.setNumberLengths(numberLengths);
		user.addSwimming(swimming);
					
	}
	

}
