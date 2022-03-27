package spring.login;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private LoginService loginService;
	
	@GetMapping("/login")
	public String loginForm(LoginVO loginVO) {
		logger.info(loginVO.toString());
		return "loginForm";
	}
	
	@PostMapping("/login")
	public String login(@Valid LoginVO loginVO, Errors error) {
		if (error.hasErrors())
			return "loginForm";
		
		logger.info(loginVO.toString());
		
		String redirectURL = loginVO.getRedirectURL();
		if (loginService.login(loginVO)) {
			if (redirectURL.isEmpty()) {
				return "loginSuccessTest";
			} else {
				return redirectURL.substring(redirectURL.indexOf('/', 1) + 1);
			}
		}
		
		return "loginForm";
	}
	
	
	
	
}
