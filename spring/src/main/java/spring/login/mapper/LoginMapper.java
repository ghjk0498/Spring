package spring.login.mapper;

import org.apache.ibatis.annotations.Mapper;

import spring.login.service.LoginVO;

@Mapper
public interface LoginMapper {
	public LoginVO actionLogin(LoginVO loginVO);
}
