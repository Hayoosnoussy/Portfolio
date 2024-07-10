/*package com.esprit.examen.services;

import static org.junit.Assert.assertNotEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.Facture;
import com.esprit.examen.repositories.FactureRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
public class FactureTest {

    @Autowired
    FactureRepository fp;
    @Test
    public void add() {
        Facture f=Facture.builder().idFacture((long)0)
                .montantRemise(1500)
                .montantFacture(10000).build();
        Facture f2=fp.save(f);
        assertNotEquals(f.getIdFacture(),f2.getIdFacture());
        fp.delete(f2);
    }


}*/