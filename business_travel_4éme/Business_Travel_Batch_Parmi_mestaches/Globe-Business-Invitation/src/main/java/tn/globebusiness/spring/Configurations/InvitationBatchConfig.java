package tn.globebusiness.spring.Configurations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import tn.globebusiness.spring.Entities.Invitation;
import tn.globebusiness.spring.batch.InvitationProcessor;
import tn.globebusiness.spring.batch.InvitationWriter;
import tn.globebusiness.spring.batch.mapper.OrderMapper;
import tn.globebusiness.spring.batch.processor.OrderItemProcessor;
import tn.globebusiness.spring.batch.writer.OrderWriter;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class InvitationBatchConfig {

	/*4. Création des variables de notre batch (nom fichier,
	 * nom job, nom step, nom lecteur*/
	private static final String FILE_NAME = "invitation.csv";
	private static final String JOB_NAME = "listInvitationsJob";
	private static final String STEP_NAME = "processingStep";
	private static final String READER_NAME = "invitationItemReader";

	/*5. Lister les champs que nous souhaitons parser dans le
	 * fichier excel*/
	private String names =" id,date,destinataire_email,sent_status ";

/*6. Définir la stratégie de délimitation des différents champs*/
	private String delimiter = ",";

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	

	
	/*
    // HOUNI  ----> 
	//7 Créer le bean associé au job et le lancer
	@Bean
	
	public Job listInvitationsJob(Step step1) {
		return jobBuilderFactory.get(JOB_NAME).start(step1).build();
		
		
		
	}

	//8 Créer le step associé au job et le lancer
	@Bean
	public Step invitationStep() {
		return stepBuilderFactory.get(STEP_NAME).<Invitation, Invitation>chunk(5).reader(invitationItemReader())
				.processor(invitationItemProcessor()).writer(invitationItemWriter()).build();
	}
	
	//9. étape 1 (ItemReader) Créer le reader pour récupérer les données depuis* le fichier csv*
	@Bean
	public ItemReader<Invitation> invitationItemReader() {
		FlatFileItemReader<Invitation> reader = new FlatFileItemReader<>();
		reader.setResource(new ClassPathResource(FILE_NAME));
		reader.setName(READER_NAME);
		reader.setLinesToSkip(1);
		//7. récupérer les données ligne par ligne du fichier excel 
		reader.setLineMapper(invitationLineMapper());
		return reader;

	}

	
	//10. récupérer les données ligne par ligne du fichier excel 

	@Bean
	public LineMapper<Invitation> invitationLineMapper() {

		final DefaultLineMapper<Invitation> defaultLineMapper = new DefaultLineMapper<>();
		final DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(delimiter);
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames(names.split(delimiter));
		defaultLineMapper.setLineTokenizer(lineTokenizer);
		defaultLineMapper.setFieldSetMapper(fieldSet -> {
			Invitation data = new Invitation();
			data.setId(fieldSet.readLong(0));
			
			try {
				data.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(fieldSet.readString(1)));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		   // data.setCompany(fieldSet.readLong(2)); 
			data.setDestinataireEmail(fieldSet.readString(2));
			data.setSentStatus(fieldSet.readBoolean(3));
                       
			return data;
		});
		return defaultLineMapper;
	}

	
	@Bean
	public ItemProcessor<Invitation, Invitation> invitationItemProcessor() {
		return new InvitationProcessor();
	}
	
	@Bean
	public ItemWriter<Invitation> invitationItemWriter() {
		return new InvitationWriter();
	}
	*/
	 
	// mena bdit nzid aal mailing b batch
	// mena bdit nzid aal mailing b batch
	// mena bdit nzid aal mailing b batch
	// mena bdit nzid aal mailing b batch
	
	
	 @Autowired
	    DataSource dataSource;


	    private final String JOB_NAME1 = "emailSenderJob";
	    private final String STEP_NAME1 = "emailSenderStep";

	    Random random = new Random();
	    int randomWithNextInt = random.nextInt();


	    @Bean(name = "emailSenderJob")
	    public Job emailSenderJob() {
	        return this.jobBuilderFactory.get(JOB_NAME1+randomWithNextInt)
	                .start(emailSenderStep())
	                .build();
	    }

	    @Bean
	   
	    public Step emailSenderStep() {
	        return this.stepBuilderFactory
	                .get(STEP_NAME1)
	                .<Invitation, Invitation>chunk(100)
	                .reader(activeOrderReader())
	                .processor(orderItemProcessor())
	                .writer(orderWriter())
	                .build();
	    }

	    @Bean
	    public ItemProcessor<Invitation, Invitation> orderItemProcessor() {
	        return new OrderItemProcessor();
	    }

	    @Bean
	    public ItemWriter<Invitation> orderWriter() {
	        return new OrderWriter();
	    }

	    @Bean
	    public ItemReader<Invitation> activeOrderReader() {
	        String sql = "SELECT * FROM invitation ";
	        return new JdbcCursorItemReaderBuilder<Invitation>()
	                .name("activeOrderReader")
	                .sql(sql)
	                .dataSource(dataSource)
	                .rowMapper(new OrderMapper())
	                .build();
	    }
	 
	    
	   
	
	
	
}
