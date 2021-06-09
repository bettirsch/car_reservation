package com.example.jedi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jedi.model.Car;
import com.example.jedi.model.Person;
import com.example.jedi.service.CarService;
import com.example.jedi.service.PersonService;

@RestController
@RequestMapping("/api/person")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private CarService carService;
	
	@GetMapping("/all")
	public List<Person> getAll(){
		return personService.getPersons();
	}
	
	@GetMapping("/{id}")
	public Optional<Person> getPerson(@PathVariable Integer id){
		return personService.getById(id);
	}
	
	@GetMapping("/{id}/reservedCars")
	public List<Car> getReservedCarsByPerson(@PathVariable Integer id){
		return carService.findReservedCarsByPerson(id);
	}
}
