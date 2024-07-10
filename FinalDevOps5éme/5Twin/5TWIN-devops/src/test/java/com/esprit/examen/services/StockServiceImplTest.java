package com.esprit.examen.services;

import static org.junit.Assert.*;

import java.util.*;

import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.entities.Produit;
import com.esprit.examen.repositories.StockRepository;
import com.esprit.examen.services.IStockService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.esprit.examen.entities.Stock;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
@RunWith(SpringRunner.class)
//@SpringBootTest
public class StockServiceImplTest {

	//@Autowired
	//IStockService stockService;

	@Mock
	StockRepository stockRepository;

	@InjectMocks
	StockServiceImpl stockService ;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}


	@Test
	public void testAddStock(){
		CategorieProduit categorieProduit = new CategorieProduit();
		categorieProduit.setCodeCategorie("C1");

		Produit produit1 = new Produit();
		produit1.setCodeProduit("P1");
		produit1.setLibelleProduit("Produit 1");
		produit1.setDateCreation(new Date());
		produit1.setPrix(100);
		Produit produit2 = new Produit();
		produit2.setCodeProduit("P2");
		produit2.setLibelleProduit("Produit 2");
		produit2.setDateCreation(new Date());
		produit2.setPrix(200);

		Stock stock = new Stock();
		stock.setLibelleStock("stock1");
		stock.setQte(10);
		stock.setQteMin(5);
		stock.setProduits(new HashSet<>(Arrays.asList(produit1, produit2)));

		Mockito.when(stockRepository.save(stock)).thenReturn(stock);
			Stock s = stockService.addStock(stock);
			org.assertj.core.api.Assertions.assertThat(s.getLibelleStock()).isEqualTo("stock1");
			Mockito.verify(stockRepository).save(stock);
		}


	/*
	@Test
	public void testAddStock() {
	//	List<Stock> stocks = stockService.retrieveAllStocks();
	//	int expected=stocks.size();
		Stock s = new Stock("stock test",10,100);
		Stock savedStock= stockService.addStock(s);
		
	//	assertEquals(expected+1, stockService.retrieveAllStocks().size());
		assertNotNull(savedStock.getLibelleStock());
		stockService.deleteStock(savedStock.getIdStock());
	} 
	
	@Test
	public void testAddStockOptimized() {
		Stock s = new Stock("stock test",10,100);
		Stock savedStock= stockService.addStock(s);
		assertNotNull(savedStock.getIdStock());
		assertSame(10, savedStock.getQte());
		assertTrue(savedStock.getQteMin()>0);
		stockService.deleteStock(savedStock.getIdStock());
	} 
	
	@Test
	public void testDeleteStock() {
		Stock s = new Stock("stock test",30,60);
		Stock savedStock= stockService.addStock(s);
		stockService.deleteStock(savedStock.getIdStock());
		assertNull(stockService.retrieveStock(savedStock.getIdStock()));
	}
*/
}
