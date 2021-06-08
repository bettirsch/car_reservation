package com.example.jedi.mapper;

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
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonInsertMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.example.jedi.mapper.tablemap.CarTableMap;
import com.example.jedi.mapper.tablemap.CarToPersonTableMap;
import com.example.jedi.mapper.tablemap.PersonTableMap;
import com.example.jedi.model.Person;

@Mapper
public interface PersonMapper
		extends CommonCountMapper, CommonDeleteMapper, CommonInsertMapper<Person>, CommonUpdateMapper {

	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	@ResultMap("personResult")
	List<Person> selectMany(SelectStatementProvider selectStatement);

	@SelectProvider(type = SqlProviderAdapter.class, method = "select")
	@ResultMap("personResult")
	Optional<Person> selectOne(SelectStatementProvider selectStatement);

	BasicColumn[] selectList = BasicColumn.columnList(PersonTableMap.PERSON_TABLE.allColumns(), CarTableMap.CAR_TABLE.allColumns());

	default List<Person> select() {
		QueryExpressionDSL<SelectModel> select = SqlBuilder.select(selectList).from(PersonTableMap.PERSON_TABLE)
				.join(CarToPersonTableMap.CAR_TO_PERSON_TABLE,
						on(CarToPersonTableMap.PERSON_ID, equalTo(PersonTableMap.PERSON_ID)))
				.join(CarTableMap.CAR_TABLE, on(CarToPersonTableMap.CAR_ID, equalTo(CarTableMap.CAR_ID)));
		SelectDSLCompleter completer = c -> c;
		return MyBatis3Utils.selectList(this::selectMany, select, completer);
	}

	default Optional<Person> selectOne(Integer id) {
		QueryExpressionDSL<SelectModel> select = SqlBuilder.select(selectList).from(PersonTableMap.PERSON_TABLE)
				.join(CarToPersonTableMap.CAR_TO_PERSON_TABLE,
						on(CarToPersonTableMap.PERSON_ID, equalTo(PersonTableMap.PERSON_ID)))
				.join(CarTableMap.CAR_TABLE, on(CarToPersonTableMap.CAR_ID, equalTo(CarTableMap.CAR_ID)));
		SelectDSLCompleter completer = c -> c.where(CarToPersonTableMap.PERSON_ID, isEqualTo(id));
		return MyBatis3Utils.selectOne(this::selectOne, select, completer);
	}
}
