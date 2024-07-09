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

import DAO.GradeRepository;

import Exception.ResourceNotFoundException;
import Model.Grade;


@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api")
public class GradeController {

	 @Autowired
	 private GradeRepository gradeRepository;
	 
		@GetMapping("/grades")
		  public List<Grade> getAllGrades() {
		    System.out.println("Get all Grades...");
		 
		    List<Grade> Grades = new ArrayList<>();
		    gradeRepository.findAll().forEach(Grades::add);
		 
		    return Grades;
		  }
		@GetMapping("/grades/{id}")
		public ResponseEntity<Grade> getGradeById(@PathVariable(value = "id") Long GradeId)
				throws ResourceNotFoundException {
			Grade Grade = gradeRepository.findById(GradeId)
					.orElseThrow(() -> new ResourceNotFoundException("Grade not found for this id :: " + GradeId));
			return ResponseEntity.ok().body(Grade);
		}
		@PostMapping("/grades")
		public Grade createGrade(@Valid @RequestBody Grade Grade) {
			return gradeRepository.save(Grade);
		}
		

		@DeleteMapping("/grades/{id}")
		public Map<String, Boolean> deleteGrade(@PathVariable(value = "id") Long GradeId)
				throws ResourceNotFoundException {
			Grade Grade = gradeRepository.findById(GradeId)
					.orElseThrow(() -> new ResourceNotFoundException("Grade not found  id :: " + GradeId));

			gradeRepository.delete(Grade);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
		}
		  
		 
		  @DeleteMapping("/grades/delete")
		  public ResponseEntity<String> deleteAllGrades() {
		    System.out.println("Delete All Grades...");
		 
		    gradeRepository.deleteAll();
		 
		    return new ResponseEntity<>("All Grades have been deleted!", HttpStatus.OK);
		  }
		 
		

		  @PutMapping("/grades/{id}")
		  public ResponseEntity<Grade> updateGrade(@PathVariable("id") long id, @RequestBody Grade Grade) {
		    System.out.println("Update Grade with ID = " + id + "...");
		 
		    Optional<Grade> GradeInfo = gradeRepository.findById(id);
		 
		    if (GradeInfo.isPresent()) {
		    	Grade grade = GradeInfo.get();
		           grade.setGrade(Grade.getGrade());
		          
		         
		           
		      return new ResponseEntity<>(gradeRepository.save(Grade), HttpStatus.OK);
		    } else {
		      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
		  }
}
