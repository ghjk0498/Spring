package spring.rest.service;

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
	
	public RestVO getRestVOById(int id) {
		return restMapper.getRestVOById(id);
	}
	
}
