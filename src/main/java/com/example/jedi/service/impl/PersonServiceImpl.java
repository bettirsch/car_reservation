package com.example.jedi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jedi.mapper.PersonMapper;
import com.example.jedi.model.Person;
import com.example.jedi.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	private PersonMapper personMapper;

	@Autowired	
	public PersonServiceImpl(PersonMapper personMapper) {
		this.personMapper = personMapper;
	}

	@Override
	public List<Person> getPersons() {
		return personMapper.getPersons();
	}

	@Override
	public Person getById(Integer id) {
		return personMapper.getById(id);
	}

}
