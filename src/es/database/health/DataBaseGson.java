package es.database.health;

import java.util.ArrayList;

import es.user.health.User;

public class DataBaseGson {

	private static final String NAMEFILE  = "Users";
	private static final String ERROR 	  = "Error";
	
	public static ArrayList <User> listUser = DataBase.giveMeListUser();
	
	
}
