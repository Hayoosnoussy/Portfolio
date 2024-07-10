package tn.globebusiness.spring.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.globebusiness.spring.Entities.Employee;
import tn.globebusiness.spring.Entities.Event;
import tn.globebusiness.spring.Entities.Favorite;
import tn.globebusiness.spring.Entities.Rating;
import tn.globebusiness.spring.Repositories.EmployeeRepository;
import tn.globebusiness.spring.Repositories.EventRepository;
import tn.globebusiness.spring.Repositories.FavoriteRepository;

@Service
public class FavoriteServiceImpl implements IFavoriteService {
	
	@Autowired
	FavoriteRepository favoriteRepository;
	
	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public Favorite addFavorite(Favorite favorite, int eventId) {
		Event event = eventRepository.findById(eventId).get();
		Employee employee = employeeRepository.findById((long) 1).get();
		favorite.setEvent(event);
		favorite.setEmployee(employee);
		return favoriteRepository.save(favorite);
	}

	@Override
	public void deleteFavorite(int favoriteId) {
		favoriteRepository.delete(favoriteRepository.findById(favoriteId).get());
	}

	@Override
	public List<Favorite> getFavoriteByEmployee() {
		return favoriteRepository.getFavoriteByEmployee();
		
	}

}
