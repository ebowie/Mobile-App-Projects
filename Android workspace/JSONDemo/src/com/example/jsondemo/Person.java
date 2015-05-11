package com.example.jsondemo;

import org.json.JSONException;
import org.json.JSONObject;

public class Person {
	String name, id, age, department;

	public Person(JSONObject personJObject){
		
		try {
			this.name = personJObject.getString("name");
			this.department = personJObject.getString("department");
			this.id = personJObject.getInt("id")+"";
			this.age = personJObject.getInt("age")+""; 
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", id=" + id + ", age=" + age
				+ ", department=" + department + "]";
	}
}
