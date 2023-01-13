package spring.login.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.util.PatternMatchUtils;

public class LoginCheckFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(LoginCheckFilter.class);
	private static final String[] allowlist = {
												"/", "/resources/*", "/actuator/**",
												"/user/add", "/login", "/logout",
												"/rest", "/rest/*", "/rest-client", "/rest-client/*",
												"/ajax", "/ajax/*", "/ajax-client", "/ajax-client/*",
												};

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String requestURI = httpRequest.getRequestURI();
		logger.info(requestURI);
		try {
			HttpSession session = httpRequest.getSession(false);
			if (isLoginCheckPath(requestURI)) {
				if (session == null || session.getAttribute("User") == null) {
					httpResponse.sendRedirect("/login?redirectURL=" + requestURI);
					return;
				}
			}
			chain.doFilter(request, response);
		} catch (Exception e) {
			throw e;
		}
	}

	private boolean isLoginCheckPath(String requestURI) {
		return !PatternMatchUtils.simpleMatch(allowlist, requestURI);
	}

}
