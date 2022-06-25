package spring.rest.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import spring.rest.service.RestServerService;
import spring.rest.service.RestVO;

@RestController
public class RestServerController {

	private static final Logger logger = LoggerFactory.getLogger(RestServerController.class);
	
	@Autowired
	RestServerService restServerService;
	
	@GetMapping("/rest/{id}")
	public RestVO rest(Model model, @PathVariable int id) {
		return restServerService.getRestVOById(id);
	}
	
}
