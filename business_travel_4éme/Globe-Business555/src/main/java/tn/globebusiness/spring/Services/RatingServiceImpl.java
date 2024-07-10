package tn.globebusiness.spring.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.globebusiness.spring.Entities.Employee;
import tn.globebusiness.spring.Entities.Event;
import tn.globebusiness.spring.Entities.Rating;
import tn.globebusiness.spring.Repositories.EmployeeRepository;
import tn.globebusiness.spring.Repositories.EventRepository;
import tn.globebusiness.spring.Repositories.RatingRepository;

@Service
public class RatingServiceImpl implements IRatingService {

	@Autowired
	RatingRepository ratingRepository;
	
	@Autowired
	EventRepository eventRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public Rating addRating(Rating rate, int eventId) {
		
		
			rate.setEvent(eventRepository.findById(eventId).get());
			rate.setEmployee(employeeRepository.findById((long) 1).orElse(new Employee()));
			return ratingRepository.save(rate);
		
	}

	@Override
	public Rating updateRating(int ratingId, Rating rate) {
		
		Rating oldRating = ratingRepository.findById(ratingId).get();
		oldRating.setRateId(ratingId);
		oldRating.setComment(rate.getComment());
		oldRating.setDate(rate.getDate());
		oldRating.setNbOfStars(rate.getNbOfStars());
		return ratingRepository.save(rate);
	}

	@Override
	public void deleteRating(int rateId) {
		ratingRepository.delete(ratingRepository.findById(rateId).get());
	}

	@Override
	public List<Rating> listAllRatings() {
		return (List<Rating>) ratingRepository.findAll();
	}

	@Override
	public Rating listRating(int eventId) {
		return ratingRepository.listEmployeeRatingForEvent(eventId);
	}

	/*
	 * @Override public Event listEventByCategory(String category) { Event event
	 * = eventRepository.findByCategory(category); if(event != null) return
	 * event; return null; }
	 */

}
