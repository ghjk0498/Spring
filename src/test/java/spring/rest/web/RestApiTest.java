package spring.rest.web;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.TestConstructor.AutowireMode;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import spring.rest.service.RestServerService;
import spring.rest.vo.RestVO;

@AutoConfigureMockMvc
@TestConstructor(autowireMode = AutowireMode.ALL)
@SpringBootTest
@ActiveProfiles(profiles = "test")
public class RestApiTest {

	private RestServerService restServerService;
	private MockMvc mockMvc;
	private ObjectMapper objectMapper;

	public RestApiTest(RestServerService restServerService, MockMvc mockMvc, ObjectMapper objectMapper) {
		super();
		this.restServerService = restServerService;
		this.mockMvc = mockMvc;
		this.objectMapper = objectMapper;
	}

	@Test
	public void getTest() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/rest/1")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN)
				.characterEncoding(StandardCharsets.UTF_8.displayName());

		MockHttpServletResponse response = mockMvc.perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andReturn()
				.getResponse();
	}

	@Test
	public void postTest() throws Exception {
		RestVO restVO = new RestVO();
		restVO.setId(7); // auto_increment
		restVO.setTitle("spring-test-title");
		restVO.setText("spring-test-text");
		restVO.setImageUrl("spring-test-imageUrl");

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/rest")
				.content(objectMapper.writeValueAsString(restVO))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN)
				.characterEncoding(StandardCharsets.UTF_8.displayName());

		mockMvc.perform(requestBuilder)
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andReturn()
			.getResponse();
	}

	@Test
	public void putTest() throws Exception {
		RestVO restVO = new RestVO();
		restVO.setId(7);
		restVO.setTitle("spring-test-title-update");
		restVO.setText("spring-test-text-update");
		restVO.setImageUrl("spring-test-imageUrl-update");

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/rest/" + restVO.getId())
				.content(objectMapper.writeValueAsString(restVO))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN)
				.characterEncoding(StandardCharsets.UTF_8.displayName());

		mockMvc.perform(requestBuilder)
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andReturn()
			.getResponse();
	}

	@Test
	public void deleteTest() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/rest/" + 7)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN)
				.characterEncoding(StandardCharsets.UTF_8.displayName());

		mockMvc.perform(requestBuilder)
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
			.andReturn()
			.getResponse();
	}

	@Test
	public void injectionTest() {
		restServerService.getRestVOById(1);
	}

}
