package com.example.jedi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.jedi.repository.CarRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.jedi.exception.DataNotFoundException;
import com.example.jedi.mapper.model.Car;
import com.example.jedi.mapper.model.Person;
import com.example.jedi.service.impl.CarServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {
	
	@Mock
	private CarRepository carRepository;
	
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
		this.carService = new CarServiceImpl(this.carRepository);
	}
	
	@Test
	public void testGetAllCars() {
		setupTestData();
		when(this.carRepository.selectAllCars()).thenReturn(this.cars);

		List<Car> result = this.carService.getAll();
		assertThat(result).isNotEmpty();
		assertThat(result.size(), is(1));
		assertThat(result.get(0).getCarId(), is(CAR_ID));
		assertThat(result.get(0).getName(), is(CAR_NAME));
		assertThat(result.get(0).getPlateNumber(), is(CAR_PLATE_NUMBER));
		assertThat(result.get(0).getNrOfWheel(), is(CAR_NUMBER_OF_WHEEL));
		assertThat(result.get(0).getPersons()).hasSameElementsAs(PERSONS);
	}

	@Test
	public void testFindCarById() {
		setupTestData();
		when(this.carRepository.selectCarById(CAR_ID)).thenReturn(Optional.of(this.car));
		
		Car result = this.carService.getById(CAR_ID);
		assertThat(result).isNotNull();
		assertThat(result.getCarId(), is(CAR_ID));
		assertThat(result.getName(), is(CAR_NAME));
		assertThat(result.getPlateNumber(), is(CAR_PLATE_NUMBER));
		assertThat(result.getNrOfWheel(), is(CAR_NUMBER_OF_WHEEL));
		assertThat(result.getPersons()).isNotNull().isEmpty();
	}
	
	@Test(expected = DataNotFoundException.class)
	public void testFindCarByIdMustThrowsCustomExceptionIfCarNotFound() {
		when(this.carRepository.selectCarById(CAR_ID)).thenReturn(Optional.empty());
		this.carService.getById(CAR_ID);
	}
	
	private void setupTestData() {
		this.cars = new ArrayList<>();
		this.car = new Car(CAR_ID, CAR_NAME, CAR_PLATE_NUMBER, CAR_NUMBER_OF_WHEEL, PERSONS);
		this.cars.add(this.car);
	}
}
