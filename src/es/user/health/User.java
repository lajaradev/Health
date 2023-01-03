package es.user.health;

import java.util.ArrayList;

import es.database.health.DataBase;
import es.exercise.health.Exercise;

public class User {
	
	private String alias;
	private String password;
	private String name;
	private String lastName;
	private String sex;
	private String birthdayDate;
	
	private int age;
	private int weight;
	private int height;
	private int imc;
	
	private ActivityFactor activity;
	
	public enum ActivityFactor {
		SEDENTARY, LIGHT, MODERATE, INTENSE, EXTREMELYHIGH
	}
	
	public static ArrayList <Exercise> listExercise = new ArrayList <Exercise>();
	
	
	public User(String alias, String password, String name, String lastName, String birthdayDate, String sex, int weight, int height, ActivityFactor activity) {
		
		this.alias = alias;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.birthdayDate = birthdayDate;
		this.sex = sex;
		this.weight = weight;
		this.height = height;
		this.activity = activity;
		
	}
		
	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getBirthdayDate() {
		return birthdayDate;
	}

	public void setBirthdayDate(String birthdayDate) {
		this.birthdayDate = birthdayDate;
	}


	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getImc() {
		return imc;
	}

	public void setImc(int imc) {
		this.imc = imc;
	}

	protected ActivityFactor getActivity() {
		return activity;
	}

	public void setActivity(ActivityFactor activity) {
		this.activity = activity;
	}

	public static ArrayList <Exercise> getListExercise() {
		return listExercise;
	}

	public static void setListExercise(ArrayList <Exercise> listExercise) {
		User.listExercise = listExercise;
	}



	
	
}
