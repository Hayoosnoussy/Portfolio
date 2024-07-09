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

import DAO.VehiculeRepository;
import Exception.ResourceNotFoundException;
import Model.Vehicule;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api")
public class VehiculeController {

	 @Autowired
	 private VehiculeRepository vehiculeRepository;
	 
		@GetMapping("/vehicules")
		  public List<Vehicule> getAllVehicules() {
		    System.out.println("Get all Vehicules...");
		 
		    List<Vehicule> Vehicules = new ArrayList<>();
		    vehiculeRepository.findAll().forEach(Vehicules::add);
		 
		    return Vehicules;
		  }
		@GetMapping("/vehicules/{id}")
		public ResponseEntity<Vehicule> getVehiculeById(@PathVariable(value = "id") Long VehiculeId)
				throws ResourceNotFoundException {
			Vehicule Vehicule = vehiculeRepository.findById(VehiculeId)
					.orElseThrow(() -> new ResourceNotFoundException("Vehicule not found for this id :: " + VehiculeId));
			return ResponseEntity.ok().body(Vehicule);
		}
		@PostMapping("/vehicules")
		public Vehicule createVehicule(@Valid @RequestBody Vehicule Vehicule) {
			return vehiculeRepository.save(Vehicule);
		}
		

		@DeleteMapping("/vehicules/{id}")
		public Map<String, Boolean> deleteVehicule(@PathVariable(value = "id") Long VehiculeId)
				throws ResourceNotFoundException {
			Vehicule Vehicule = vehiculeRepository.findById(VehiculeId)
					.orElseThrow(() -> new ResourceNotFoundException("Vehicule not found  id :: " + VehiculeId));

			vehiculeRepository.delete(Vehicule);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
		}
		  
		 
		  @DeleteMapping("/vehicules/delete")
		  public ResponseEntity<String> deleteAllVehicules() {
		    System.out.println("Delete All Vehicules...");
		 
		    vehiculeRepository.deleteAll();
		 
		    return new ResponseEntity<>("All Vehicules have been deleted!", HttpStatus.OK);
		  }
		 
		

		  @PutMapping("/vehicules/{id}")
		  public ResponseEntity<Vehicule> updateVehicule(@PathVariable("id") long id, @RequestBody Vehicule Vehicule) {
		    System.out.println("Update Vehicule with ID = " + id + "...");
		 
		    Optional<Vehicule> VehiculeInfo = vehiculeRepository.findById(id);
		 
		    if (VehiculeInfo.isPresent()) {
		    	Vehicule vehicule = VehiculeInfo.get();
		           vehicule.setMarque(Vehicule.getMarque());
		           vehicule.setMatricule(Vehicule.getMatricule());
		         
		           
		      return new ResponseEntity<>(vehiculeRepository.save(Vehicule), HttpStatus.OK);
		    } else {
		      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
		  }
}

