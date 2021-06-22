package com.example.jedi.system;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.jedi.mapper.CarMapper;
import com.example.jedi.mapper.model.Car;

@RunWith(SpringRunner.class)
@MybatisTest
public class CarMapperTest {

	@Autowired
	private CarMapper carMapper;

	private final Integer CAR_ID = 1;
	private final String CAR_NAME = "1969 Ford Mustang Convertible 351 cui";
	private final String CAR_PLATE_NUMBER = "abc-123";
	private final Integer CAR_NUMBER_OF_WHEEL = 4;

	@Test
	public void testSelectOne() {
		Optional<Car> result = this.carMapper.selectOne(CAR_ID);
		assertThat(result).isNotEmpty();
		assertThat(result.get().getCarId(), is(CAR_ID));
		assertThat(result.get().getName(), is(CAR_NAME));
		assertThat(result.get().getPlateNumber(), is(CAR_PLATE_NUMBER));
		assertThat(result.get().getNrOfWheel(), is(CAR_NUMBER_OF_WHEEL));
	}

	@Test
	public void testSelectOneIsEmptyWhenCarNotFound() {
		Optional<Car> result = this.carMapper.selectOne(546724454);
		assertThat(result).isEmpty();
	}

	@Test
	public void testSelect() {
		List<Car> result = this.carMapper.select();
		assertThat(result).isNotNull().isNotEmpty().hasSize(5);
		assertThat(result.get(0).getCarId(), is(CAR_ID));
		assertThat(result.get(0).getName(), is(CAR_NAME));
		assertThat(result.get(0).getPlateNumber(), is(CAR_PLATE_NUMBER));
		assertThat(result.get(0).getNrOfWheel(), is(CAR_NUMBER_OF_WHEEL));
		assertThat(result.get(0).getPersons()).isNull();
	}

	@Test
	public void testFindCarById() {
		Optional<Car> result = this.carMapper.selectOne(CAR_ID);
		assertThat(result).isNotNull().isNotEmpty();
		assertThat(result.get().getCarId(), is(CAR_ID));
		assertThat(result.get().getName(), is(CAR_NAME));
		assertThat(result.get().getPlateNumber(), is(CAR_PLATE_NUMBER));
		assertThat(result.get().getNrOfWheel(), is(CAR_NUMBER_OF_WHEEL));
		assertThat(result.get().getPersons()).isNull();
	}

	@Test
	public void testFindCarByIdMustThrowsCustomExceptionIfCarNotFound() {
		Optional<Car> result = this.carMapper.selectOne(546724454);
		assertThat(result).isEmpty();
	}
}
