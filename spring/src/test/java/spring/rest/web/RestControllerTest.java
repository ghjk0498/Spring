package spring.rest.web;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import spring.config.DBConfig;
import spring.config.RootConfig;
import spring.config.WebConfig;

@WebAppConfiguration()
@ContextConfiguration(classes={RootConfig.class, WebConfig.class, DBConfig.class})
class RestControllerTest {

	@Autowired
	WebApplicationContext context;
	
	MockMvc mockMvc;
	
	@BeforeEach
	void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	void testTest() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/spring/");
		mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
	}

}
