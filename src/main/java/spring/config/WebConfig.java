package spring.config;

import javax.servlet.Filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import spring.login.filter.LoginCheckFilter;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
				.addResourceLocations("/resources/");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
	}

//	@Profile("!test")
//	@Bean
//	public FilterRegistrationBean<Filter> loginFilter() {
//		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<Filter>(new LoginCheckFilter());
//		filterRegistrationBean.setOrder(1);
//		return filterRegistrationBean;
//	}
//
//	@Bean
//	public FilterRegistrationBean<Filter> hiddenHttpMethodFilter() {
//		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<Filter>(new HiddenHttpMethodFilter());
//		filterRegistrationBean.setOrder(10);
//		return filterRegistrationBean;
//	}


	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasenames("classpath:message/message-common");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

}
