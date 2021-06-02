package com.example.jedi.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.example.jedi.model.Person;

@Mapper
public interface PersonMapper {

	@Select("SELECT * FROM person")
	List<Person> getPersons();

	@Select("SELECT * FROM person WHERE person_id = #{personId}")
	@ResultMap(value = "personResult")
	Person getById(Integer personId);

}
