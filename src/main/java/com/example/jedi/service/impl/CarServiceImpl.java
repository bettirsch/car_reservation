package com.example.jedi.service.impl;

import com.example.jedi.exception.DataNotFoundException;
import com.example.jedi.exception.ExceptionMessage;
import com.example.jedi.mapper.model.Car;
import com.example.jedi.repository.CarRepository;
import com.example.jedi.service.CarService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService{

	private CarRepository carRepository;
	
	public CarServiceImpl(CarRepository carRepository) {
		this.carRepository = carRepository;
	}

	@Override
	public List<Car> getAll() {
		return carRepository.selectAllCars();
	}

	@Override
	public Car getById(Integer carId) throws DataNotFoundException {
		return carRepository.selectCarById(carId).orElseThrow(() -> new DataNotFoundException(ExceptionMessage.CAR_NOT_FOUND));
	}

}
