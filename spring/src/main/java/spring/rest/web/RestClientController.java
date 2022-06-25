package spring.rest.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import spring.rest.service.RestClientService;
import spring.rest.service.RestVO;

@Controller
public class RestClientController {

	private static final Logger logger = LoggerFactory.getLogger(RestClientController.class);
	
	@Autowired
	RestClientService restClientService;
	
	@GetMapping("restClient")
	public String restClient(Model model) {
		String uri = "http://localhost:8080/rest/1/";
		
		try {
			RestVO restVO = restClientService.get(uri);
			model.addAttribute("restVO", restVO);
			logger.info("restVO", restVO.toString());
			return "test";
		} catch (IOException e) {
			logger.debug("imageService failed");
			e.printStackTrace();
			return "index";
		}
	}
	
	@GetMapping("test")
	public String test2() {
		return "test";
	}
	
}
