package com.example.jedi.service.impl;

import java.util.List;

import com.example.jedi.repository.PersonRepository;
import org.springframework.stereotype.Service;

import com.example.jedi.exception.DataNotFoundException;
import com.example.jedi.exception.ExceptionMessage;
import com.example.jedi.mapper.PersonMapper;
import com.example.jedi.mapper.model.Car;
import com.example.jedi.mapper.model.Person;
import com.example.jedi.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	private PersonRepository personRepository;

	public PersonServiceImpl(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	@Override
	public List<Person> getPersons() {
		return personRepository.selectAllPersons();
	}

	@Override
	public Person getById(Integer id) throws DataNotFoundException {
		return personRepository.selectPersonById(id).orElseThrow(() -> new DataNotFoundException(ExceptionMessage.PERSON_NOT_FOUND));
	}

	@Override
	public List<Car> findCarsByPersonId(Integer personId) throws DataNotFoundException {
		return this.getById(personId).getCars();
	}
}
