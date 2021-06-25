package com.example.jedi.mapper;

import com.example.jedi.mapper.model.Car;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CarMapper {

	String CAR_RESULT = "com.example.jedi.mapper.CarMapper.carResult";

	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	@ResultMap(CAR_RESULT)
	List<Car> selectMany(SelectStatementProvider selectStatement);

	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	@ResultMap(CAR_RESULT)
	Optional<Car> selectOne(SelectStatementProvider selectStatement);

}