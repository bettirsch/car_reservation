package com.example.jedi.testingcontroller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.jedi.controller.CarController;
import com.example.jedi.mapper.model.Car;
import com.example.jedi.service.CarService;

@RunWith(MockitoJUnitRunner.class)
public class CarControllerTest {
	
	private CarController carController;
	
	@Mock
	private CarService carService;

	private List<Car> cars;
	private Car car;
	
	@Before
	public void init() {
		this.carController = new CarController(carService);
		this.cars = new ArrayList<>();
		this.car = new Car(1, "trabant", "ABC-123", 4, null);
		this.cars.add(this.car);
	}
	
	@Test
	public void testGetAllCars() throws Exception {
		when(this.carService.getAll()).thenReturn(cars);
		
		List<Car> result = this.carController.getAll();
		assertThat(result).hasSameElementsAs(cars);
	}

}
