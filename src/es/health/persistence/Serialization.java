package es.health.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import es.health.database.DataBase;
import es.health.user.User;
import es.health.user.UserPremium;

public class Serialization {
	
	private static ArrayList <User> listUser = DataBase.giveMeListUser();
	
	/***
	 * 
	 * @throws FileNotFoundException
	 */
    public void persistToJson(String rutaFichero) {
    	//Serializo el objeto a cadena
        //String animalSerializada = this.serialize();
		
    	RuntimeTypeAdapterFactory<User> runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory
			    .of(User.class, "tipo")
			    .registerSubtype(User.class, User.class.getName())
			    .registerSubtype(UserPremium.class, UserPremium.class.getName());
    	
    	Gson gson = new GsonBuilder().registerTypeAdapterFactory(runtimeTypeAdapterFactory).create();
			
        String usersSerializados = gson.toJson(listUser);

        try{
        
        	// Intentamos abrir el fichero de texto
            FileOutputStream fop;
            File file = new File(rutaFichero);
			fop = new FileOutputStream(file);
            OutputStreamWriter osw = new OutputStreamWriter(fop);
            
            osw.write(usersSerializados);//Escribimos la información
            
            osw.close();//Y cerramos el fichero de texto

        }
        catch (IOException e){//Si hay un error, escribimos el mensaje de error en el Log
            e.printStackTrace();
        }
        
    }
    
    public void loadFromJson(String rutaFichero) {//Obtener animal del fichero de texto
        String cadena = "";

        try {// Componemos una cadena de texto a partir de varias lineas de archivo, donde estan guardados los datos de los usuarios
            FileInputStream fis;
            File file = new File(rutaFichero);
            fis = new FileInputStream(file);
            if (fis != null) {
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);
                String linea = "";
                StringBuilder sb = new StringBuilder();
                while ((linea = br.readLine()) != null) {
                    sb.append(linea);//Vamos leyendo linea a linea del texto y los vamos concatenando en un StringBuilder
                }
                isr.close();
                cadena = sb.toString();//El StringBuilder lo transformamos a string
            }
        } catch (FileNotFoundException e) {//Si hay algún error, lo mostramos en el Log
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (cadena.isEmpty()) {
            //return null;
        } else {
        	
        	RuntimeTypeAdapterFactory<User> runtimeTypeAdapterFactory = RuntimeTypeAdapterFactory
    			    .of(User.class, "tipo")
    			    .registerSubtype(User.class, User.class.getName())
    			    .registerSubtype(UserPremium.class, UserPremium.class.getName());
        	
    			Gson gson = new GsonBuilder().registerTypeAdapterFactory(runtimeTypeAdapterFactory).create();
    			
    		
    			
    			
        		Type listType = new TypeToken<ArrayList<User>>(){}.getType();
        		
        		listUser = gson.fromJson(cadena.split("\\n")[0], listType);        	
        		
        		
        		
        		//Deserializar
        		/*
            Gson gson = new Gson();
            return gson.fromJson(cadena.split("\\n")[0], AnimalPersist.class);
            */
        }
    }
    
	private String serialize(){
		Gson gson = new Gson();
		
        return gson.toJson(this);//Serializamos la animal
	}

}
