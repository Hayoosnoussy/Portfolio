package Project;


import java.text.ParseException;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import org.springframework.context.annotation.ComponentScan;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;




@SpringBootApplication

@EnableJpaRepositories("DAO")
@ComponentScan("DAO")
@EntityScan(basePackages={"Model"})
@ComponentScan("Controller")



public class DeplacementApplication  {

	public static void main(String[] args) throws ParseException {
	SpringApplication.run(DeplacementApplication.class, args);

		
		
	
	
	}

	
		
	

}
