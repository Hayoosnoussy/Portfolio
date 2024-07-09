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

import DAO.ServiceRepository;
import Exception.ResourceNotFoundException;
import Model.Service;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ServiceController {

	 @Autowired
	 private ServiceRepository serviceRepository;
	 
		@GetMapping("/services")
		  public List<Service> getAllServices() {
		    System.out.println("Get all Services...");
		 
		    List<Service> Services = new ArrayList<>();
		    serviceRepository.findAll().forEach(Services::add);
		 
		    return Services;
		  }
		@GetMapping("/services/{id}")
		public ResponseEntity<Service> getServiceById(@PathVariable(value = "id") Long ServiceId)
				throws ResourceNotFoundException {
			Service Service = serviceRepository.findById(ServiceId)
					.orElseThrow(() -> new ResourceNotFoundException("Service not found for this id :: " + ServiceId));
			return ResponseEntity.ok().body(Service);
		}
		@PostMapping("/services")
		public Service createService(@Valid @RequestBody Service Service) {
			return serviceRepository.save(Service);
		}
		

		@DeleteMapping("/services/{id}")
		public Map<String, Boolean> deleteService(@PathVariable(value = "id") Long ServiceId)
				throws ResourceNotFoundException {
			Service Service = serviceRepository.findById(ServiceId)
					.orElseThrow(() -> new ResourceNotFoundException("Service not found  id :: " + ServiceId));

			serviceRepository.delete(Service);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
		}
		  
		 
		  @DeleteMapping("/services/delete")
		  public ResponseEntity<String> deleteAllServices() {
		    System.out.println("Delete All Services...");
		 
		    serviceRepository.deleteAll();
		 
		    return new ResponseEntity<>("All Services have been deleted!", HttpStatus.OK);
		  }
		 
		

		  @PutMapping("/services/{id}")
		  public ResponseEntity<Service> updateService(@PathVariable("id") long id, @RequestBody Service Service) {
		    System.out.println("Update Service with ID = " + id + "...");
		 
		    Optional<Service> ServiceInfo = serviceRepository.findById(id);
		 
		    if (ServiceInfo.isPresent()) {
		    	Service service = ServiceInfo.get();
		           service.setServUser(Service.getServUser());
		          
		         
		           
		      return new ResponseEntity<>(serviceRepository.save(Service), HttpStatus.OK);
		    } else {
		      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
		  }
}
