package spring.login.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import spring.login.mapper.LoginMapper;

@Service
public class LoginService {

	@Autowired
	private LoginMapper loginMapper;
	
	public boolean login(LoginVO loginVO) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		LoginVO afterLoginVO = loginMapper.actionLogin(loginVO);
		SessionVO sessionVO = new SessionVO(loginVO.getEmail(), loginVO.getPassword());
		
		if (afterLoginVO != null) {
			HttpSession session = attr.getRequest().getSession();
			session.setAttribute("User", sessionVO);
			return true;
		}
		
		return false;
	}
	
}
