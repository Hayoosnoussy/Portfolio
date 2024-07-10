package tn.globebusiness.spring.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.globebusiness.spring.Entities.Category;
import tn.globebusiness.spring.Entities.Company;
import tn.globebusiness.spring.Entities.Event;
import tn.globebusiness.spring.Repositories.CategoryRepository;
import tn.globebusiness.spring.Repositories.CompanyRepository;
import tn.globebusiness.spring.Repositories.EventRepository;

@Service
public class EventServiceImpl implements IEventService {

	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	CompanyRepository companyRepository;

	@Override
	public Event addEvent(Event event, int categoryId) {
		List<Event> listOfEvents = listAllEvents();
		if (!listOfEvents.contains(event)){
			Category category = categoryRepository.findById(categoryId).orElse(new Category());
			Company company = companyRepository.findById((long) 1).orElse(new Company());
			event.setCategory(category);
			event.setCompany(company);
			return eventRepository.save(event);
		}
		return null;
	}

	@Override
	public Event updateEvent(int eventId, Event event) {
		Event oldEvent = eventRepository.findById(eventId).orElse(new Event());
		oldEvent.setCategory(event.getCategory());
		oldEvent.setDescription(event.getDescription());
		oldEvent.setDuration(event.getDuration());
		oldEvent.setLocation(event.getLocation());
		oldEvent.setStartDate(event.getStartDate());
		oldEvent.setTitle(event.getTitle());
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
		if (event != null)
			return event;
		return null;
	}

	@Override
	public List<Event> listCategoryForCompany(Category category) {
		List<Event> event = eventRepository.findByCategoryForCompany(category);
		if (event != null)
			return event;
		return null;
	}

	@Override
	public Company listCategoryForEmployee(Long employeeId) {
		Company company = eventRepository.findByCategoryForEmployee(employeeId);
		if (company != null)
			return company;
		return null;
	}

}
