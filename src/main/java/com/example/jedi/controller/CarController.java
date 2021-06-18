package com.example.jedi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jedi.mapper.model.Car;
import com.example.jedi.service.CarService;

@RestController
@RequestMapping("/api/car")
public class CarController {

	private CarService carService;
	
	public CarController(CarService carService){
		this.carService = carService;
	}

	@GetMapping("/all") 
	public List<Car> getAll() throws RuntimeException{
		return carService.getAll();
	}
	
}
