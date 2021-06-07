package com.example.jedi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jedi.mapper.CarMapper;
import com.example.jedi.model.Car;
import com.example.jedi.service.CarService;

@Service
public class CarServiceImpl implements CarService{

	@Autowired
	private CarMapper carMapper;
	
	@Override
	public List<Car> getCars() {
		return carMapper.select();
	}

	@Override
	public List<Car> findReservedCarsByPerson(Integer personId) {
		return carMapper.selectCarsByPersonId(personId);
	}

}
