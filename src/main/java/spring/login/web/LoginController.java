package spring.login.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import spring.login.service.LoginService;
import spring.login.vo.LoginVO;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	@GetMapping("/login")
	public String loginForm(LoginVO loginVO) {
		return "loginForm";
	}

	@PostMapping("/login")
	public String login(@Valid LoginVO loginVO, Errors error) {
		if (error.hasErrors())
			return "loginForm";

		String redirectURL = loginVO.getRedirectURL();
		if (loginService.login(loginVO)) {
			if (redirectURL.isEmpty()) {
				return "redirect:";
			} else {
				return "redirect:" + redirectURL;
			}
		} else {
			error.reject("failed");
		}
		return "loginForm";
	}

}
