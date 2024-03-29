package com.example.jedi.service;

import java.util.List;

import com.example.jedi.exception.DataNotFoundException;
import com.example.jedi.mapper.model.Car;
import com.example.jedi.mapper.model.Person;

public interface PersonService {

	List<Person> getPersons();

	Person getById(Integer id) throws DataNotFoundException;

	List<Car> findCarsByPersonId(Integer personId) throws DataNotFoundException;

}
