package tn.globebusiness.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.scheduling.annotation.EnableScheduling;
import javax.mail.MessagingException;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tn.globebusiness.spring.Services.EmailSenderService;

@EnableBatchProcessing
@EnableTransactionManagement
@EnableJpaRepositories("tn.globebusiness.spring.Repositories")
@ComponentScan("tn.globebusiness.spring.Repositories")
@EntityScan(basePackages={"tn.globebusiness.spring.Entities"})
@ComponentScan("tn.globebusiness.spring.Services")
@ComponentScan("tn.globebusiness.spring.Controllers")
@ComponentScan("tn.globebusiness.spring.batch.mapper")
@ComponentScan("tn.globebusiness.spring.batch.processor")
@ComponentScan("tn.globebusiness.spring.batch.writer")
@ComponentScan("tn.globebusiness.spring.batch")
@ComponentScan("tn.globebusiness.spring.orders.api")
@ComponentScan("tn.globebusiness.spring.orders.email")
@ComponentScan("tn.globebusiness.spring.Configuration")
@ComponentScan("Exception")
@EnableSwagger2
@EnableScheduling
@SpringBootApplication
public class GlobeBusinessApplication {
	@Autowired
	private EmailSenderService emailSenderService;

	public static void main(String[] args) {
		SpringApplication.run(GlobeBusinessApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void sendMail() throws MessagingException {

		emailSenderService.sendMail("snowartsoondone@gmail.com",
				"Follow the link and subscribe to the application to make your business travel easier"
				+ "Cordialement",
				"Invitation"
				);
		
	}
}
