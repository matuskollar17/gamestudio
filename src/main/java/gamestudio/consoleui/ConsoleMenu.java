package gamestudio.consoleui;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

import gamestudio.entity.Comment;
import gamestudio.service.CommentService;
import gamestudio.service.FavoriteService;
import gamestudio.service.RatingService;
import gamestudio.service.ScoreService;

public class ConsoleMenu {
	private ConsoleGameUI[] games;

	
	@Autowired
	private ScoreService scoreService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private RatingService ratingService;
	
	@Autowired
	private FavoriteService favoriteService;
	
	private Scanner scanner = new Scanner(System.in);

	public ConsoleMenu(ConsoleGameUI[] games) {
		this.games = games;
	}

	public void show() {
		while (true) {
		//scoreService.addScore(new Score("Matus", "Mines", 1500));
		//scoreService.addScore(new Score("Juraj", "Mines", 0));
		//scoreService.addScore(new Score("Jakub", "Mines", 85));
		//scoreService.addScore(new Score("Martin", "Mines", 102));
		//scoreService.addScore(new Score("Juraj", "Puzzle", 43));
				
		//System.out.println(scoreService.getTopScores("Puzzle"));
		//System.out.println(scoreService.getTopScores("Mines"));
		
		//commentService.addComment(new Comment("Ferko", "Mines", "Dobra hra"));
		
		
	//commentService.addComment(new Comment ("Matus", "GuessNumber", "Najlepsia Hra ako som hral"));
		
		
		//favoriteService.setFavorite(new Favorite("Matus", "15 Puzzle"));
		//favoriteService.setFavorite(new Favorite("Matus", "GuessNumber"));
		
		//ratingService.setRating(new Rating("Matus", "Mines", 3));
		//ratingService.setRating(new Rating(System.getProperty("user.name"), "Mines", 1));
	//	ratingService.setRating(new Rating(System.getProperty("user.name"), "15 Puzzle", 3));
	//	ratingService.setRating(new Rating("Jurko", "15 Puzzle", 4));
		//ratingService.setRating(new Rating("Ferko", "GuessNumber", 2));
			
			
		//System.out.println(System.currentTimeMillis() /1000 *60 );
	//	ratingService.setRating(new Rating("Mirko", "GuessNumber", 4));
//		ratingService.setRating(new Rating("Katka", "GuessNumber", 3));
		
	//	System.out.println(ratingService.getAverageRating("Mines"));
		
		//System.out.println(System.getProperty("user.name"));
		
		//commentService.addComment("dobra hra");
		
		//commentService.addComment(new Comment("dobra hra"));
		
		//commentService.addComment(new Comment(""));
		
		System.out.println("--------------------");
		System.out.println("List of Games");
		int index = 1;
		for (ConsoleGameUI game : games) {
			double rating = ratingService.getAverageRating(game.getName());
			System.out.printf("%d. %s (%.2f/5)\n", index, game.getName(), rating);
			index++;
		}

		System.out.println("--------------------");
		do {
			System.out.println("Select a Game, for exit ENTER X");
			String line = scanner.nextLine().trim();
			if ("x".equals(line)) {
				System.exit(0);

			}
			{
				for (ConsoleGameUI game : games) {
					if (line.equals(game.getName()))
						game.run();
				}
			}

			try {
				index = Integer.parseInt(line);

			} catch (NumberFormatException e) {
				index = -1;
			}

		} while (index < 1 || index > games.length);

		games[index - 1].run();

	}

	}
}
