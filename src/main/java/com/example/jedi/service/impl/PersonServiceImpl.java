package com.example.jedi.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jedi.mapper.PersonMapper;
import com.example.jedi.model.Person;
import com.example.jedi.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonMapper personMapper;

	@Override
	public List<Person> getPersons() {
		return personMapper.select();
	}

	@Override
	public Optional<Person> getById(Integer id) {
		return personMapper.selectOne(id);
	}

}
