package gamestudio;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import gamestudio.consoleui.ConsoleGameUI;
import gamestudio.consoleui.ConsoleMenu;
import gamestudio.game.minesweeper.consoleui.ConsoleUI;
import gamestudio.game.minesweeper.core.Field;
import gamestudio.service.CommentService;
import gamestudio.service.FavoriteService;
import gamestudio.service.RatingService;
import gamestudio.service.ScoreService;
import gamestudio.service.impl.CommentServiceJPA;
import gamestudio.service.impl.FavoriteServiceJPA;
import gamestudio.service.impl.RatingServiceJPA;
import gamestudio.service.impl.ScoreServiceJPA;
import gamestudio.service.impl.ScoreServiceRestClient;

@Configuration
@SpringBootApplication
@ComponentScan(basePackages = { "gamestudio" },
excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = "gamestudio.server.*"))

public class SpringClient {

	public static void main(String[] args) {

		//SpringApplication.run(SpringClient.class, args);
		new SpringApplicationBuilder(SpringClient.class).web(false).run(args);
	}

	@Bean
	public CommandLineRunner runner(ConsoleMenu menu) {
				//return args -> ui.run();
				return args -> menu.show();
	}
	
	
	@Bean
	public ConsoleMenu menu(ConsoleGameUI[] games) {
		return new ConsoleMenu(games);
		//return args -> menu.show();
	}
	
	@Bean
	public ConsoleGameUI consoleUIMines(Field field) {
		return new ConsoleUI(field);
	}
	
	@Bean
	public Field fieldMines() {
		return new Field(9,9,10);
	}

	
	@Bean
	public ConsoleGameUI consoleUIPuzzle(gamestudio.game.puzzle.core.Field field) {
		return new gamestudio.game.puzzle.consoleui.ConsoleUI(field);
	}
	
	@Bean
	public gamestudio.game.puzzle.core.Field fieldPuzzle() {
		return new gamestudio.game.puzzle.core.Field(4, 5);
	}
	
	
	@Bean
	public ConsoleGameUI consoleUIGuessNumber() {
		return (ConsoleGameUI) new gamestudio.game.guessnumber.consoleui.ConsoleUI();
	}
	
	
	@Bean
	public CommentService commentService() {
		return new CommentServiceJPA();
	}
	
	
	
	@Bean
	public RatingService ratingService() {
		return new RatingServiceJPA();
	}
	
/*	@Bean
	public ScoreService scoreService() {
		return new ScoreServiceJPA();
	}*/
	
	
	@Bean
	public ScoreService scoreService() {
		return new ScoreServiceRestClient();
	}
	
	
	
	@Bean
	public FavoriteService favoriteService() {
		return new FavoriteServiceJPA();
	}
	
	
	
	
	
}
