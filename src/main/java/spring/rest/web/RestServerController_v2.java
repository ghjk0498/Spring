package spring.rest.web;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import spring.rest.service.RestApiService;
import spring.rest.service.RestServerService;
import spring.rest.vo.EquipmentVo;
import spring.rest.vo.RestVO;

@RestController
@RequestMapping("/api/dvmg")
@Tag(name = "Equipment Management", description = "설비 관리 관련 API")
public class RestServerController_v2 {

	private static final String QUERY_FAILED = "query failed";

	@Autowired
	@Qualifier("equipmentService")
	private RestApiService<EquipmentVo> restApiService;

	@GetMapping("/equipment")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@Operation(summary = "설비 조회", description = "설비 데이터를 조회한다.")
	public ResponseEntity<Object> read() {
		List<EquipmentVo> result = this.restApiService.read(new EquipmentVo());
		return ResponseEntity.ok().body(result);
	}

	@PostMapping("/equipment")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@Operation(summary = "설비 입력", description = "설비 데이터를 입력한다.")
	public ResponseEntity<Object> create(@RequestBody EquipmentVo equipmentVo) {
		int result = this.restApiService.create(equipmentVo);
		if (result > 0) {
			try {
				return ResponseEntity.created(new URI("/equipment" + equipmentVo.getEqId())).body(equipmentVo);
			} catch (URISyntaxException e) {
				return ResponseEntity.internalServerError().body(new EquipmentVo());
			}
		} else {
			return ResponseEntity.badRequest().body(QUERY_FAILED);
		}
	}

	@GetMapping("/equipment/{eqId}")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@Operation(summary = "ID 설비 조회", description = "ID를 통해 설비 데이터를 조회한다.")
	public ResponseEntity<Object> readById(@PathVariable String eqId, @ModelAttribute EquipmentVo equipmentVo) {
		if (! eqId.equals(equipmentVo.getEqId())) {
			return ResponseEntity.badRequest().body("url로 지정한 Id와 Request Body로 보낸 Id 불일치");
		}

		equipmentVo.setEqId(eqId);
		List<EquipmentVo> result = this.restApiService.read(equipmentVo);
		return ResponseEntity.ok().body(result);
	}

	@PutMapping("/equipment/{eqId}")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@Operation(summary = "ID 설비 수정", description = "ID를 통해 설비 데이터를 수정한다.")
	public ResponseEntity<Object> updateById(@PathVariable String eqId, @RequestBody EquipmentVo equipmentVo) {
		if (! eqId.equals(equipmentVo.getEqId())) {
			return ResponseEntity.badRequest().body("url로 지정한 Id와 Request Body로 보낸 Id 불일치");
		}

		equipmentVo.setEqId(eqId);
		int result = this.restApiService.update(equipmentVo);
		if (result > 0) {
			try {
				return ResponseEntity.created(new URI("/equipment" + equipmentVo.getEqId())).body(equipmentVo);
			} catch (URISyntaxException e) {
				return ResponseEntity.internalServerError().body(new EquipmentVo());
			}
		} else {
			return ResponseEntity.badRequest().body(QUERY_FAILED);
		}
	}

	@DeleteMapping("/equipment/{eqId}")
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@Operation(summary = "ID 설비 삭제", description = "ID를 통해 설비 데이터를 삭제한다.")
	public ResponseEntity<Object> deleteById(@PathVariable String eqId, @RequestBody EquipmentVo equipmentVo) {
		if (! eqId.equals(equipmentVo.getEqId())) {
			return ResponseEntity.badRequest().body("url로 지정한 Id와 Request Body로 보낸 Id 불일치");
		}

		equipmentVo.setEqId(eqId);
		int result = this.restApiService.delete(equipmentVo);
		if (result > 0) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.badRequest().body(QUERY_FAILED);
		}
	}

}
