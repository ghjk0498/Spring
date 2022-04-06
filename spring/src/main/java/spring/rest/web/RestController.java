package spring.rest.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
import spring.rest.Test;

@Controller
public class RestController {

	private static final Logger logger = LoggerFactory.getLogger(RestController.class);
	
	@GetMapping("rest")
	public String test() {
		WebClient webClient = WebClient.create();
		Mono<Test> mono = webClient.get()
				 .uri("http://localhost:8000/test/1/")
				 .accept(MediaType.APPLICATION_JSON)
				 .retrieve()
				 .bodyToMono(Test.class);
		
		Test test = mono.block();
		logger.debug(test.toString());
		return "test";
	}
	
}
