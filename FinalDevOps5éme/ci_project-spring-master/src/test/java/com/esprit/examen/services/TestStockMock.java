package com.esprit.examen.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.esprit.examen.entities.Stock;
import com.esprit.examen.repositories.StockRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TestStockMock {

	@Mock
	StockRepository or;
	@InjectMocks
	StockServiceImpl osI;
	
	
	Stock s = Stock.builder().libelleStock("livre").qte(300).qteMin(30).build();

	@Test
	public void Add() {
		Stock o_add = new Stock();
		o_add.setLibelleStock("yasmine");

		Mockito.when(or.save(ArgumentMatchers.any(Stock.class))).thenReturn(o_add);

		Stock o_added = osI.addStock(o_add);
		
		assertEquals(o_add.getLibelleStock(),o_added.getLibelleStock());

		verify(or).save(o_add);
	}

	@Test
	public void retrieveStock() {

		List<Stock> stocks = new ArrayList<>();
		stocks.add(new Stock());

		Mockito.when(or.findAll()).thenReturn(stocks);

		List<Stock> expected = osI.retrieveAllStocks();
		log.info("get ==> " + stocks.toString());
		assertEquals(expected, stocks);

		verify(or).findAll();
	}

	@Test
	public void retrieveStockStatus() {

		List<Stock> stocks = new ArrayList<>();
		Stock v = new Stock();
		v.setQte(150);
		v.setQteMin(20);
		v.setLibelleStock("livre2");
		stocks.add(v);
	 

		Mockito.when(or.retrieveStatusStock()).thenReturn(stocks);
		
		String expected = osI.retrieveStatusStock();
		log.info("get ==> " + stocks.toString());
		assertNotEquals(expected, String.valueOf(stocks));

		verify(or).retrieveStatusStock();
	}

	@Test
	public void retrieveStockById() {

		Mockito.when(or.findById(Mockito.anyLong())).thenReturn(Optional.of(s));
		Stock stock = osI.retrieveStock((long) 8);
		assertNotNull(stock);
		log.info("get ==> " + stock.toString());

		verify(or).findById(Mockito.anyLong());
	}


	@Test
	public void DeleteStock_ifFound() {
		Stock o_delete = new Stock();
		o_delete.setLibelleStock("yasmine delete");
		o_delete.setIdStock((long) 8);

		when(or.findById(o_delete.getIdStock())).thenReturn(Optional.of(o_delete));

		osI.deleteStock(o_delete.getIdStock());
		verify(or).deleteById(o_delete.getIdStock());
	}

	@Test
	public void DeleteException_ifnotFound() {
		try {
			Stock o_delete = new Stock();
			o_delete.setLibelleStock("yasmine delete");
			o_delete.setIdStock((long) 8);

			when(or.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(null));
			osI.deleteStock(o_delete.getIdStock());
		} catch (Exception e) {
			String expectedMessage = "entity with id";
			String actualMessage = e.getMessage();

			assertTrue(actualMessage.contains(expectedMessage));
		}
	}

	@Test
	public void EditStock_ifFound() {
		Stock o_edit = new Stock();

		o_edit.setIdStock((long) 8);
		o_edit.setLibelleStock("yasmine edit");

		Stock new_o_edit = new Stock();
		new_o_edit.setLibelleStock("new yasmine edit");

		when(or.findById(o_edit.getIdStock())).thenReturn(Optional.of(o_edit));
		o_edit = osI.updateStock(new_o_edit);

		verify(or).save(o_edit);
	}

	@Test
	public void EditException_ifnotFound() {
		try {
			Stock o_edit = new Stock();
			o_edit.setIdStock((long) 8);
			o_edit.setLibelleStock("yasmine edit");
			
			Stock new_o_edit = new Stock();
			new_o_edit.setIdStock(5L);
			new_o_edit.setLibelleStock("new yasmine edit");

			when(or.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(null));
			osI.updateStock(new_o_edit);

		} catch (Exception e) {
			String expectedMessage = "entity with id";
			String actualMessage = e.getMessage();

			assertTrue(actualMessage.contains(expectedMessage));
		}
	}
		
}
