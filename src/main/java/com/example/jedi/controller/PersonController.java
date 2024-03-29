package com.example.jedi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.jedi.exception.DataNotFoundException;
import com.example.jedi.mapper.model.Car;
import com.example.jedi.mapper.model.Person;
import com.example.jedi.service.PersonService;

@RestController
@RequestMapping("/api/person")
public class PersonController {

	private PersonService personService;

	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@GetMapping("/all")
	public List<Person> getAll() {
		return personService.getPersons();
	}

	@GetMapping("/{id}")
	public Person getPerson(@PathVariable Integer id) throws DataNotFoundException {
		return personService.getById(id);
	}

	@GetMapping("/{id}/cars")
	public List<Car> getReservedCarsByPerson(@PathVariable Integer id) throws DataNotFoundException {
		return personService.findCarsByPersonId(id);
	}
}