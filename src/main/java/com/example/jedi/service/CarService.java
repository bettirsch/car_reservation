package com.example.jedi.service;

import java.util.List;

import com.example.jedi.model.Car;

public interface CarService {

	List<Car> getAll();

	public List<Car> findCarsByPersonId(Integer id);

}
