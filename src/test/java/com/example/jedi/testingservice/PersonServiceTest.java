package com.example.jedi.testingservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.jedi.mapper.CarMapper;
import com.example.jedi.mapper.PersonMapper;
import com.example.jedi.mapper.model.Car;
import com.example.jedi.mapper.model.Person;
import com.example.jedi.service.PersonService;
import com.example.jedi.service.impl.PersonServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class PersonServiceTest {
	
	private PersonService personService;
	
	@Mock
	private PersonMapper personMapper;
	@Mock
	private CarMapper carMapper;

	private List<Person> persons;
	private Person person;
	
	@Before
	public void init() {
		this.personService = new PersonServiceImpl(personMapper, carMapper);
		this.persons = new ArrayList<>();
		this.person = new Person(1, "Pista", "Kiss", null);
		this.persons.add(this.person);
	}
	
	@Test
	public void testGetPersons() {
		when(personMapper.select()).thenReturn(persons);
		
		List<Person> result =  personService.getPersons();
		assertThat(result).hasSameElementsAs(persons);
	}
	
	@Test
	public void testGetPersonById() {
		when(personMapper.selectOne(1)).thenReturn(Optional.of(person));
		
		Optional<Person> result = personService.getById(1);
		assertThat(result.get()).isNotNull();
	}
	
	@Test
	public void testFindCarsByPersonId() {
		Integer personId = 1;
		List<Car> cars = new ArrayList<>();
		Car car = new Car(1, "trabant", "ABC-123", 4, persons);
		cars.add(car);
		when(carMapper.selectCarsByPersonId(personId)).thenReturn(cars);

		List<Car> result = personService.findCarsByPersonId(personId);
		assertThat(result).hasSameElementsAs(cars);
	}
}
