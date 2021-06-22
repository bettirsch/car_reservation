package com.example.jedi.test.controller;

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

import com.example.jedi.controller.CarController;
import com.example.jedi.exception.DataNotFoundException;
import com.example.jedi.exception.ExceptionMessage;
import com.example.jedi.mapper.model.Car;
import com.example.jedi.mapper.model.Person;
import com.example.jedi.service.CarService;

@RunWith(MockitoJUnitRunner.class)
public class CarControllerTest {
	
	@Mock
	private CarService carService;
	
	private CarController carController;

	private List<Car> cars;
	private Car car;
	
	private final Integer CAR_ID = 1; 
	private final String CAR_NAME = "1967 Chevrolet Impala SS 427 Convertiblee"; 
	private final String CAR_PLATE_NUMBER = "ABC-123"; 
	private final Integer CAR_NUMBER_OF_WHEEL = 4;
	private final List<Person> PERSONS = new ArrayList<>();
	
	@Before
	public void init() {
		this.carController = new CarController(carService);
	}
	
	@Test
	public void testGetAllCars(){
		setupTestData();
		when(this.carService.getAll()).thenReturn(cars);
		
		List<Car> result = this.carController.getAll();
		assertThat(result).isNotEmpty();
		assertThat(result.size(), is(1));
		assertThat(result.get(0).getCarId(), is(CAR_ID));
		assertThat(result.get(0).getName(), is(CAR_NAME));
		assertThat(result.get(0).getPlateNumber(), is(CAR_PLATE_NUMBER));
		assertThat(result.get(0).getNrOfWheel(), is(CAR_NUMBER_OF_WHEEL));
		assertThat(result.get(0).getPersons(), is(PERSONS));
	}
	
	@Test
	public void testGetAllCarsEmpty(){
		this.cars = new ArrayList<>();
		when(this.carService.getAll()).thenReturn(new ArrayList<>());
		
		List<Car> result = this.carController.getAll();
		assertThat(result).isNotNull().isEmpty();
	}
	
	@Test
	public void testFindCarById() {
		setupTestData();
		when(this.carService.getById(CAR_ID)).thenReturn(this.car);
		
		Car result = this.carController.getCarById(CAR_ID);
		assertThat(result).isNotNull();
		assertThat(result.getCarId(), is(CAR_ID));
		assertThat(result.getName(), is(CAR_NAME));
		assertThat(result.getPlateNumber(), is(CAR_PLATE_NUMBER));
		assertThat(result.getNrOfWheel(), is(CAR_NUMBER_OF_WHEEL));
		assertThat(result.getPersons()).isNotNull().isEmpty();
	}
	
	@Test(expected = DataNotFoundException.class)
	public void testFindCarByIdMustThrowsCustomExceptionIfCarNotFound() {
		when(this.carService.getById(CAR_ID)).thenThrow(new DataNotFoundException(ExceptionMessage.CAR_NOT_FOUND));
		this.carController.getCarById(CAR_ID);
	}
	
	private void setupTestData() {
		this.cars = new ArrayList<>();
		this.car = new Car(CAR_ID, CAR_NAME, CAR_PLATE_NUMBER, CAR_NUMBER_OF_WHEEL, PERSONS);
		this.cars.add(this.car);
	}
}
