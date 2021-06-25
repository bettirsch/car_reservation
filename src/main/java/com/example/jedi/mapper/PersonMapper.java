package com.example.jedi.mapper;

import com.example.jedi.mapper.model.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PersonMapper{

	String PERSON_RESULT = "com.example.jedi.mapper.PersonMapper.personResult";

	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	@ResultMap(PERSON_RESULT)
	List<Person> selectMany(SelectStatementProvider selectStatement);

	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	@ResultMap(PERSON_RESULT)
	Optional<Person> selectOne(SelectStatementProvider selectStatement);

}
