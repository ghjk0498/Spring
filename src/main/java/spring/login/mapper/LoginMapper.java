package spring.login.mapper;

import org.apache.ibatis.annotations.Mapper;

import spring.login.vo.LoginVO;

@Mapper
public interface LoginMapper {
	public LoginVO actionLogin(LoginVO loginVO);
}
