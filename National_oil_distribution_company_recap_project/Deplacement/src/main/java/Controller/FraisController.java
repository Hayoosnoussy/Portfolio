package Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import DAO.FraisRepository;

import Exception.ResourceNotFoundException;
import Model.Frais;


@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api")
public class FraisController {

	@Autowired
	private FraisRepository fraisRepository;
	@GetMapping("/fraiss")
	  public List<Frais> getFrais() {
	    System.out.println("Les frais sont...");
	 
	    List<Frais> Fraiss = new ArrayList<>();
	    fraisRepository.findAll().forEach(Fraiss::add);
	 
	    return Fraiss;
	  }
	  
	


	@PostMapping("/fraiss")
	public Frais createFrais(@Valid @RequestBody Frais Frais) {
		return fraisRepository.save(Frais);
	}
	

	@DeleteMapping("/fraiss/{id}")
	public Map<String, Boolean> deleteFrais(@PathVariable(value = "id") Long FraisId)
			throws ResourceNotFoundException {
		Frais Frais = fraisRepository.findById(FraisId)
				.orElseThrow(() -> new ResourceNotFoundException("Frais not found  id :: " + FraisId));

		fraisRepository.delete(Frais);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
	  
	 
	  @DeleteMapping("/fraiss/delete")
	  public ResponseEntity<String> deleteAllFraiss() {
	    System.out.println("Delete All Fraiss...");
	 
	    fraisRepository.deleteAll();
	 
	    return new ResponseEntity<>("All Fraiss have been deleted!", HttpStatus.OK);
	  }
	 
	

	  @PutMapping("/fraiss/{id}")
	  public ResponseEntity<Frais> updateFrais(@PathVariable("id") long id, @RequestBody Frais Frais) {
	    System.out.println("Update Frais with ID = " + id + "...");
	 
	    Optional<Frais> FraisInfo = fraisRepository.findById(id);
	 
	    if (FraisInfo.isPresent()) {
	    	Frais frais = FraisInfo.get();
	           frais.setTotPetitDejouner(Frais.getTotPetitDejouner());
	           frais.setTotDinner(Frais.getTotDinner());
	           frais.setTotPrerep(Frais.getTotPrerep());
	           frais.setTotHebergement(Frais.getTotHebergement());
	           frais.setTauxKilometrique(Frais.getTauxKilometrique());
	           frais.setDivers(Frais.getDivers());
	           frais.setTotal(Frais.getTotal());
	           
	      return new ResponseEntity<>(fraisRepository.save(Frais), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	  }
}
