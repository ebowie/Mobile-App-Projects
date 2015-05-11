package com.example.jsondemo;

import java.util.LinkedList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PersonUtils {

	static public class PersonsJSONParser{
		
		static LinkedList<Person> parse(String json) throws JSONException{
			JSONArray personsJSONArray = new JSONArray(json);
			LinkedList<Person> persons = new LinkedList<Person>();
			for(int i=0; i< personsJSONArray.length(); i++){
				JSONObject personJObject = personsJSONArray.getJSONObject(i);
				Person person = new Person(personJObject);
				persons.add(person);
			}
			return persons;
		}
	}
}
