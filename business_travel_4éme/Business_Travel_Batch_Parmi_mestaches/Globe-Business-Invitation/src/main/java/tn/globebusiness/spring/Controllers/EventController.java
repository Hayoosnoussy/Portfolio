package tn.globebusiness.spring.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.globebusiness.spring.Entities.Category;
import tn.globebusiness.spring.Entities.Event;
import tn.globebusiness.spring.Repositories.CategoryRepository;
import tn.globebusiness.spring.Services.IEventService;

@RestController
public class EventController {
	
	@Autowired
	IEventService eventService;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@PostMapping("AddEvent")
	public void addEvent(@RequestBody Event event){
		eventService.addEvent(event);
	}
	
	@GetMapping("ListEvent/{eventId}")
	public Event listEvent(@PathVariable("eventId") Integer eventId) {
		return eventService.listEvent(eventId);
	}

	@PutMapping("updateEvent")
	public Event updateEvent(@RequestBody Event event) {
		return eventService.updateEvent(event);
	}
	
	@DeleteMapping("deleteEvent/{eventId}")
	public boolean deleteEvent(@PathVariable("eventId") Integer eventId) {
		eventService.deleteEvent(eventId);
		return true;
	}
	
	@GetMapping("ListAllEvents")
	public List<Event> listAllEvents(){
		return eventService.listAllEvents();
	}
	
	@GetMapping("ListEventByCategory/{category}")
	public List<Event> listEventByCategory(@PathVariable("category") String categoryName){
		Category category = categoryRepository.findByName(categoryName);
		if(category != null) return eventService.listEventByCategory(category);
		return null;
	}
	
	@GetMapping("ListEventByCategoryForCompany/{category}")
	public List<Event> ListEventByCategoryForCompany(@PathVariable("category") String categoryName){
		Category category = categoryRepository.findByName(categoryName);
		if(category != null) return eventService.listCategoryForCompany(category);
		return null;
	}
	
	@GetMapping("ListEventByCategoryForEmployee/{category}")
	public List<Event> listEventByCategoryForEmployee(@PathVariable("category") String categoryName){
		Category category = categoryRepository.findByName(categoryName);
		List<Event> eventsFinale = new ArrayList();
		if(category != null) {
			List<Event> events = eventService.listEventByCategory(category);
			events.forEach((event) -> {
				if(event.getCompany().getId() == (eventService.listCategoryForEmployee((long) 1)).getId()){
		        	 eventsFinale.add(event); 
		         }
	        });
		}
		return eventsFinale;
	}
}
