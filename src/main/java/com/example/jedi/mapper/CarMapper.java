package com.example.jedi.mapper;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.select.QueryExpressionDSL;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectModel;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

import com.example.jedi.mapper.model.Car;
import com.example.jedi.mapper.tablemap.CarTableMap;

@Mapper
public interface CarMapper {

	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	@ResultMap("carResult")
	List<Car> selectMany(SelectStatementProvider selectStatement);

	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	@ResultMap("carResult")
	Optional<Car> selectOne(SelectStatementProvider selectStatement);

	BasicColumn[] selectList = BasicColumn.columnList(CarTableMap.CAR_TABLE.allColumns());

	default List<Car> select() {
		QueryExpressionDSL<SelectModel> select = SqlBuilder.select(selectList).from(CarTableMap.CAR_TABLE);
		SelectDSLCompleter completer = c -> c;
		return MyBatis3Utils.selectList(this::selectMany, select, completer);
	}

	default Optional<Car> selectOne(Integer id) {
		QueryExpressionDSL<SelectModel> select = SqlBuilder.select(selectList).from(CarTableMap.CAR_TABLE);
		SelectDSLCompleter completer = c -> c.where(CarTableMap.CAR_ID, isEqualTo(id));
		return MyBatis3Utils.selectOne(this::selectOne, select, completer);
	}
}