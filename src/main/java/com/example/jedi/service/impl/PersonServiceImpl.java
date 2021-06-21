package com.example.jedi.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.jedi.exception.CustomException;
import com.example.jedi.exception.ExceptionMessage;
import com.example.jedi.mapper.CarMapper;
import com.example.jedi.mapper.PersonMapper;
import com.example.jedi.mapper.model.Car;
import com.example.jedi.mapper.model.Person;
import com.example.jedi.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	private PersonMapper personMapper;
	private CarMapper carMapper;

	public PersonServiceImpl(PersonMapper personMapper, CarMapper carMapper) {
		this.personMapper = personMapper;
		this.carMapper = carMapper;
	}

	@Override
	public List<Person> getPersons() {
		return personMapper.select();
	}

	@Override
	public Person getById(Integer id) throws CustomException{
		return personMapper.selectOne(id).orElseThrow(() -> new CustomException(ExceptionMessage.DATA_NOT_FOUND));
	}
	
	@Override
	public List<Car> findCarsByPersonId(Integer personId) throws CustomException{
		this.getById(personId);
		return carMapper.selectCarsByPersonId(personId);
	}
}
