package spring.rest.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import spring.rest.service.ImageService;

@Controller
public class RestController {

	private static final Logger logger = LoggerFactory.getLogger(RestController.class);
	
	@Autowired
	ImageService imageService;
	
	@GetMapping("rest")
	public String test() {
		String uri = "http://localhost:8000/test/6/";
		
		try {
			imageService.restGet(uri);
			return "test";
		} catch (IOException e) {
			logger.debug("imageService failed");
			e.printStackTrace();
			return "index";
		}
	}
	
}
