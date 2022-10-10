package spring.rest.service;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.rest.mapper.RestMapper;

@Service
public class RestServerService {

	private static final Logger logger = LoggerFactory.getLogger(RestServerService.class);
	
	@Autowired
	ServletContext servletContext;
	@Autowired
	RestMapper restMapper;
	
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
