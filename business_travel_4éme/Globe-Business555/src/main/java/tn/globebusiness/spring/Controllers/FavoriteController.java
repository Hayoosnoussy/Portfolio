package tn.globebusiness.spring.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.globebusiness.spring.Entities.Event;
import tn.globebusiness.spring.Entities.Favorite;
import tn.globebusiness.spring.Entities.Travel;
import tn.globebusiness.spring.Repositories.CategoryRepository;
import tn.globebusiness.spring.Services.IEventService;
import tn.globebusiness.spring.Services.IFavoriteService;

@RestController
public class FavoriteController {
	
	@Autowired
	IFavoriteService favoriteService;

	@Autowired
	CategoryRepository categoryRepository;

	@PostMapping("AddFavorite/{eventId}")
	public void addFavorite(@RequestBody Favorite favorite, @PathVariable("eventId") int eventId) {
		favoriteService.addFavorite(favorite, eventId);
	}

	@GetMapping("ListFavoriteByEmployee")
	public List<Favorite> listFavorite() {
		return favoriteService.getFavoriteByEmployee();
	}

	

	@DeleteMapping("deleteFavorite/{favoriteId}")
	public boolean deleteFavorite(@PathVariable("favoriteId") Integer favoriteId) {
		favoriteService.deleteFavorite(favoriteId);
		return true;
	}

}
