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

import DAO.TypeMissionRepository;
import Exception.ResourceNotFoundException;
import Model.TypeMission;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api")
public class TypeMissionController {

	 @Autowired
	 private TypeMissionRepository typemissionRepository;
	 
		@GetMapping("/typemissions")
		  public List<TypeMission> getAllTypeMissions() {
		    System.out.println("Get all TypeMissions...");
		 
		    List<TypeMission> TypeMissions = new ArrayList<>();
		    typemissionRepository.findAll().forEach(TypeMissions::add);
		 
		    return TypeMissions;
		  }
		@GetMapping("/typemissions/{id}")
		public ResponseEntity<TypeMission> getTypeMissionById(@PathVariable(value = "id") Long TypeMissionId)
				throws ResourceNotFoundException {
			TypeMission TypeMission = typemissionRepository.findById(TypeMissionId)
					.orElseThrow(() -> new ResourceNotFoundException("TypeMission not found for this id :: " + TypeMissionId));
			return ResponseEntity.ok().body(TypeMission);
		}
		@PostMapping("/typemissions")
		public TypeMission createTypeMission(@Valid @RequestBody TypeMission TypeMission) {
			return typemissionRepository.save(TypeMission);
		}
		

		@DeleteMapping("/typemissions/{id}")
		public Map<String, Boolean> deleteTypeMission(@PathVariable(value = "id") Long TypeMissionId)
				throws ResourceNotFoundException {
			TypeMission TypeMission = typemissionRepository.findById(TypeMissionId)
					.orElseThrow(() -> new ResourceNotFoundException("TypeMission not found  id :: " + TypeMissionId));

			typemissionRepository.delete(TypeMission);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
		}
		  
		 
		  @DeleteMapping("/typemissions/delete")
		  public ResponseEntity<String> deleteAllTypeMissions() {
		    System.out.println("Delete All TypeMissions...");
		 
		    typemissionRepository.deleteAll();
		 
		    return new ResponseEntity<>("All TypeMissions have been deleted!", HttpStatus.OK);
		  }
		 
		

		  @PutMapping("/typemissions/{id}")
		  public ResponseEntity<TypeMission> updateTypeMission(@PathVariable("id") long id, @RequestBody TypeMission TypeMission) {
		    System.out.println("Update TypeMission with ID = " + id + "...");
		 
		    Optional<TypeMission> TypeMissionInfo = typemissionRepository.findById(id);
		 
		    if (TypeMissionInfo.isPresent()) {
		    	TypeMission typemission = TypeMissionInfo.get();
		           typemission.setTyoem(TypeMission.getTyoem());
		          
		         
		           
		      return new ResponseEntity<>(typemissionRepository.save(TypeMission), HttpStatus.OK);
		    } else {
		      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
		  }
}

