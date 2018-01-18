package gamestudio.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;

import gamestudio.entity.Comment;
import gamestudio.entity.Favorite;
import gamestudio.entity.Rating;
import gamestudio.entity.Score;
import gamestudio.game.puzzle.core.Field;
import gamestudio.service.CommentService;
import gamestudio.service.FavoriteService;
import gamestudio.service.RatingService;
import gamestudio.service.ScoreService;
@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class SliderController {
	@Autowired
	private ScoreService scoreService;
	@Autowired
	private RatingService ratingService;
	@Autowired
	private UserController userController;
	@Autowired
	private CommentService commentService;
	@Autowired
	private FavoriteService favoriteService;
	
	
	public double rating;
	
	public double getRating() {
		return rating = ratingService.getAverageRating("Slider");
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
	
	
	private Field field;
	
	private String message;
	
	private int scoretime;
	
	public String getMessage() {
		return message;
	}
	

	@RequestMapping("/slider")
	public String slider(@RequestParam(value = "tile", required = false) String tile, Model model) {
		process(tile, model);
		fillModel(model);
		return "slider";
	}
	
	
		
	
	private void fillModel(Model model) {
		model.addAttribute("sliderController", this);
		model.addAttribute("rating", ratingService.getAverageRating("Slider"));
		model.addAttribute("score", scoreService.getTopScores("Slider"));
		model.addAttribute("comment", commentService.getComments("Slider"));
		if (userController.isLogged()) {
		model.addAttribute("favorite", favoriteService.isFavorite(userController.getLoggedPlayer().getLogin(), "Slider"));}
	}
	
		
	/*@RequestMapping("/puzzle_set_rat")
	public String puzzleSetRat(@RequestParam(value = "value", required = false) String value, Model model) {
		int rating = Integer.parseInt(value);

				
		ratingService.setRating(new Rating(userController.getLoggedPlayer().getLogin(), "15 Puzzle", rating));
		fillModel(model);
		return "/puzzle3";
		
	}
	
	@RequestMapping("/puzzle_set_com")
	public String puzzleSetCom(@RequestParam(value = "content", required = false) String content, Model model) {
		if(!"".equals(content)) {
		commentService.addComment(new Comment(userController.getLoggedPlayer().getLogin(), "15 Puzzle", content));
		}
		
		fillModel(model);
	
		return "puzzle3";
	}
	*/
	
	public String render() {

		StringBuilder sb = new StringBuilder();

		sb.append("<table class='puzzle game'>\n");

		for (int row = 0; row < field.getRowCount(); row++) {
			sb.append("<tr class='puzzle puzzletable                                                                                                                                                                                              '>\n");
			for (int column = 0; column < field.getColumnCount(); column++) {

				int tile = field.getTile(row, column);
				sb.append("<td class='puzzle'>\n");
				if (!field.isSolved()) {
					sb.append(String.format("<a href='/slider?tile=%d'>\n", tile));
					if (tile != 0) {
					sb.append("<img class='slider' src=../gs/img/pexeso/image_part_00" + tile + ".jpg/>");
						//sb.append(tile);
					}
				} else {
					
					sb.append(tile);
					
					//sb.append("<p>SOLVED</p>");
				}

				sb.append("</a>");
				sb.append("</td>\n");
			}

		}
		
		sb.append("</tr>\n");
		sb.append("</table>\n");
		return sb.toString();
	
	
	}
	
	
	/*@RequestMapping("/puzzle_set_fav")
	public String puzzleSetFav(Model model)  {
	
			favoriteService.setFavorite(new Favorite(userController.getLoggedPlayer().getLogin(), "15 Puzzle"));
		
		
		fillModel(model);
		
		return "/puzzle3";
	}*/
	
	
	
	private void initField() {
		field = new Field(3,3);
		message = "";
	}



 private void process(String tile, Model model) {
		int tile2;
		try {
			tile2 = Integer.parseInt(tile);

			field.moveTile(tile2);
			
			if (field.isSolved()) {
				message = "SOLVED";
				int time = (int) field.getTcount();
				Score score = new Score();
		
				score.setGame("Slider");
				score.setUsername(userController.getLoggedPlayer().getLogin());
				
				score.setValue(time);
				
				scoreService.addScore(score);
			
	
			}

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			initField();
		}

		fillModel(model);
		
		
 }
}
