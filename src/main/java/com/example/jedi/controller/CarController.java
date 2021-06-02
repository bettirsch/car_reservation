package com.example.jedi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jedi.model.Car;
import com.example.jedi.service.CarService;

@RestController
@RequestMapping("/rest/car")
public class CarController {

	private CarService carService;
	
	@Autowired
	public CarController(CarService carService) {
		this.carService = carService;
	}
	
	@GetMapping("/all")
	public List<Car> getAll() {
		return carService.getCars();
	}
	
}
