package spring.rest.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import spring.rest.service.RestClientService;
import spring.rest.service.RestVO;

@Controller
public class RestClientController {

	private static final Logger logger = LoggerFactory.getLogger(RestClientController.class);
	
	@Autowired
	RestClientService restClientService;
	
	@GetMapping("getClient")
	public String getClient(Model model) {
		String uri = "http://localhost:8080/rest/1";
		
		try {
			RestVO restVO = restClientService.get(uri);
			model.addAttribute("restVO", restVO);
			logger.info(restVO.toString());
			return "test";
		} catch (IOException e) {
			logger.debug("imageService failed");
			e.printStackTrace();
			return "index";
		}
	}
	
	@PostMapping("postClient")
	public String postClient(@ModelAttribute RestVO restVO) {
		String uri = "http://localhost:8080/rest";
		restClientService.post(uri, restVO);
		return "test";
	}
	
	@GetMapping("test")
	public String test2() {
		return "test";
	}
	
}
