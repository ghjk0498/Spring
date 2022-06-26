package spring.rest.web;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import spring.rest.service.RestServerService;
import spring.rest.service.RestVO;

@ResponseBody
@RestController
public class RestServerController {

	private static final Logger logger = LoggerFactory.getLogger(RestServerController.class);
	
	@Autowired
	RestServerService restServerService;
	
	@GetMapping("/rest")
	public List<Map<String, RestVO>> getAll(Model model) {
		return restServerService.getAllRestVO();
	}
	
	@GetMapping("/rest/{id}")
	public RestVO get(Model model, @PathVariable int id) {
		return restServerService.getRestVOById(id);
	}
	
	@PostMapping("/rest")
	public RestVO post(@RequestBody RestVO restVO) {
		return restServerService.postRestVO(restVO);
	}
	
	@PutMapping("/rest/{id}")
	public RestVO put(@RequestBody RestVO restVO, @PathVariable int id) {
		return restServerService.putRestVO(restVO);
	}
	
	@DeleteMapping("/rest/{id}")
	public String delete(@PathVariable int id) {
		restServerService.deleteRestVO(id);
		return "";
	}
}
