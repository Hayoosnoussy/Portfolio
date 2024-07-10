package tn.globebusiness.spring.Services;

import java.util.List;

import tn.globebusiness.spring.Entities.Favorite;

public interface IFavoriteService {

	public Favorite addFavorite(Favorite favorite, int eventId);
	
	public void deleteFavorite(int favoriteId);
	
	public List<Favorite> getFavoriteByEmployee();
}
