package com.example.jedi.testingservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.jedi.mapper.CarMapper;
import com.example.jedi.model.Car;
import com.example.jedi.service.CarService;
import com.example.jedi.service.impl.CarServiceImpl;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CarServiceTest {

	@InjectMocks
	private CarService carService = new CarServiceImpl();

	@Mock
	private CarMapper carMapper;
	
	private List<Car> cars;
	private Car car;
	
	@BeforeAll
	void init() {
		this.cars = new ArrayList<>();
		this.car = new Car(1, "trabant", "ABC-123", 4, null);
		cars.add(car);
	}
	
	@Test
	void getAllTest() {
		when(carMapper.select()).thenReturn(cars);

		List<Car> result = carService.getAll();
		assertThat(result).hasSameElementsAs(cars);
	}

	@Test
	void findCarsByPersonId() {
		Integer personId = 1;
		when(carMapper.selectCarsByPersonId(personId)).thenReturn(cars);

		List<Car> result = carService.findCarsByPersonId(personId);
		verify(carMapper).selectCarsByPersonId(personId);
		assertThat(result).hasSameElementsAs(cars);
	}
}
