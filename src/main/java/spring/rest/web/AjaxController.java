package spring.rest.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.extern.slf4j.Slf4j;
import spring.rest.service.RestServerService;
import spring.rest.vo.RestVO;

@Slf4j
@Controller
public class AjaxController {

	@Autowired
	RestServerService restServerService;

	@GetMapping("/ajax-client")
	public String get() {
		return "/ajax/ajaxClientPage";
	}

	@GetMapping("/ajax")
	public String getAll(Model model) {
		log.info(restServerService.getAllRestVO().toString());
		model.addAttribute("restVOList", restServerService.getAllRestVO());
		return "/ajax/data-table";
	}

	@GetMapping("/ajax/{id}")
	public String get(Model model, @PathVariable int id) {
		model.addAttribute("restVOList", restServerService.getRestVOById(id));
		return "/ajax/data-table";
	}

	@PostMapping("/ajax")
	public String post(@RequestBody RestVO restVO) {
		restServerService.postRestVO(restVO);
		return "/ajax/data-table";
	}

	@PutMapping("/ajax")
	public String put(@RequestBody RestVO restVO) {
		restServerService.putRestVO(restVO);
		return "/ajax/data-table";
	}

	@DeleteMapping("/ajax")
	public String delete(@RequestBody RestVO restVO) {
		restServerService.deleteRestVO(restVO.getId());
		return "/ajax/data-table";
	}

}
