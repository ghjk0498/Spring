package spring.rest.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import spring.rest.service.RestVO;

@Mapper
public interface RestMapper {
	public List<Map<String, RestVO>> getAllRestVO();
	public RestVO getRestVOById(int id);
	public void postRestVO(RestVO restVO);
	public void putRestVO(RestVO restVO);
	public void deleteRestVO(int id);
}
