package gamestudio.server;

import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.beans.factory.annotation.Autowired;*/
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
public class PuzzleController {

	
	///nema tu byt fill model, a ani autowired trieda extends na nas abstract game controller, premenna message je uz  v rodicovi a moze byt zmazana
	
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
		return rating = ratingService.getAverageRating("15 Puzzle");
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
	

	@RequestMapping("/puzzle3")
	public String puzzle(@RequestParam(value = "tile", required = false) String tile, Model model) {
		process(tile, model);
		fillModel(model);
		return "puzzle3";
	}
	
	
	@RequestMapping("/puzzle_easy")
	public String puzzle_easy(@RequestParam(value = "tile", required = false) String tile, Model model) {

		field = new Field(4, 3);
		fillModel(model);
		return "puzzle3";
	}
	
	
	@RequestMapping("/puzzle_med")
	public String puzzle_med(@RequestParam(value = "tile", required = false) String tile, Model model) {

		field = new Field(6, 5);
		fillModel(model);
		return "puzzle3";
	}

	@RequestMapping("/puzzle_hard")
	public String puzzle_hard(@RequestParam(value = "tile", required = false) String tile, Model model) {

		field = new Field(8, 8);
		fillModel(model);
		return "puzzle3";
	}
	
	
	private void fillModel(Model model) {
		model.addAttribute("puzzleController", this);
		model.addAttribute("rating", ratingService.getAverageRating("15 Puzzle"));
		model.addAttribute("score", scoreService.getTopScores("Puzzle"));
		model.addAttribute("comment", commentService.getComments("15 Puzzle"));
		if (userController.isLogged()) {
		model.addAttribute("favorite", favoriteService.isFavorite(userController.getLoggedPlayer().getLogin(), "15 Puzzle"));}
	}
	
		
	@RequestMapping("/puzzle_set_rat")
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
	
	
	public String render() {

		StringBuilder sb = new StringBuilder();

		sb.append("<table class='puzzle game'>\n");

		for (int row = 0; row < field.getRowCount(); row++) {
			sb.append("<tr class='puzzle puzzletable                                                                                                                                                                                              '>\n");
			for (int column = 0; column < field.getColumnCount(); column++) {

				int tile = field.getTile(row, column);
				sb.append("<td class='puzzle'>\n");
				if (!field.isSolved()) {
					sb.append(String.format("<a href='/puzzle3?tile=%d'>\n", tile));
					if (tile != 0) {
					sb.append(tile);
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
	
	
	@RequestMapping("/puzzle_set_fav")
	public String puzzleSetFav(Model model)  {
	
			favoriteService.setFavorite(new Favorite(userController.getLoggedPlayer().getLogin(), "15 Puzzle"));
		
		
		fillModel(model);
		
		return "/puzzle3";
	}
	
	
	
	private void initField() {
		field = new Field(5,4);
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
		
				score.setGame("15 Puzzle");
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
		
		
 }}

