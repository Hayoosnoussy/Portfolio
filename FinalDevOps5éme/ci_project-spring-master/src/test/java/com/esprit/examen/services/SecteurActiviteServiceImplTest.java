package com.esprit.examen.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.SecteurActivite;
import com.esprit.examen.repositories.SecteurActiviteRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SecteurActiviteServiceImplTest {
	
	@Autowired
	SecteurActiviteRepository secteurrepo;
	//TRADITIONELLE
	
	@Test
	public void addsa() {
				SecteurActivite sa = SecteurActivite.builder()
				.codeSecteurActivite("filler code")
				.libelleSecteurActivite("ziedzied")
				.build();
				SecteurActivite sar = secteurrepo.save(sa);
		
		assertEquals(sa.getIdSecteurActivite(), sa.getIdSecteurActivite() );
	}
}

