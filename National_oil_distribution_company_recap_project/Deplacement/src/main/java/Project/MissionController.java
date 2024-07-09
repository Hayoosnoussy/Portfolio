package Project;

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

import DAO.MissionRepository;
import Exception.ResourceNotFoundException;
import Model.Mission;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api")

public class MissionController {

	 @Autowired
	 private MissionRepository missionRepository;
	 
		@GetMapping("/missions")
		  public List<Mission> getAllMissions() {
		    System.out.println("Get all Missions...");
		 
		    List<Mission> Missions = new ArrayList<>();
		    missionRepository.findAll().forEach(Missions::add);
		 
		    return Missions;
		  }
		@GetMapping("/missions/{id}")
		public ResponseEntity<Mission> getMissionById(@PathVariable(value = "id") Long MissionId)
				throws ResourceNotFoundException {
			Mission Mission = missionRepository.findById(MissionId)
					.orElseThrow(() -> new ResourceNotFoundException("Mission not found for this id :: " + MissionId));
			return ResponseEntity.ok().body(Mission);
		}
		@PostMapping("/missions")
		public Mission createMission(@Valid @RequestBody Mission Mission) {
			return missionRepository.save(Mission);
		}
		

		@DeleteMapping("/missions/{id}")
		public Map<String, Boolean> deleteMission(@PathVariable(value = "id") Long MissionId)
				throws ResourceNotFoundException {
			Mission Mission = missionRepository.findById(MissionId)
					.orElseThrow(() -> new ResourceNotFoundException("Mission not found  id :: " + MissionId));

			missionRepository.delete(Mission);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
		}
		  
		 
		  @DeleteMapping("/missions/delete")
		  public ResponseEntity<String> deleteAllMissions() {
		    System.out.println("Delete All Missions...");
		 
		    missionRepository.deleteAll();
		 
		    return new ResponseEntity<>("All Missions have been deleted!", HttpStatus.OK);
		  }
		 
		

		  @PutMapping("/missions/{id}")
		  public ResponseEntity<Mission> updateMission(@PathVariable("id") long id, @RequestBody Mission Mission) {
		    System.out.println("Update Mission with ID = " + id + "...");
		 
		    Optional<Mission> MissionInfo = missionRepository.findById(id);
		 
		    if (MissionInfo.isPresent()) {
		    	Mission mission = MissionInfo.get();
		           mission.setTypem(Mission.getTypem());
		           mission.setDatedep(Mission.getDatedep());
		           mission.setDateret(Mission.getDateret());
		           mission.setFrais(Mission.getFrais());
		           mission.setKilometrage(Mission.getKilometrage());
		           mission.setIterative(Mission.getIterative());
		           
		      return new ResponseEntity<>(missionRepository.save(Mission), HttpStatus.OK);
		    } else {
		      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
		  }
}