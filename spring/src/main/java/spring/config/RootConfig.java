package spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages= {"spring.login.service", "spring.rest.service"})
@Import({DBConfig.class})
public class RootConfig {

	
}
