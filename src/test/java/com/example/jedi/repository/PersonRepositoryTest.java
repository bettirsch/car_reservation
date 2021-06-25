package com.example.jedi.repository;

import com.example.jedi.exception.DataNotFoundException;
import com.example.jedi.mapper.PersonMapper;
import com.example.jedi.mapper.model.Car;
import com.example.jedi.mapper.model.Person;
import com.example.jedi.repository.impl.PersonRepositoryImpl;
import com.example.jedi.service.impl.PersonServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PersonRepositoryTest {

	private static final int CAR_NUMBER_OF_WHEEL = 4;
	private static final String CAR_PLATE_NUMBER = "ABC-123";
	private static final String CAR_NAME = "1967 Chevrolet Impala SS 427 Convertiblee";
	private static final int CAR_ID = 1;
	private static final int PERSON_ID = 1;
	private static final String PERSON_FIRST_NAME = "Neil";
	private static final String PERSON_LAST_NAME = "Robertson";

	@Mock
	private PersonMapper personMapper;
	
	private PersonRepository personRepository;

	private List<Person> persons;
	private Person person;
	private List<Car> cars;
	
	@Before
	public void init() {
		this.personRepository = new PersonRepositoryImpl(this.personMapper);
	}
	
	@Test
	public void testGetAllPersons() {
		setupTestData();
		when(this.personMapper.selectMany((SelectStatementProvider) notNull())).thenReturn(this.persons);
		
		List<Person> result =  this.personRepository.selectAllPersons();
		assertThat(result).isNotEmpty();
		assertThat(result.size(), is(1));
		assertThat(result.get(0).getPersonId(), is(PERSON_ID));
		assertThat(result.get(0).getFirstName(), is(PERSON_FIRST_NAME));
		assertThat(result.get(0).getLastName(), is(PERSON_LAST_NAME));
		assertThat(result.get(0).getCars()).hasSameElementsAs(cars);
	}

	@Test
	public void testGetAllPersonsSelectStatementIsCorrect(){
		when(this.personMapper.selectMany((SelectStatementProvider) notNull())).thenReturn(new ArrayList<>());

		this.personRepository.selectAllPersons();
		ArgumentCaptor<SelectStatementProvider> argumentCaptor = ArgumentCaptor.forClass(SelectStatementProvider.class);
		verify(this.personMapper).selectMany(argumentCaptor.capture());
		String expectedSelect = "select person.* from person left join car_to_person on car_to_person.person_id = person.person_id left join car on car_to_person.car_id = car.car_id";
		assertThat(argumentCaptor.getValue().getSelectStatement()).isEqualTo(expectedSelect);
	}
	
	@Test
	public void testGetPersonById() {
		setupTestData();
		when(this.personMapper.selectOne(notNull())).thenReturn(Optional.of(this.person));
		
		Optional<Person> result = this.personRepository.selectPersonById(PERSON_ID);
		assertThat(result).isNotNull().isPresent();
		assertThat(result.get().getPersonId(), is(PERSON_ID));
		assertThat(result.get().getFirstName(), is(PERSON_FIRST_NAME));
		assertThat(result.get().getLastName(), is(PERSON_LAST_NAME));
		assertThat(result.get().getCars()).hasSameElementsAs(cars);
	}


	@Test
	public void testGetPersonByIdSelectStatementIsCorrect(){
		when(this.personMapper.selectOne(notNull())).thenReturn(Optional.empty());
		this.personRepository.selectPersonById(PERSON_ID);

		ArgumentCaptor<SelectStatementProvider> argumentCaptor = ArgumentCaptor.forClass(SelectStatementProvider.class);
		verify(this.personMapper).selectOne(argumentCaptor.capture());
		String expectedSelect = "select person.* from person left join car_to_person on car_to_person.person_id = person.person_id left join car on car_to_person.car_id = car.car_id where person.person_id =";
		assertThat(argumentCaptor.getValue().getSelectStatement()).startsWith(expectedSelect);

		Object parameter = argumentCaptor.getValue().getParameters().get("p1");
		assertThat(parameter).isNotNull().isExactlyInstanceOf(Integer.class);
		assertThat((Integer)parameter).isEqualTo(PERSON_ID);
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
