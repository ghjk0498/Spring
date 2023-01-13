package spring.auto.rest.web;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import spring.auto.rest.dto.AutoRestDto;
import spring.auto.rest.service.AutoRestServerService;

@Slf4j
@RestController
@RequestMapping("/auto")
@RequiredArgsConstructor
public class AutoRestController {

	private final AutoRestServerService autoRestServerService;

	@GetMapping("/rest")
	public ResponseEntity<Object> getAllByTableName(@Valid @RequestBody AutoRestDto autoRestDto) {
		ResponseEntity<Object> responseEntity = null;
		List<Map<String, Object>> result = null;
		try {
			result = autoRestServerService.getAllByTableName(autoRestDto.getSchemaName(), autoRestDto.getTableName(), autoRestDto.getColumnList());
			responseEntity = ResponseEntity.ok().body(result);
		} catch (NoSuchElementException noSuchElementException) {
			responseEntity = ResponseEntity.status(HttpStatus.FORBIDDEN).body(result);
			log.error(noSuchElementException.getMessage());
		}
		return responseEntity;
	}

	@GetMapping("/rest/columns")
	public ResponseEntity<Object> getColumnsByTableName(@Valid @RequestBody AutoRestDto autoRestDto) {
		ResponseEntity<Object> responseEntity = null;
		List<String> result = null;
		try {
			result = autoRestServerService.getColumnsByTableName(autoRestDto.getSchemaName(), autoRestDto.getTableName());
			responseEntity = ResponseEntity.ok().body(result);
		} catch (NoSuchElementException noSuchElementException) {
			responseEntity = ResponseEntity.status(HttpStatus.FORBIDDEN).body(result);
			log.error(noSuchElementException.getMessage());
		}
		return responseEntity;
	}

}
