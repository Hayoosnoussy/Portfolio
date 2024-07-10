package tn.globebusiness.spring.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import tn.globebusiness.spring.Entities.Rating;
import tn.globebusiness.spring.Repositories.RatingRepository;

public class RatingServiceImpl implements IRatingService {
	
	@Autowired
	RatingRepository rateRepository;

	@Override
	public Rating addRate(Rating rate) {
		List<Rating> listOfRatings = listAllRatings();
		if(!listOfRatings.contains(rate)) return rateRepository.save(rate);
		return null;
	}

	@Override
	public Rating updateRate(Rating rate) {
		return rateRepository.save(rate);
	}

	@Override
	public void deleteRate(int rateId) {
		rateRepository.delete(rateRepository.findById(rateId).get());
	}

	@Override
	public List<Rating> listAllRatings() {
		return (List<Rating>) rateRepository.findAll();
	}

	@Override
	public Rating listRate(int rateId) {
		return rateRepository.findById(rateId).orElse(new Rating());
	}

	/*@Override
	public Event listEventByCategory(String category) {
		Event event = eventRepository.findByCategory(category);
		if(event != null) return event;
		return null;
	}*/

}
