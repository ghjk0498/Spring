package spring.rest.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import spring.rest.mapper.RestMapper;
import spring.rest.vo.RestVO;

@Service
@RequiredArgsConstructor
public class RestServerService {

	private final RestMapper restMapper;

	public List<Map<String, RestVO>> getAllRestVO() {
		return restMapper.getAllRestVO();
	}

	public RestVO getRestVOById(int id) {
		return restMapper.getRestVOById(id);
	}

	public RestVO postRestVO(RestVO restVO) {
		restMapper.postRestVO(restVO);
		return restVO;
	}

	public RestVO putRestVO(RestVO restVO) {
		restMapper.putRestVO(restVO);
		return restVO;
	}

	public int deleteRestVO(int id) {
		restMapper.deleteRestVO(id);
		return id;
	}

}
