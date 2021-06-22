package com.example.jedi.system;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.jedi.mapper.PersonMapper;
import com.example.jedi.mapper.model.Person;

@RunWith(SpringRunner.class)
@MybatisTest
public class PersonMapperTest {

	@Autowired
	private PersonMapper personMapper;
	
	private static final int PERSON_ID = 1;
	private static final String PERSON_FIRST_NAME = "John";
	private static final String PERSON_LAST_NAME = "Higgins";
	
	@Test
	public void testSelectOne() {
		Optional<Person> result = this.personMapper.selectOne(PERSON_ID);
		assertThat(result).isNotEmpty();
		assertThat(result.get().getPersonId(), is(PERSON_ID));
		assertThat(result.get().getFirstName(), is(PERSON_FIRST_NAME));
		assertThat(result.get().getLastName(), is(PERSON_LAST_NAME));
		assertThat(result.get().getCars()).hasSize(3);
	}
	
	@Test
	public void testSelectOneIsEmptyWhenPersonNotFound() {
		Optional<Person> result = this.personMapper.selectOne(546724454);
		assertThat(result).isEmpty();
	}
	
	@Test
	public void testSelect() {
		List<Person> result = this.personMapper.select();
		assertThat(result).isNotNull().isNotEmpty().hasSize(5);
		assertThat(result.get(0).getPersonId(), is(PERSON_ID));
		assertThat(result.get(0).getFirstName(), is(PERSON_FIRST_NAME));
		assertThat(result.get(0).getLastName(), is(PERSON_LAST_NAME));
		assertThat(result.get(0).getCars()).isNotNull().isEmpty();
	}
}
