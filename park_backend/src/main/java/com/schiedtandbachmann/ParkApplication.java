package com.schiedtandbachmann;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EntityScan(basePackages= {"model","DBEntity"})


@EnableJpaRepositories("repository")
@ComponentScan("repository")
@ComponentScan("Services")
@ComponentScan("Config")

@ComponentScan("exception")
@EnableScheduling
@EnableCaching
public class ParkApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkApplication.class, args);
	}

	
	  public static final String LOGGER_NAME = "zrConnector";
	  
	  
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate(); 
	}


}
