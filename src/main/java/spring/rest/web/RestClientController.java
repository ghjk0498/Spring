package spring.rest.web;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import spring.rest.service.RestClientService;
import spring.rest.vo.RestVO;

@Controller
public class RestClientController {

	private static final Logger logger = LoggerFactory.getLogger(RestClientController.class);

	@Autowired
	RestClientService restClientService;

	@Value("${rest.server-url}")
	private String serverUrl;

	@GetMapping("/rest-client")
	public String getAllRestVO(Model model) {
		String uri = serverUrl;

		try {
			List<RestVO> restVOList = restClientService.getAllRestVO(uri);
			model.addAttribute("restVOList", restVOList);
			return "restClientPage";
		} catch (IOException e) {
			return "error";
		}
	}

	@GetMapping("/rest-client/{id}")
	public String getRestVOById(Model model, @PathVariable int id) {
		String uri = serverUrl + "/" + id;

		try {
			RestVO restVO = restClientService.getRestVOById(uri);
			model.addAttribute("restVOList", restVO);
			return "restClientPage";
		} catch (IOException e) {
			return "error";
		}
	}

	@PostMapping("/rest-client")
	public String postRestVO(@ModelAttribute RestVO restVO) {
		String uri = serverUrl;
		restClientService.postRestVO(uri, restVO);
		return "redirect:/rest-client";
	}

	@PutMapping("/rest-client/{id}")
	public String putRestVO(@ModelAttribute RestVO restVO, @PathVariable int id) {
		String uri = serverUrl + "/" + id;
		restClientService.putRestVO(uri, restVO);
		return "redirect:/rest-client";
	}

	@DeleteMapping("/rest-client/{id}")
	public String deleteRestVO(@PathVariable int id) {
		String uri = serverUrl + "/" + id;
		restClientService.deleteRestVO(uri);
		return "redirect:/rest-client";
	}

}
