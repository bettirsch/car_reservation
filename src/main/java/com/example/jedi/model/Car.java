package com.example.jedi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Car {
	private Integer carId;

	private String name;

	private String plateNumber;

	private Integer nrOfWheel;

	@JsonIgnore
	private List<Person> persons;

	public Car() {
		super();
	}

	public Car(Integer carId, String name, String plateNumber, Integer nrOfWheel, List<Person> persons) {
		super();
		this.carId = carId;
		this.name = name;
		this.plateNumber = plateNumber;
		this.nrOfWheel = nrOfWheel;
		this.persons = persons;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public Integer getNrOfWheel() {
		return nrOfWheel;
	}

	public void setNrOfWheel(Integer nrOfWheel) {
		this.nrOfWheel = nrOfWheel;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

}
