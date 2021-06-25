package com.example.jedi.repository;

import com.example.jedi.exception.DataNotFoundException;
import com.example.jedi.mapper.CarMapper;
import com.example.jedi.mapper.model.Car;
import com.example.jedi.mapper.model.Person;
import com.example.jedi.mapper.tablemap.CarTableMap;
import com.example.jedi.repository.impl.CarRepositoryImp;
import com.example.jedi.service.impl.CarServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@RunWith(MockitoJUnitRunner.class)
public class CarRepositoryTest {
	
	@Mock
	private CarMapper carMapper;
	
	private CarRepository carRepository;

	private List<Car> cars;
	private Car car;
	
	private final Integer CAR_ID = 1; 
	private final String CAR_NAME = "1967 Chevrolet Impala SS 427 Convertiblee"; 
	private final String CAR_PLATE_NUMBER = "ABC-123"; 
	private final Integer CAR_NUMBER_OF_WHEEL = 4;
	private final List<Person> PERSONS = new ArrayList<>();
	
	@Before
	public void init() {
		this.carRepository = new CarRepositoryImp(this.carMapper);
	}
	
	@Test
	public void testGetAllCars() {
		setupTestData();
		when(this.carMapper.selectMany(notNull())).thenReturn(this.cars);

		List<Car> result = this.carRepository.selectAllCars();
		assertThat(result).isNotEmpty();
		assertThat(result.size(), is(1));
		assertThat(result.get(0).getCarId(), is(CAR_ID));
		assertThat(result.get(0).getName(), is(CAR_NAME));
		assertThat(result.get(0).getPlateNumber(), is(CAR_PLATE_NUMBER));
		assertThat(result.get(0).getNrOfWheel(), is(CAR_NUMBER_OF_WHEEL));
		assertThat(result.get(0).getPersons()).hasSameElementsAs(PERSONS);
	}

	@Test
	public void testGetAllCarsSelectStatementIsCorrect(){
		when(this.carMapper.selectMany(notNull())).thenReturn(new ArrayList<>());

		this.carRepository.selectAllCars();
		ArgumentCaptor<SelectStatementProvider> argumentCaptor = ArgumentCaptor.forClass(SelectStatementProvider.class);
		verify(this.carMapper).selectMany(argumentCaptor.capture());
		String expectedSelect = "select * from car";
		assertThat(argumentCaptor.getValue().getSelectStatement()).isEqualTo(expectedSelect);
	}

	@Test
	public void testFindCarById() {
		setupTestData();
		when(this.carMapper.selectOne(notNull())).thenReturn(Optional.of(this.car));
		
		Optional<Car> result = this.carRepository.selectCarById(CAR_ID);
		assertThat(result).isNotNull().isPresent();
		assertThat(result.get().getCarId(), is(CAR_ID));
		assertThat(result.get().getName(), is(CAR_NAME));
		assertThat(result.get().getPlateNumber(), is(CAR_PLATE_NUMBER));
		assertThat(result.get().getNrOfWheel(), is(CAR_NUMBER_OF_WHEEL));
		assertThat(result.get().getPersons()).isNotNull().isEmpty();
	}

	@Test
	public void FestFindCarByIdSelectStatementIsCorrect(){
		when(this.carMapper.selectOne(notNull())).thenReturn(Optional.empty());

		this.carRepository.selectCarById(CAR_ID);
		ArgumentCaptor<SelectStatementProvider> argumentCaptor = ArgumentCaptor.forClass(SelectStatementProvider.class);
		verify(this.carMapper).selectOne(argumentCaptor.capture());
		String expectedSelect = "";
		assertThat(argumentCaptor.getValue().getSelectStatement()).startsWith(expectedSelect);
		Object parameter = argumentCaptor.getValue().getParameters().get("p1");
		assertThat(parameter).isNotNull().isExactlyInstanceOf(Integer.class);
		assertThat((Integer)parameter).isEqualTo(CAR_ID);
	}

	private void setupTestData() {
		this.cars = new ArrayList<>();
		this.car = new Car(CAR_ID, CAR_NAME, CAR_PLATE_NUMBER, CAR_NUMBER_OF_WHEEL, PERSONS);
		this.cars.add(this.car);
	}
}
