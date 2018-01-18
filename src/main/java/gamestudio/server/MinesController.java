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
import gamestudio.game.minesweeper.core.Clue;
import gamestudio.game.minesweeper.core.Field;
import gamestudio.game.minesweeper.core.GameState;
import gamestudio.game.minesweeper.core.Tile;
import gamestudio.game.minesweeper.core.TileState;
import gamestudio.service.CommentService;
import gamestudio.service.FavoriteService;
import gamestudio.service.RatingService;
import gamestudio.service.ScoreService;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class MinesController {
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
		return rating = ratingService.getAverageRating("Mines");
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	private boolean marking;

	public boolean isMarking() {
		return marking;
	}

	private String message;

	public String getMessage() {
		return message;
	}

	private Field field;

	private int level;

	private int scorecount;

	// ake url do browsera
	@RequestMapping("/mines_mark")
	public String minesMark(Model model) {
		marking = !marking;

		fillMethod(model);

		return "mines3";

	}

	@RequestMapping("/mines_set_rat")
	public String minesSetRat(@RequestParam(value = "value", required = false) String value, Model model) {
		int rating = Integer.parseInt(value);

		ratingService.setRating(new Rating(userController.getLoggedPlayer().getLogin(), "Mines", rating));
		fillMethod(model);
		return "/mines3";

	}

	/*
	 * @RequestMapping("/mines3") public String mines(@RequestParam(value = "row",
	 * required = false) String row,
	 * 
	 * @RequestParam(value = "column", required = false) String column, Model model)
	 * { field = new Field(3, 3, 1); try { if (marking)
	 * field.markTile(Integer.parseInt(row), Integer.parseInt(column)); else //
	 * if(open) field.openTile(Integer.parseInt(row), Integer.parseInt(column));
	 * if(field.getState() == GameState.FAILED) { message = "You Dumb!";
	 * 
	 * }
	 * 
	 * else if(field.getState() == GameState.SOLVED) { message =
	 * "Nice, you have solved the game !";
	 * 
	 * int time = (int) field.getTcount(); Score score = new Score();
	 * 
	 * 
	 * 
	 * 
	 * if(userController.isLogged()) {
	 * 
	 * 
	 * score.setGame("Mines");
	 * score.setUsername(userController.getLoggedPlayer().getLogin());
	 * 
	 * score.setValue(time + 1000);
	 * 
	 * scoreService.addScore(score); } else {
	 * 
	 * score.setGame("Mines"); score.setUsername("Guest");
	 * 
	 * score.setValue(time + 1000);
	 * 
	 * scoreService.addScore(score);
	 * 
	 * }
	 * 
	 * }
	 * 
	 * } catch (NumberFormatException e) { // TODO Auto-generated catch block //
	 * e.printStackTrace(); initField(); }
	 * 
	 * fillMethod(model); // ktore html sa zobrazi return "mines3"; }
	 */

	@RequestMapping("/mines3")
	public String mines(@RequestParam(value = "row", required = false) String row,
			@RequestParam(value = "column", required = false) String column, Model model) {
		// field = new Field(3,3,1);
		process(row, column);
		fillMethod(model);

		return "/mines3";
	}

	@RequestMapping("/mines3_easy")
	public String mines_easy(@RequestParam(value = "row", required = false) String row,
			@RequestParam(value = "column", required = false) String column, Model model) {
		// field = new Field(3,3,1);
		process(row, column);
		fillMethod(model);
		level = 1;
		return "/mines3";
	}

	@RequestMapping("/mines3_med")
	public String mines_med(@RequestParam(value = "row", required = false) String row,
			@RequestParam(value = "column", required = false) String column, Model model) {
		field = new Field(9, 9, 15);
		fillMethod(model);
		level = 2;

		return "/mines3";
	}

	@RequestMapping("/mines3_hard")
	public String mines_hard(@RequestParam(value = "row", required = false) String row,
			@RequestParam(value = "column", required = false) String column, Model model) {
		field = new Field(15, 15, 20);
		fillMethod(model);
		level = 3;

		return "/mines3";
	}

	private void process(String row, String column) {

		try {
			if (marking)
				field.markTile(Integer.parseInt(row), Integer.parseInt(column));
			else
				// if(open)
				field.openTile(Integer.parseInt(row), Integer.parseInt(column));
			if (field.getState() == GameState.FAILED) {
				message = "You Dumb!";

			}

			else if (field.getState() == GameState.SOLVED) {
				message = "Nice, you have solved the game !";
				scoreSave();
			}

		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			initField();

		}

	}

	private void scoreSave() {
		int time = (int) field.getTcount();
		Score score = new Score();

		if (userController.isLogged()) {

			score.setGame("Mines");
			score.setUsername(userController.getLoggedPlayer().getLogin());
			if (level == 1) {
				scorecount = time + 1000;
			}
			if (level == 2) {
				scorecount = time + 2000;
			}
			if (level == 3) {
				scorecount = time + 3000;
			}
			score.setValue(scorecount);
			scoreService.addScore(score);
		} else {

			score.setGame("Mines");
			score.setUsername("Guest");

			if (level == 1) {
				scorecount = time + 1000;
			}
			if (level == 2) {
				scorecount = time + 2000;
			}
			if (level == 3) {
				scorecount = time + 3000;
			}
			score.setValue(scorecount);
			scoreService.addScore(score);
		

		}

	}

	private void fillMethod(Model model) {
		model.addAttribute("minesController", this);
		model.addAttribute("scores", scoreService.getTopScores("Mines"));
		model.addAttribute("rating", ratingService.getAverageRating("Mines"));
		model.addAttribute("comment", commentService.getComments("Mines"));
		if (userController.isLogged()) {
			model.addAttribute("favorite",
					favoriteService.isFavorite(userController.getLoggedPlayer().getLogin(), "Mines"));
		}

	}

	@RequestMapping("/mines_set_com")
	public String minesSetCom(@RequestParam(value = "content", required = false) String content, Model model) {
		if (!"".equals(content)) {
			commentService.addComment(new Comment(userController.getLoggedPlayer().getLogin(), "Mines", content));
		}

		fillMethod(model);

		return "mines3";
	}

	public String render() {
		// return "<b>HELLO </b>";
		StringBuilder sb = new StringBuilder();

		sb.append("<table class='mineren game'>\n");

		for (int row = 0; row < field.getRowCount(); row++) {
			sb.append("<tr>\n");
			for (int column = 0; column < field.getColumnCount(); column++) {

				Tile tile = field.getTile(row, column);
				String image = null;

				switch (tile.getState()) {
				case CLOSED:
					image = "closed";
					break;
				case MARKED:
					image = "marked";
					break;

				case OPEN:
					if (tile instanceof Clue)
						image = "open" + ((Clue) tile).getValue();
					else
						image = "mine";
					break;
				}

				if (tile.getState().equals(TileState.OPEN) && (field.getState() == GameState.PLAYING)) {
					sb.append("<td>\n");
					// sb.append("<a href='/mines'>\n");
					// sb.append(String.format("<a href='/mines?row=%d&column=%d'>\n", row,
					// column));
					sb.append("<img src ='/images/mines/" + image + ".png'>\n");
					// sb.append("</a>\n");
					sb.append("</td>\n");
				} else {
					sb.append("<td>\n");
					// sb.append("<a href='/mines'>\n");
					sb.append(String.format("<a href='/mines3?row=%d&column=%d'>\n", row, column));
					sb.append("<img src ='/images/mines/" + image + ".png'>\n");
					sb.append("</a>\n");
					sb.append("</td>\n");
				}
			}

			sb.append("</tr>\n");

		}

		sb.append("</table>\n");

		return sb.toString();
	}

	/// spodmienkovat
	private void initField() {
		field = new Field(5, 5, 3);
		message = "";
	}

	@RequestMapping("/mines_set_fav")
	public String minesSetFav(Model model) {

		favoriteService.setFavorite(new Favorite(userController.getLoggedPlayer().getLogin(), "Mines"));

		fillMethod(model);

		return "/mines3";
	}

}
