package com.example.jedi.unittest.testingcontroller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.jedi.controller.PersonController;
import com.example.jedi.exception.CustomException;
import com.example.jedi.exception.ExceptionMessage;
import com.example.jedi.mapper.model.Car;
import com.example.jedi.mapper.model.Person;
import com.example.jedi.service.PersonService;

@RunWith(MockitoJUnitRunner.class)
public class PersonControllerTest {

	private static final int CAR_NUMBER_OF_WHEEL = 4;
	private static final String CAR_PLATE_NUMBER = "ABC-123";
	private static final String CAR_NAME = "1967 Chevrolet Impala SS 427 Convertiblee";
	private static final int CAR_ID = 1;
	private static final int PERSON_ID = 1;
	private static final String PERSON_FIRST_NAME = "Neil";
	private static final String PERSON_LAST_NAME = "Robertson";

	@Mock
	private PersonService personService;

	private PersonController personController;

	private List<Person> persons;
	private Person person;
	private List<Car> cars;

	@Before
	public void init() {
		this.personController = new PersonController(this.personService);
	}

	@Test
	public void testGetAllPersons() throws Exception {
		setupTestData();
		when(this.personService.getPersons()).thenReturn(persons);

		List<Person> result = this.personController.getAll();
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
		when(this.personService.getById(PERSON_ID)).thenReturn(this.person);

		Person result = this.personController.getPerson(PERSON_ID);
		assertThat(result).isNotNull();
		assertThat(result.getPersonId(), is(PERSON_ID));
		assertThat(result.getFirstName(), is(PERSON_FIRST_NAME));
		assertThat(result.getLastName(), is(PERSON_LAST_NAME));
		assertThat(result.getCars()).hasSameElementsAs(cars);
	}

	@Test(expected = CustomException.class)
	public void testGetPersonByIdMustThrowsExceptionIfPersonNotFound() {
		when(this.personService.getById(PERSON_ID)).thenThrow(new CustomException(ExceptionMessage.DATA_NOT_FOUND));
		this.personController.getPerson(PERSON_ID);
	}

	@Test
	public void testFindCarsByPersonId() {
		setupTestData();
		when(this.personService.findCarsByPersonId(PERSON_ID)).thenReturn(this.cars);

		List<Car> result = this.personController.getReservedCarsByPerson(PERSON_ID);
		assertThat(result).isNotEmpty();
		assertThat(result.size(), is(1));
		assertThat(result.get(0).getCarId(), is(CAR_ID));
		assertThat(result.get(0).getName(), is(CAR_NAME));
		assertThat(result.get(0).getPlateNumber(), is(CAR_PLATE_NUMBER));
		assertThat(result.get(0).getNrOfWheel(), is(CAR_NUMBER_OF_WHEEL));
		assertThat(result.get(0).getPersons()).hasSameElementsAs(this.persons);
	}

	@Test(expected = CustomException.class)
	public void testFindCarsByPersonIdMustThrowsExceptionIfPersonNotFound() {
		when(this.personService.findCarsByPersonId(PERSON_ID)).thenThrow(new CustomException(ExceptionMessage.DATA_NOT_FOUND));
		this.personController.getReservedCarsByPerson(PERSON_ID);
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
