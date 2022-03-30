package spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import spring.rest.config.RestConfig;

@Configuration
@ComponentScan(basePackages= {"spring.login.service"})
@Import({DBConfig.class, RestConfig.class})
public class RootConfig {

	
}
