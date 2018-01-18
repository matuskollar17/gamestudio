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
import gamestudio.game.guessnumber.consoleui.ConsoleUI;
import gamestudio.service.CommentService;
import gamestudio.service.FavoriteService;
import gamestudio.service.RatingService;
import gamestudio.service.ScoreService;

@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class GuessNumberController {

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

	private ConsoleUI consoleUI;

	private String message;

	private String test = "sdhkgdfklhjgdfjkljhdfkl";

	public String getTest() {
		return test;
	}

	public double rating;

	public String getMessage() {
		return message = consoleUI.getMessage1();

	}

	public double getRating() {
		return rating = ratingService.getAverageRating("Guess Number");
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	private boolean marking;

	public boolean isMarking() {
		return marking;
	}

	private int startnum = 5;

	@RequestMapping("/guessnum")
	public String guessNum(Model model) {
		consoleUI = new ConsoleUI(10);

		fillMethod(model);
		//consoleUI.setRannum((int) (Math.random() * 10) + 1);
		message = "";

		return "/guessnum";

	}
	
	
	@RequestMapping("/guess_easy")
	public String guessNum_easy(Model model) {
		consoleUI = new ConsoleUI(10);

		fillMethod(model);
		//consoleUI.setRannum((int) (Math.random() * 10) + 1);
		message = "Guess the number from 1 to 10";

		return "/guessnum";

	}
	
	
	@RequestMapping("/guess_med")
	public String guessNum_med(Model model) {
		consoleUI = new ConsoleUI(100);

		fillMethod(model);
		//consoleUI.setRannum((int) (Math.random() * 10) + 1);
		message = "Guess the number from 1 to 100";

		return "/guessnum";

	}
	
	@RequestMapping("/guess_hard")
	public String guessNum_hard(Model model) {
		consoleUI = new ConsoleUI(1000);

		fillMethod(model);
		//consoleUI.setRannum((int) (Math.random() * 10) + 1);
		message = "Guess the number from 1 to 100";

		return "/guessnum";

	}
	

	private void fillMethod(Model model) {
		model.addAttribute("guessNumberController", this);
		model.addAttribute("comment", commentService.getComments("Guess Number"));
		model.addAttribute("scores", scoreService.getTopScores("Guess Number"));
		model.addAttribute("rating", ratingService.getAverageRating("Guess Number"));
		if (userController.isLogged()) {
		model.addAttribute("favorite", favoriteService.isFavorite(userController.getLoggedPlayer().getLogin(), "Guess Number"));}
	}

	@RequestMapping("/guessnumber_post")
	public String guessNumber_post(@RequestParam(value = "guessnumber", required = false) String guessnumber,
			Model model) {
		// public String guessNumber_post(@RequestParam(value = "guessnumber", required
		// = false) int guessnumber, Model model) {
		int hint = Integer.parseInt(guessnumber);

		consoleUI.process(hint);
		fillMethod(model);

		/*
		 * if(!"".equals(guessnumber)) { int hint = Integer.parseInt(guessnumber);
		 * 
		 * if (hint == startnum) { message = "Great, you have found a secret number"; }
		 * else if(hint > 5) { message = "Zadal si veeeeeelmi velke cislo";
		 * 
		 * } else if (hint < 5){ message = "zadal si nizke cislo"; }
		 */

		// commentService.addComment(new
		// Comment(userController.getLoggedPlayer().getLogin(), "Guess Number",
		// guessnumber));

		return "/guessnum";

	}
	
	
	@RequestMapping("/guessnum_set_rat")
	public String guessNumSetRat(@RequestParam(value = "value", required = false) String value, Model model) {
		int rating = Integer.parseInt(value);

		
		ratingService.setRating(new Rating(userController.getLoggedPlayer().getLogin(), "Guess Number", rating));
		fillMethod(model);
		return "/guessnum";
		

	}
	
	
	@RequestMapping("/guessnum_set_com")
	public String guessNumSetCom(@RequestParam(value = "content", required = false) String content, Model model) {
		if(!"".equals(content)) {
		commentService.addComment(new Comment(userController.getLoggedPlayer().getLogin(), "Guess Number", content));
		}
		
		fillMethod(model);
		
		return "/guessnum";
	}
	
	
	@RequestMapping("/guessnum_set_fav")
	public String guessNumSetFav(Model model)  {
	
			favoriteService.setFavorite(new Favorite(userController.getLoggedPlayer().getLogin(), "Guess Number"));
		
		
		fillMethod(model);
		
		return "/guessnum";
	}
	
	

}
