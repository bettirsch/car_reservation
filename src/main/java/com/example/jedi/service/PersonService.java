package com.example.jedi.service;

import java.util.List;

import com.example.jedi.model.Person;

public interface PersonService {

	List<Person> getPersons();

	Person getById(Integer id);

}
