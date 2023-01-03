package es.main.health;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import es.database.health.DataBase;
import es.exercise.health.Exercise;
import es.user.health.User;
import es.user.health.User.ActivityFactor;
import es.user.health.UserPremium;
import es.user.health.UserPremium.Duration;

public class Main {
	
	private static final String HEALTHAPP 	  = "HEALTH APP BY SAMUEL LAJARA";
	private static final String SEPARATOR     = "-------------------------------\n";
	
	private static final String MAINMENU      = "1. Sign in\n2. Log in\n3. Exit";
	private static final String USERMENU      = "1. Conversion to Premium\n2. Change of Password\n3. Enter Wight\n4. Activity Register\n5. Show Report\n6. Generate PDF Report\n7. Exit";

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
	
	private static final String EXERCISEMENU  = "1. Generic Exercise\n2. Running\n3. Cycling\n4. Swimming\n";
	private static final String STARTDATE	  = "Start Date: ";
	private static final String ENDDATE	 	  = "End Date: ";
	private static final String DATAFORMAT	  = "yyyy/mm/dd";
	private static final String AGE	 		  = "Age: ";
	
	private static Scanner scanIn = new Scanner(System.in);
	
	public static void main(String[] args) {
				
		System.out.println(HEALTHAPP);
		System.out.println(SEPARATOR);
		
		menu();
		
		scanIn.close();	
	}
	
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
					System.out.println("Op 1");
					premiumConversion(user);
				break;
					
				case 2: // CHANGE OF PASSWORD
					System.out.println("Op 2");
					changePassword(user);
				break;
					
				case 3: // ENTER WEIGHT 
					System.out.println("Op 3");
				break;
				
				case 4: // EXERCISE REGISTER
					exerciseRegister(user);
				break;
				
				case 5: // SHOW REPORT
					System.out.println("op 5");
					//ShowInformation();
				break;
				
				case 6: // GENERATE PDF REPORT
					System.out.println("Op 6");
				break;
				
				case 7: // EXIT
					System.out.println("Op 7");
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
	
	private static String screen(String str) {
		
		System.out.println(str);
		return(scanIn.nextLine());
	}
	
	/* Method to register a new user.
	 * Request the data and validate it.
	 * Then add the data to an array in another class
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
		user.setSex(strSex);				  // SEX
		user.setWeight(intWeight);			  // WEIGHT
		user.setHeight(intHeight);			  // HEIGHT
		user.setActivity(actActivity);        // ACTIVITYFACTOR
		DataBase.addUser(user);	
		 
	}
	
	private static int calculateAge(String strBirthday) {
		
		int age = 0;
		Calendar birthday;
		
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(date);
		int dateYear = calendar.get(Calendar.YEAR);
		
		SimpleDateFormat sdf = new SimpleDateFormat(DATAFORMAT);
		try {
			calendar.setTime(sdf.parse(strBirthday));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int dateBirth = calendar.get(Calendar.YEAR);
		
		age = dateYear - dateBirth;
		
		System.out.println(AGE + age);
		return age;
		
	}
	
	/* Validate that the alias is not empty */
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
	
	/* Validate that the password has more than 5 characters */
	private static String validatePassword() {
		
		String strPassword;
		
		do{ // PASSWORD 
			strPassword = screen(PASSWORD);
			if(strPassword.compareTo(SPACE) <= 5) {
				System.out.println(PASSWORDMIN);
			}
		}while(strPassword.compareTo(SPACE) <= 5);
		
		return strPassword;
	}
	
	private static Calendar currentDate(){
		
		Calendar calendar = Calendar.getInstance();	
		return calendar;
		
	}
	
	/* Validate that the year is between 1900 and 2022
	 * Validate that the month is between 1 and 12
	 * Validate that the day is between 1 and 31
	 * 
	 * And check that the date entered is not greater than the current one
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

	/* Take the data that is entered on the screen and ask the class if it has this player 
	 * The player returns
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
	
	private static User logInUser() { 
		
		String strAlias, strPassword;
		
		strAlias = screen(ALIAS);
		strPassword = screen(PASSWORD);
	
		if(DataBase.userExists(strAlias, strPassword) != null) {
			return DataBase.userExists(strAlias, strPassword);
		}
		return null;
	}
	
	

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
			
			strDuration = screen("Duration: M / A / I");
			durDuration = UserPremium.validateDuration(strDuration);
		
		} while(durDuration == null);
		
		intDuration = UserPremium.validateDayDuration(durDuration);
		
		LocalDateTime Todaysdate = LocalDateTime.now();     		// Today
	    LocalDateTime newDate = Todaysdate.plusDays(intDuration);   // Plus day
		
	    //System.out.println(newDate);
	    
	    user = new UserPremium(user, Todaysdate, durDuration);
	   // DataBase.removeUser(user);
	    DataBase.addUser(user);
	    
	}
	
	private static void changePassword(User user){
		
		String oldPassword, newPassword, strAlias = user.getAlias(), strPassword = user.getPassword();
		
		
		oldPassword = screen(OLDPASSWORD);
		
		if(DataBase.passwordOld(strAlias, strPassword) == true) {
			
			newPassword = screen(NEWPASSWORD);
			// TO DO PREMIUM OR NOT ??? VALIDATE TYPE
			user.setPassword(newPassword);
			System.out.println(PASSWORDOK);
			
		}
		
	}
	
	private static void exerciseRegister(User user) {
		
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
				
				break;
					
				case 3: // CYCLING
					
				break;
				
				case 4: // SWIMMING
					
				break;
									
				default:
					System.out.println(DEFAULT);
				break;
				} // SWITCH END	
			
		}while(b);
		
	}
	
	private static void genericExercise(User user) {
		
		Date dateStart = null;
		Date dateEnd = null;
		long dateDuration;
		int intDistance, intKCalConsumed, intFcMax, intFcMed, intFcMin;
		
		try {
			dateStart = new SimpleDateFormat("MM/dd/yyyy").parse(screen(STARTDATE));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		try {
			dateEnd = new SimpleDateFormat("MM/dd/yyyy").parse(screen(ENDDATE));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		dateDuration = dateEnd.getTime() - dateStart.getTime();
		intDistance = Integer.parseInt(screen("Distance: "));
		intFcMax = Integer.parseInt(screen("FcMax: "));
		intFcMin = Integer.parseInt(screen("FcMin: "));
		
		Exercise exercise = new Exercise();
		exercise.setStartDate(dateStart);
		exercise.setStartDate(dateEnd);
		exercise.setDuration(dateDuration);
		exercise.setDistance(intDistance);
		exercise.setFcMax(intFcMax);
		exercise.setFcMin(intFcMin);
		//TO DO
		//exercise.setkCalConsumed(user.getSex(), user.getAge(), user.getWeight());
		//exercise.setFcMed();
		user.listExercise.add(exercise);
		
	}
	
}
