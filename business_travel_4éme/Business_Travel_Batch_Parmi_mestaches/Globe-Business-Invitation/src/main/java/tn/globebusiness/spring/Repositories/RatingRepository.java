package tn.globebusiness.spring.Repositories;

import org.springframework.data.repository.CrudRepository;

import tn.globebusiness.spring.Entities.Rating;

public interface RatingRepository extends CrudRepository<Rating, Integer> {

}
