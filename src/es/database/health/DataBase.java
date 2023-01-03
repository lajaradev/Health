package es.database.health;

import java.io.File;
import java.util.ArrayList;

import com.google.gson.Gson;

import es.user.health.User;

public class DataBase implements IShow {
	
	private static final String INFUSER		  = "SHOW INFORMATION USER\n";
	private static final String ALIAS 		  = "Alias: ";
	private static final String PASSWORD      = "Password: ";
	private static final String NAME 		  = "Name: ";
	private static final String LASTNAME 	  = "Last Name:";
	private static final String SEX 		  = "Sex:";
	private static final String BIRTHDAY	  = "Birthday Date: ";
	private static final String WEIGHT   	  = "Weight: ";
	private static final String SEPARATOR     = "-------------------------------\n";
	private static final String ALIASEXISTS   = "This alias already exists";
	private static final String USEREXISTS 	  = "There is no user with this combination\n";
	
	private static ArrayList <User> listUser = new ArrayList <User>();
	
	
	
	
	
	
	
	
	// ADD USER
	public static void addUser(User user) {
		
		if(aliasExists(user.getAlias()) == null) { 
			listUser.add(user);
		}
		else {
			System.out.println(ALIASEXISTS);
		}
		
	}
	
	// REMOVE USER
	public static void removeUser(User user) {
		
		if(aliasExists(user.getAlias()) != null) { 
			listUser.remove(user);
		}
				
	}
	
	// ALIAS EXIST
	public static User aliasExists(String alias) {
		
		for(int i = 0; i < listUser.size(); i ++) {
			if(listUser.get(i).getAlias().compareTo(alias) == 0) {
				return listUser.get(i);
			}	
		}
		return null;
		
	}
	
	// PASSWORD EXISTS WITH USER
	public static boolean passwordOld(String alias, String password) {
			
		for(int i = 0; i < listUser.size(); i ++) {
			
			if ((listUser.get(i).getAlias().compareTo(alias) == 0) && (listUser.get(i).getPassword().compareTo(password) == 0) ){
				//System.out.println("The player is: " + listPlayer.get(i).getAlias());
				return true;
				}
			}

			return false;	
		 	
		}
	// USER EXISTS
	public static User userExists(String alias, String password) {
		
		for(int i = 0; i < listUser.size(); i ++) {
			if ((listUser.get(i).getAlias().compareTo(alias) == 0) && (listUser.get(i).getPassword().compareTo(password) == 0) ){
				//System.out.println("The player is: " + listPlayer.get(i).getAlias());
				return listUser.get(i);
			}
		}
		System.out.println(USEREXISTS);
		return null;	
	 	
	}
	
	// GIVE ME LIST USERS
	public static ArrayList <User> giveMeListUser(){
	
		return listUser;
	}
	
	// SHOW LIST USER
	public static void showUser(ArrayList <User> listUser) {
		
		System.out.println(INFUSER);
		for(User u: listUser) {
			System.out.println(ALIAS 	 + u.getAlias());		// ALIAS
			System.out.println(PASSWORD  + u.getPassword());	  // PASSWORD
			System.out.println(NAME 	 + u.getName()); // NAME
			System.out.println(LASTNAME  + u.getLastName()); // LAST NAME
			System.out.println(BIRTHDAY  + u.getBirthdayDate()); // BIRTHDAY DATE
			System.out.println(SEX	     + u.getSex()); // SEX
			System.out.println(WEIGHT	 + u.getWeight()); // SEX
			
			System.out.println(SEPARATOR);
		
		}
	}

	public String ShowInformation() {
		// TODO Auto-generated method stub
		return null;
	}

	

	
}
