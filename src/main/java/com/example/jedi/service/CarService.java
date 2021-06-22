package com.example.jedi.service;

import java.util.List;

import com.example.jedi.exception.DataNotFoundException;
import com.example.jedi.mapper.model.Car;

public interface CarService {

	List<Car> getAll();

	Car getById(Integer carId) throws DataNotFoundException;

}
