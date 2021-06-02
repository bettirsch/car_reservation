package com.example.jedi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.jedi.model.Car;

@Mapper
public interface CarMapper {

	@Select("SELECT * FROM car")
	List<Car> getCars();

	@Select("SELECT c.* FROM car as c"
			+ " INNER JOIN car_to_person as cp ON cp.car_id = c.car_id "
			+ "WHERE cp.person_id = #{personId}")
	List<Car> findReservedCarsByPerson(Integer personId);
}