package gamestudio.service.impl;

import gamestudio.entity.Game;

public interface GameService {
	Game getGame(String ident) {
		
		List<Game> getGames(String ident);
	}

}
