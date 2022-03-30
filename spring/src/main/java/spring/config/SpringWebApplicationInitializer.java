package spring.config;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import spring.login.filter.LoginCheckFilter;

public class SpringWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

//	@Value("${spring.profiles.active}")
//	private String activeProfile;
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { RootConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
	
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		
		LoginCheckFilter loginCheckFilter = new LoginCheckFilter();
		
		return new Filter[] { characterEncodingFilter, loginCheckFilter };
	}
	
//	@Override
//	public void onStartup(ServletContext servletContext) throws ServletException {
//		super.onStartup(servletContext);
//		
//		servletContext.setInitParameter("spring.profiles.active", activeProfile);
//	}

}
