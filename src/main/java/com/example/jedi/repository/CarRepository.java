package com.example.jedi.repository;

import com.example.jedi.mapper.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarRepository {

    List<Car> selectAllCars();

    Optional<Car> selectCarById(Integer id);
}
