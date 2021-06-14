package com.example.jedi.mapper.model;

import java.util.List;

public class Person {

	private Integer personId;

	private String firstName;

	private String lastName;

	private List<Car> cars;

	public Person() {
	}

	public Person(Integer personId, String firstName, String lastName, List<Car> cars) {
		this.personId = personId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.cars = cars;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

}
