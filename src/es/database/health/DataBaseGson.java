package es.database.health;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import es.user.health.User;
import com.google.gson.*;

public class DataBaseGson {

	private static final String NAMEFILE  = "Users.txt";
	private static final String ERROR 	  = "Error";
	
	private static ArrayList <User> listUser = new ArrayList <User>();
	
	/* Write file.txt */
	public static void writeUsers(ArrayList <User> listUser) {
		
		Gson gson = new Gson();
		String json = gson.toJson(listUser);
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(NAMEFILE));
			bw.write("");
			bw.close();
			bw = new BufferedWriter(new FileWriter(NAMEFILE));
			bw.write(json);
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
}
