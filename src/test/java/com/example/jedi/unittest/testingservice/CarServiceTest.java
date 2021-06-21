package com.example.jedi.unittest.testingservice;

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

import com.example.jedi.mapper.CarMapper;
import com.example.jedi.mapper.model.Car;
import com.example.jedi.mapper.model.Person;
import com.example.jedi.service.CarService;
import com.example.jedi.service.impl.CarServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {
	
	@Mock
	private CarMapper carMapper;
	
	private CarService carService;

	private List<Car> cars;
	private Car car;
	
	private final Integer CAR_ID = 1; 
	private final String CAR_NAME = "1967 Chevrolet Impala SS 427 Convertiblee"; 
	private final String CAR_PLATE_NUMBER = "ABC-123"; 
	private final Integer CAR_NUMBER_OF_WHEEL = 4;
	private final List<Person> PERSONS = new ArrayList<>();
	
	@Before
	public void init() {
		this.carService = new CarServiceImpl(this.carMapper);
	}
	
	@Test
	public void testGetAllCars() {
		setupTestData();
		when(this.carMapper.select()).thenReturn(this.cars);

		List<Car> result = this.carService.getAll();
		assertThat(result).isNotEmpty();
		assertThat(result.size(), is(1));
		assertThat(result.get(0).getCarId(), is(CAR_ID));
		assertThat(result.get(0).getName(), is(CAR_NAME));
		assertThat(result.get(0).getPlateNumber(), is(CAR_PLATE_NUMBER));
		assertThat(result.get(0).getNrOfWheel(), is(CAR_NUMBER_OF_WHEEL));
		assertThat(result.get(0).getPersons()).hasSameElementsAs(PERSONS);
	}

	private void setupTestData() {
		this.cars = new ArrayList<>();
		this.car = new Car(CAR_ID, CAR_NAME, CAR_PLATE_NUMBER, CAR_NUMBER_OF_WHEEL, PERSONS);
		this.cars.add(this.car);
	}
}
