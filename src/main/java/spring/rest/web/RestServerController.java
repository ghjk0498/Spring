package spring.rest.web;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import spring.rest.service.RestServerService;
import spring.rest.vo.RestVO;

@Tag(name="Rest Example")
@RequiredArgsConstructor
@RestController
public class RestServerController {

	private final RestServerService restServerService;

	@Tag(name="전체 조회")
	@Operation(summary="테이블 전체 조회", description="select * from rest;")
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
