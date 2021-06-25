package com.example.jedi.repository.impl;

import com.example.jedi.mapper.CarMapper;
import com.example.jedi.mapper.model.Car;
import com.example.jedi.mapper.tablemap.CarTableMap;
import com.example.jedi.repository.CarRepository;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.SqlBuilder;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Repository
public class CarRepositoryImp implements CarRepository {

    private CarMapper carMapper;

    private final static BasicColumn[] SELECT_LIST = BasicColumn.columnList(CarTableMap.CAR_TABLE.allColumns());

    public CarRepositoryImp(CarMapper carMapper) {
        this.carMapper = carMapper;
    }

    @Override
    public List<Car> selectAllCars() {
        SelectStatementProvider selectStatement = SqlBuilder.select(SELECT_LIST)
                .from(CarTableMap.CAR_TABLE)
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return carMapper.selectMany(selectStatement);
    }

    @Override
    public Optional<Car> selectCarById(Integer id) {
        SelectStatementProvider selectStatement = SqlBuilder.select(SELECT_LIST)
                .from(CarTableMap.CAR_TABLE)
                .where(CarTableMap.CAR_ID, isEqualTo(id))
                .build()
                .render(RenderingStrategies.MYBATIS3);
        return carMapper.selectOne(selectStatement);
    }
}
