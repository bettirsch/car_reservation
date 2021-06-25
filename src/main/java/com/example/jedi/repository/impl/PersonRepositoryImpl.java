package com.example.jedi.repository.impl;

import com.example.jedi.mapper.PersonMapper;
import com.example.jedi.mapper.model.Person;
import com.example.jedi.mapper.tablemap.CarTableMap;
import com.example.jedi.mapper.tablemap.CarToPersonTableMap;
import com.example.jedi.mapper.tablemap.PersonTableMap;
import com.example.jedi.repository.PersonRepository;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

    private PersonMapper personMapper;

    private BasicColumn[] selectSimpleList = BasicColumn.columnList(PersonTableMap.PERSON_TABLE.allColumns());
    private BasicColumn[] selectList = BasicColumn.columnList(PersonTableMap.PERSON_TABLE.allColumns(), CarTableMap.CAR_TABLE.allColumns());

    public PersonRepositoryImpl(PersonMapper personMapper) {
        this.personMapper = personMapper;
    }

    @Override
    public Optional<Person> selectPersonById(Integer personId) {
        SelectStatementProvider selectStatementProvider = SqlBuilder.select(selectSimpleList)
                .from(PersonTableMap.PERSON_TABLE)
                .leftJoin(CarToPersonTableMap.CAR_TO_PERSON_TABLE,
                        on(CarToPersonTableMap.PERSON_ID, equalTo(PersonTableMap.PERSON_ID)))
                .leftJoin(CarTableMap.CAR_TABLE, on(CarToPersonTableMap.CAR_ID, equalTo(CarTableMap.CAR_ID)))
                .where(PersonTableMap.PERSON_ID, isEqualTo(personId))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return personMapper.selectOne(selectStatementProvider);
    }

    @Override
    public List<Person> selectAllPersons() {
        SelectStatementProvider selectStatementProvider = SqlBuilder.select(selectSimpleList).from(PersonTableMap.PERSON_TABLE)
                .leftJoin(CarToPersonTableMap.CAR_TO_PERSON_TABLE,
                        on(CarToPersonTableMap.PERSON_ID, equalTo(PersonTableMap.PERSON_ID)))
                .leftJoin(CarTableMap.CAR_TABLE, on(CarToPersonTableMap.CAR_ID, equalTo(CarTableMap.CAR_ID)))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return personMapper.selectMany(selectStatementProvider);
    }
}
