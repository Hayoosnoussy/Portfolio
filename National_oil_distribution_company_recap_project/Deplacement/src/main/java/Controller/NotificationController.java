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

import DAO.NotificationRepository;
import Exception.ResourceNotFoundException;
import Model.Notification;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api")
public class NotificationController {

	 @Autowired
	 private NotificationRepository notificationRepository;
	 
		@GetMapping("/notifications")
		  public List<Notification> getAllNotifications() {
		    System.out.println("Get all Notifications...");
		 
		    List<Notification> Notifications = new ArrayList<>();
		    notificationRepository.findAll().forEach(Notifications::add);
		 
		    return Notifications;
		  }
		@GetMapping("/notifications/{id}")
		public ResponseEntity<Notification> getNotificationById(@PathVariable(value = "id") Long NotificationId)
				throws ResourceNotFoundException {
			Notification Notification = notificationRepository.findById(NotificationId)
					.orElseThrow(() -> new ResourceNotFoundException("Notification not found for this id :: " + NotificationId));
			return ResponseEntity.ok().body(Notification);
		}
		@PostMapping("/notifications")
		public Notification createNotification(@Valid @RequestBody Notification Notification) {
			return notificationRepository.save(Notification);
		}
		

		@DeleteMapping("/notifications/{id}")
		public Map<String, Boolean> deleteNotification(@PathVariable(value = "id") Long NotificationId)
				throws ResourceNotFoundException {
			Notification Notification = notificationRepository.findById(NotificationId)
					.orElseThrow(() -> new ResourceNotFoundException("Notification not found  id :: " + NotificationId));

			notificationRepository.delete(Notification);
			Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", Boolean.TRUE);
			return response;
		}
		  
		 
		  @DeleteMapping("/notifications/delete")
		  public ResponseEntity<String> deleteAllNotifications() {
		    System.out.println("Delete All Notifications...");
		 
		    notificationRepository.deleteAll();
		 
		    return new ResponseEntity<>("All Notifications have been deleted!", HttpStatus.OK);
		  }
		 
		

		  @PutMapping("/notifications/{id}")
		  public ResponseEntity<Notification> updateNotification(@PathVariable("id") long id, @RequestBody Notification Notification) {
		    System.out.println("Update Notification with ID = " + id + "...");
		 
		    Optional<Notification> NotificationInfo = notificationRepository.findById(id);
		 
		    if (NotificationInfo.isPresent()) {
		    	Notification notification = NotificationInfo.get();
		           notification.setNotification(Notification.getNotification());
		          
		         
		           
		      return new ResponseEntity<>(notificationRepository.save(Notification), HttpStatus.OK);
		    } else {
		      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		    }
		  }
}
