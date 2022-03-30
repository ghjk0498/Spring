package spring.rest.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

	@GetMapping("rest")
	public String test() {
		
		return "test";
	}
	
}
