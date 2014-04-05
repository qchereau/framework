package main.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class CloningUtil {

	private static HashMap<Class, Integer> alreadyMetClass = new HashMap<Class, Integer>();  

	public static <T> List<T> clone(List<T> consideredList){
		if(consideredList.size()==0){
			return new LinkedList<T>();
		}
		T firstElement = consideredList.get(0); 	
		List<T> returnedList = new LinkedList<T>();
		if(firstElement.getClass().equals(Integer.class)||firstElement.getClass().equals(String.class)){
			for(int i=0; i<consideredList.size(); i++){
				returnedList.add(consideredList.get(0));
			}
			return returnedList;
		}else{
			try {
				Class<? extends Object> consideredClass = firstElement.getClass(); 
				Constructor<?> consideredConstructor = null;
				int bestPosition =0;
				if(alreadyMetClass.containsKey(consideredClass)){
					consideredConstructor = firstElement.getClass().getConstructors()[alreadyMetClass.get(consideredClass)];
				}else{
					Constructor<?>[] constructors = firstElement.getClass().getConstructors();
					int maxParameter =0;
					bestPosition =-1;
					for(int i=0; i<constructors.length ; i++){
						//TODO valider tout les champs
						Constructor<?> aConstructor = constructors[i];
						if(aConstructor.getParameterCount()>maxParameter){
							maxParameter=aConstructor.getParameterCount();
							bestPosition=i;
						}
					}
					consideredConstructor = constructors[bestPosition];
				}
				
				alreadyMetClass.put(consideredClass, bestPosition);
				return returnedList;
			} catch (SecurityException e) {
				e.printStackTrace();
			}
			return null;
		}

	}
}
