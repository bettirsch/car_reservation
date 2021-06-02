package com.example.jedi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class JediApplication {

	public static void main(String[] args) {
		SpringApplication.run(JediApplication.class, args);
	}

}
