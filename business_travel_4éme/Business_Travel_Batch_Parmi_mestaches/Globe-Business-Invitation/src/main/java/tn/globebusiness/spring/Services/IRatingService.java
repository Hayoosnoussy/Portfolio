package tn.globebusiness.spring.Services;

import java.util.List;

import tn.globebusiness.spring.Entities.Rating;

public interface IRatingService {
	
	public Rating addRate(Rating rate);
	public void deleteRate(int rateId);
	public List<Rating> listAllRatings();
	public Rating updateRate(Rating rate);
	public Rating listRate(int RateId);

}
