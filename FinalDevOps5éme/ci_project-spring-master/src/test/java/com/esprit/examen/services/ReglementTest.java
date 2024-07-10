package com.esprit.examen.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.esprit.examen.entities.Reglement;
import com.esprit.examen.repositories.ReglementRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class ReglementTest {
	@Mock
	ReglementRepository reglementrepo;
	@InjectMocks
	ReglementServiceImpl reglementserv;

	@Test
	void Ajouter_Reglement() {
		Reglement reglement = new Reglement();
		reglement.setPayee(true);

		Mockito.when(reglementrepo.save(ArgumentMatchers.any(Reglement.class))).thenReturn(reglement);

		Reglement reglement_save = reglementserv.addReglement(reglement);
		
		log.info("get ==> " + String.valueOf(reglement));

		assertThat(reglement_save.getPayee()).isSameAs(reglement_save.getPayee());
		verify(reglementrepo).save(reglement_save);
	}

	@Test
	void RetrieveAll() {
		List<Reglement> reglements = new ArrayList<>();
		reglements.add(new Reglement());

		when(reglementrepo.findAll()).thenReturn(reglements);

		List<Reglement> expected = reglementserv.retrieveAllReglements();
		
		log.info("get ==> " + String.valueOf(reglements));
		
		assertEquals(expected, reglements);
		verify(reglementrepo).findAll();
	}
	
	
	@Test
	void getChiffreAffaireEntreDeuxDate() throws ParseException {
		
		Reglement reg1 = new Reglement();
		Reglement reg2 = new Reglement();
		
		float chifre1 = 18L; 
		
		String sDate1 = "31/12/2021"; 
		String sDate2= "31/12/2020"; 
		
		SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy");
		
		Date date1 = formatter1.parse(sDate1); 
		Date date2 = formatter2.parse(sDate2);
		
		reg1.setDateReglement(date1);
		reg2.setDateReglement(date2);
		
		when(reglementrepo.getChiffreAffaireEntreDeuxDate(reg1.getDateReglement(), reg2.getDateReglement())).thenReturn(chifre1);
		
		Float chifre2 = reglementserv.getChiffreAffaireEntreDeuxDate(date1, date2);
		
		assertEquals(chifre1, chifre2);
		
		PrintStream printr  = new PrintStream(System.out); 
		printr.println(chifre1);
		printr.println(chifre2);
		
		verify(reglementrepo).getChiffreAffaireEntreDeuxDate(date1, date2);
		
		
		
	}
	


}

