package com.example.jedi.service;

import java.util.List;

import com.example.jedi.model.Car;

public interface CarService {

	List<Car> getCars();

	List<Car> findReservedCarsByPerson(Integer id);

}
