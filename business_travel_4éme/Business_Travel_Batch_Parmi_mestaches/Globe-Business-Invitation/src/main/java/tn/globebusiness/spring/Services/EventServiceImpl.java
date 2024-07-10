package tn.globebusiness.spring.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.globebusiness.spring.Entities.Category;
import tn.globebusiness.spring.Entities.Company;
import tn.globebusiness.spring.Entities.Event;
import tn.globebusiness.spring.Repositories.EventRepository;

@Service
public class EventServiceImpl implements IEventService {
	
	@Autowired
	EventRepository eventRepository;

	@Override
	public Event addEvent(Event event) {
		List<Event> listOfEvents = listAllEvents();
		if(!listOfEvents.contains(event)) return eventRepository.save(event);
		return null;
	}

	@Override
	public Event updateEvent(Event event) {
		return eventRepository.save(event);
	}

	@Override
	public void deleteEvent(int eventId) {
		eventRepository.delete(eventRepository.findById(eventId).get());
	}

	@Override
	public List<Event> listAllEvents() {
		return (List<Event>) eventRepository.findAllEvents();
	}

	@Override
	public Event listEvent(int eventId) {
		return eventRepository.findById(eventId).orElse(new Event());
	}

	@Override
	public List<Event> listEventByCategory(Category category) {
		List<Event> event = eventRepository.findByCategory(category);
		if(event != null) return event;
		return null;
	}

	@Override
	public List<Event> listCategoryForCompany(Category category) {
		List<Event> event = eventRepository.findByCategoryForCompany(category);
		if(event != null) return event;
		return null;
	}

	@Override
	public Company listCategoryForEmployee(Long employeeId) {
		Company company = eventRepository.findByCategoryForEmployee(employeeId);
		if(company != null) return company;
		return null;
	}

}
