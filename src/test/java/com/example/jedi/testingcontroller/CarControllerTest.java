package com.example.jedi.testingcontroller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CarControllerTest {
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	private MockMvc mockMvc;

	@BeforeAll
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	void getAll() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/car/all").accept(MediaType.ALL))
				.andExpect(status().isOk()).andReturn();
		String json = result.getResponse().getContentAsString();
		assertThat(json).isNotEmpty();
	}

}
