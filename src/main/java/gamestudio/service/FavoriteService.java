package gamestudio.service;

import java.util.List;

import gamestudio.entity.Comment;
import gamestudio.entity.Favorite;

public interface FavoriteService {

	
	void setFavorite(Favorite favorite);
	
	List<Favorite> getFavorite(String game);
	
	//void removeFavorite(String username, String game);
	
	boolean isFavorite(String username, String game);

	
	
	
}
