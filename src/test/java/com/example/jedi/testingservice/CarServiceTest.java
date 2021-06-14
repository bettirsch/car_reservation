package com.example.jedi.testingservice;

import static org.assertj.core.api.Assertions.assertThat;
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
import com.example.jedi.service.CarService;
import com.example.jedi.service.impl.CarServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {

	private CarService carService;

	@Mock
	private CarMapper carMapper;
	
	private List<Car> cars;
	private Car car;
	
	@Before
	public void init() {
		this.carService = new CarServiceImpl(carMapper);
		this.cars = new ArrayList<>();
		this.car = new Car(1, "trabant", "ABC-123", 4, null);
		cars.add(car);
	}
	
	@Test
	public void testGetAllCars() {
		when(carMapper.select()).thenReturn(cars);

		List<Car> result = carService.getAll();
		assertThat(result).hasSameElementsAs(cars);
	}

}
