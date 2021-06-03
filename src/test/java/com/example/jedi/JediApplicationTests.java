package com.example.jedi;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jedi.controller.CarController;
import com.example.jedi.controller.PersonController;

@SpringBootTest
class JediApplicationTests {

	@Autowired
	private CarController carController;

	@Autowired
	private PersonController personController;
	
	@Test
	void contextLoads() throws Exception{
		assertThat(carController).isNotNull();
		assertThat(personController).isNotNull();
	}

}
