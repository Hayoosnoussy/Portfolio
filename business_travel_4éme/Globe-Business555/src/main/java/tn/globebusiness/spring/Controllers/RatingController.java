package tn.globebusiness.spring.Controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.globebusiness.spring.Entities.Rating;
import tn.globebusiness.spring.Services.IRatingService;

@RestController
public class RatingController {

	@Autowired
	IRatingService ratingService;

	@PostMapping("AddRating/{eventId}")
	public void addRating(@RequestBody Rating rating, @PathVariable("eventId") int eventId) {
		System.out.println(eventId);
		ratingService.addRating(rating, eventId);
	}

	@PutMapping("updateRating/{rateId}")
	public Rating updateRating(@PathVariable("rateId") int rateId, @RequestBody Rating rating) {
		return ratingService.updateRating(rateId, rating);
	}

	@DeleteMapping("deleteRating/{ratingId}")
	public boolean deleteRating(@PathVariable("ratingId") Integer ratingId) {
		ratingService.deleteRating(ratingId);
		return true;
	}

	@GetMapping("ListAllRatings")
	public List<Rating> listAllRatings() {
		return ratingService.listAllRatings();
	}
	
	@GetMapping("ListRating/{eventId}")
	public Rating listRating(@PathVariable("eventId") int eventId) {
		return ratingService.listRating(eventId);
	}

}
