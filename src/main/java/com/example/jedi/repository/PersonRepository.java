package com.example.jedi.repository;

import com.example.jedi.mapper.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository {

    Optional<Person> selectPersonById(Integer personId);

    List<Person> selectAllPersons();
}
