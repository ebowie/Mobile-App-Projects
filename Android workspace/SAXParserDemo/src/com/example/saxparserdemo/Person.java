package com.example.saxparserdemo;

//http://liisp.uncc.edu/~mshehab/api/xml/person.xml

public class Person {
	String name, department, id;
	int age;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", department=" + department + ", id="
				+ id + ", age=" + age + "]";
	}
}
