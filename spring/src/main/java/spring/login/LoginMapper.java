package spring.login;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {
	public LoginVO actionLogin(LoginVO loginVO);
}
