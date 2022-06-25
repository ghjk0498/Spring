package spring.rest.mapper;

import org.apache.ibatis.annotations.Mapper;

import spring.rest.service.RestVO;

@Mapper
public interface RestMapper {
	public RestVO getRestVOById(int id);
	public void postRestVO(RestVO restVO);
}
