package com.example.jedi.testingservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
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

	private static final int CAR_NUMBER_OF_WHEEL = 4;
	private static final String CAR_PLATE_NUMBER = "ABC-123";
	private static final String CAR_NAME = "1967 Chevrolet Impala SS 427 Convertiblee";
	private static final int CAR_ID = 1;
	private static final int PERSON_ID = 1;
	private static final String PERSON_FIRST_NAME = "Neil";
	private static final String PERSON_LAST_NAME = "Robertson";
	@Mock
	private PersonMapper personMapper;
	@Mock
	private CarMapper carMapper;
	
	private PersonService personService;

	private List<Person> persons;
	private Person person;
	private List<Car> cars;
	
	@Before
	public void init() {
		this.personService = new PersonServiceImpl(this.personMapper, this.carMapper);
	}
	
	@Test
	public void testGetPersons() {
		setupTestData();
		when(this.personMapper.select()).thenReturn(this.persons);
		
		List<Person> result =  this.personService.getPersons();
		assertThat(result).isNotEmpty();
		assertThat(result.size(), is(1));
		assertThat(result.get(0).getPersonId(), is(PERSON_ID));
		assertThat(result.get(0).getFirstName(), is(PERSON_FIRST_NAME));
		assertThat(result.get(0).getLastName(), is(PERSON_LAST_NAME));
		assertThat(result.get(0).getCars()).hasSameElementsAs(cars);
	}
	
	@Test
	public void testGetPersonById() {
		setupTestData();
		when(this.personMapper.selectOne(PERSON_ID)).thenReturn(Optional.of(this.person));
		
		Optional<Person> result = this.personService.getById(PERSON_ID);
		assertThat(result).isNotEmpty().hasValue(this.person);
	}
	
	@Test
	public void testFindCarsByPersonId() {
		setupTestData();
		when(this.carMapper.selectCarsByPersonId(PERSON_ID)).thenReturn(this.cars);

		List<Car> result = this.personService.findCarsByPersonId(PERSON_ID);
		assertThat(result).isNotEmpty();
		assertThat(result.size(), is(1));
		assertThat(result.get(0).getCarId(), is(CAR_ID));
		assertThat(result.get(0).getName(), is(CAR_NAME));
		assertThat(result.get(0).getPlateNumber(), is(CAR_PLATE_NUMBER));
		assertThat(result.get(0).getNrOfWheel(), is(CAR_NUMBER_OF_WHEEL));
		assertThat(result.get(0).getPersons()).hasSameElementsAs(this.persons);
	}
	
	private void setupTestData() {
		this.persons = new ArrayList<>();
		this.cars = new ArrayList<>();
		
		this.person = new Person(PERSON_ID, PERSON_FIRST_NAME, PERSON_LAST_NAME, this.cars);
		this.persons.add(this.person);

		Car car = new Car(CAR_ID, CAR_NAME, CAR_PLATE_NUMBER, CAR_NUMBER_OF_WHEEL, this.persons);
		cars.add(car);
	}
}
