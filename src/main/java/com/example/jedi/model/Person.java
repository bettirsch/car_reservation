package com.example.jedi.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {

	private Integer personId;

	private String firstName;

	private String lastName;

	private List<Car> cars;

}
