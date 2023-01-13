package es.health.user;

import java.util.ArrayList;

import es.health.database.IShow;
import es.health.exercise.Cycling;
import es.health.exercise.Exercise;
import es.health.exercise.Running;
import es.health.exercise.Swimming;

public class User implements IShow {
	
	private static final String SP 		      = "\n--------------------";
	private static final String CHANG 		  = "\n\n CHANGES";
	private static final String EXERC 		  = "\n\n EXERCICES";
	private static final String ALIAS 		  = "\nAlias: ";
	private static final String PASSWORD      = "\nPassword: ";
	private static final String NAME 		  = "\nName: ";
	private static final String LASTNAME 	  = "\nLast Name: ";
	private static final String BIRTHDAY	  = "\nBirthday: ";
	private static final String AGE	 		  = "\nAge: ";
	private static final String SEX 		  = "\nSex: ";
	private static final String WEIGHT 		  = "\nWeight: ";
	private static final String KG	 		  = " Kg ";
	private static final String HEIGHT 		  = "\nHeight: ";
	private static final String CM	 		  = " cm ";
	private static final String IMC 		  = "\nIMC: ";
	private static final String MALNUTRITION  = "\nThe person is underweight";
	private static final String NORMAL 		  = "\nThe person is on your line";
	private static final String OVERWEIGHT 	  = "\nThe person is overweight";
	private static final String OBESITY 	  = "\nThe person is obese";
	
	protected String alias;
	protected String password;
	protected String name;
	protected String lastName;
	protected String birthdayDate;
	protected int age;
	protected String sex;
	protected int weight;
	protected int height;
	protected Double imc;
	
	protected static ArrayList <Exercise> listExercise = new ArrayList <Exercise>();
	protected static ArrayList <Weight> listWeight = new ArrayList <Weight>();
	
	protected ActivityFactor activity;
	
	public enum ActivityFactor {
		SEDENTARY, LIGHT, MODERATE, INTENSE, EXTREMELYHIGH
	}
	
	protected Suggestion suggestion;
	
	public enum Suggestion{
		MALNUTRITION, NORMAL, OVERWEIGHT, OBESITY
	}
		
	public User() {
		
	}
	
	public User(String alias, String password, String name, String lastName, String birthdayDate, int age, String sex, int weight, int height, ActivityFactor activity) {
		
		this.alias = alias;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.birthdayDate = birthdayDate;
		this.age = age;
		this.sex = sex;
		this.weight = weight;
		this.height = height;
		this.activity = activity;
		
	}
		
	
	// ADD WEIGHT
	public static void addWeight(Weight weight) {
			
		listWeight.add(weight);
				
	}
	
	// ADD EXERCISE
	public static void addExercise(Exercise exercise) {
		
		listExercise.add(exercise);
			
	}
	
	// ADD EXERCISE
	public static void addRunning(Running running) {
			
		listExercise.add(running);
				
	}
	
	// ADD EXERCISE
	public static void addCycling(Cycling cycling) {
				
		listExercise.add(cycling);
					
	}
	
	// ADD EXERCISE
	public static void addSwimming(Swimming swimming) {
			
		listExercise.add(swimming);
				
	}
		

	public void valueIMC() {
		
		double imc = getImc();
		
		if(imc < 18.5) {
			this.suggestion = Suggestion.MALNUTRITION;
		}
		else if(imc >= 18.5 && imc < 25) {
			this.suggestion = Suggestion.NORMAL;
		}
		else if(imc >= 25 && imc < 30) {
			this.suggestion = Suggestion.OVERWEIGHT;
		}
		else{
			this.suggestion = Suggestion.OBESITY;
		}
		
	}
	
	private String getSentence() {
		
		if(suggestion == Suggestion.MALNUTRITION) return MALNUTRITION;
		else if(suggestion == Suggestion.NORMAL) return NORMAL;
		else if(suggestion == Suggestion.OVERWEIGHT) return OVERWEIGHT;
		else return OBESITY;
	}

	public String ShowInformation() {
		
		String informationUser = "";
		informationUser += ALIAS + alias ;
		informationUser += PASSWORD + password ;
		informationUser += NAME + name ;
		informationUser += LASTNAME + lastName ;
		informationUser += BIRTHDAY + birthdayDate ;
		informationUser += AGE + age ;
		informationUser += SEX + sex ;
		informationUser += WEIGHT + weight + KG ;
		informationUser += HEIGHT + height + CM;
		informationUser += IMC + imc;
		informationUser += getSentence();
				// string builder
		if(!listWeight.isEmpty()) {
			for(int i = 0; i < listWeight.size(); i ++ ) {
				informationUser += CHANG;
				informationUser += SP;
				informationUser += listWeight.get(i).ShowInformation();		
			}
		}
	
		if(!listExercise.isEmpty()) {
			for(int i = 0; i < listExercise.size(); i ++ ) {
				informationUser += EXERC;
				informationUser += SP;
				informationUser += listExercise.get(i).ShowInformation();		
			}
		}
				
		return informationUser;
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

	public Double getImc() {
		return imc;
	}

	public void setImc() {
		this.imc = (double) this.weight / (this.height * this.height);
	}

	protected ActivityFactor getActivity() {
		return activity;
	}

	public void setActivity(ActivityFactor activity) {
		this.activity = activity;
	}

	




	
	
}
