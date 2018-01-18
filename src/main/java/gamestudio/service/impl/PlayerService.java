package gamestudio.service.impl;

import gamestudio.entity.Player;

public interface PlayerService {

	void register(Player player);

	Player login(String login, String password);

}