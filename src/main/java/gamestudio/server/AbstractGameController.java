package gamestudio.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import gamestudio.service.CommentService;
import gamestudio.service.FavoriteService;
import gamestudio.service.RatingService;
import gamestudio.service.ScoreService;

public class AbstractGameController {
	@Autowired
	protected ScoreService scoreService;
	@Autowired
	protected RatingService ratingService;
	@Autowired
	protected UserController userController;
	@Autowired
	protected CommentService commentService;
	@Autowired
	protected FavoriteService favoriteService;
	
	
	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	protected String message;
	
	
	private void fillModel(Model model) {
	/*	model.addAttribute("puzzleController", this);
		model.addAttribute("rating", ratingService.getAverageRating("15 Puzzle"));*/
		model.addAttribute("score", scoreService.getTopScores("getGameName()"));
		model.addAttribute("comment", commentService.getComments("getGameName()"));
/*		if (userController.isLogged()) {
		model.addAttribute("favorite", favoriteService.isFavorite(userController.getLoggedPlayer().getLogin(), "15 Puzzle"));}
*/	}
	
	
	protected abstract String getGameName();
	
	
	public abstract String render();
	
}
