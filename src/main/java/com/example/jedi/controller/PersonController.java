package com.example.jedi.controller;

import java.util.List;

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
@RequestMapping("/rest/person")
public class PersonController {

	private PersonService personService;
	private CarService carService;
	
	@Autowired
	public PersonController(PersonService personService, CarService carService) {
		this.personService = personService;
		this.carService = carService;
	}
	
	@GetMapping("/all")
	public List<Person> getAll(){
		return personService.getPersons();
	}
	
	@GetMapping("/{id}")
	public Person getPerson(@PathVariable Integer id){
		return personService.getById(id);
	}
	
	@GetMapping("/{id}/reservedCars")
	public List<Car> getReservedCarsByPerson(@PathVariable Integer id){
		return carService.findReservedCarsByPerson(id);
	}
}
