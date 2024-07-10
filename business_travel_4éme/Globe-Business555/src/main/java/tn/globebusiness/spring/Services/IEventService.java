package tn.globebusiness.spring.Services;

import java.util.List;

import tn.globebusiness.spring.Entities.Category;
import tn.globebusiness.spring.Entities.Company;
import tn.globebusiness.spring.Entities.Event;

public interface IEventService {

	public Event addEvent(Event event, int categoryId);

	public Event updateEvent(int eventId, Event event);

	public void deleteEvent(int eventId);

	public Event listEvent(int eventId);

	public List<Event> listEventByCategory(Category category);

	List<Event> listCategoryForCompany(Category category);

	Company listCategoryForEmployee(Long employeeId);

	public List<Event> listAllEvents();

}
