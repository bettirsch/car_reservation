package com.example.jedi.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.jedi.mapper.CarMapper;
import com.example.jedi.mapper.model.Car;
import com.example.jedi.service.CarService;

@Service
public class CarServiceImpl implements CarService{

	private CarMapper carMapper;
	
	public CarServiceImpl(CarMapper carMapper) {
		this.carMapper = carMapper;
	}

	@Override
	public List<Car> getAll() {
		return carMapper.select();
	}

}
