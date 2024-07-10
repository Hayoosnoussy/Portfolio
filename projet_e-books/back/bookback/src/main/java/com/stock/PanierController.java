package com.stock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import exception.ResourceNotFoundException;
import model.Panier;
import repository.PanierRepository;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api")
public class PanierController {
	@Autowired
	private PanierRepository panierRepository;
	@GetMapping("/paniers")
	  public List<Panier> getAllpanniers() {
	    System.out.println("Get all panier...");
	 
	    List<Panier> Paniers = new ArrayList<>();
	    panierRepository.findAll().forEach(Paniers::add);
	 
	    return Paniers;
	  }

	@GetMapping("/paniers/{id}")
	public ResponseEntity<Panier> getPanierById(@PathVariable(value = "id") Long PanierId)
			throws ResourceNotFoundException {
		Panier Panier = panierRepository.findById(PanierId)
				.orElseThrow(() -> new ResourceNotFoundException("Panier not found for this id :: " + PanierId));
		return ResponseEntity.ok().body(Panier);
	}
	
	@PostMapping("/paniers")
	public Panier createPanier(@Validated @RequestBody Panier Panier) {
		return panierRepository.save(Panier);
	}
	

	@DeleteMapping("/paniers/{id}")
	public Map<String, Boolean> deletePanier(@PathVariable(value = "id") Long PanierId)
			throws ResourceNotFoundException {
		Panier Panier = panierRepository.findById(PanierId)
				.orElseThrow(() -> new ResourceNotFoundException("Panier not found  id :: " + PanierId));

		panierRepository.delete(Panier);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/paniers/delete")
	  public ResponseEntity<String> deleteAllPaniers() {
	    System.out.println("Delete All Paniers...");
	 
	    panierRepository.deleteAll();
	 
	    return new ResponseEntity<>("All Paniers have been deleted!", HttpStatus.OK);
	  }
	 
	

	  @PutMapping("/paniers/{id}")
	  public ResponseEntity<Panier> updatePanier(@PathVariable("id") long id, @RequestBody Panier Panier) {
	    System.out.println("Update Panier with ID = " + id + "...");
	 
	    Optional<Panier> PanierInfo = panierRepository.findById(id);
	 
	    if (PanierInfo.isPresent()) {
	    	Panier panier = PanierInfo.get();
	           panier.setPrix(Panier.getPrix());
	        
	           
	           
	      return new ResponseEntity<>(panierRepository.save(Panier), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    } 
	  }
	  

}
