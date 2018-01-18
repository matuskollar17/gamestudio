package gamestudio.entity;





import java.util.Date;

import gamestudio.service.CommentService;
import gamestudio.service.ScoreService;
import gamestudio.service.impl.CommentServiceJDBC;
import gamestudio.service.impl.ScoreServiceJDBC;

public class Test {

	public static void main(String[] args) {
		
		Score score = new Score();
		score.setUsername("Janko");
		score.setGame("Mines");
		score.setValue(89);
		
		ScoreService scoreService = new ScoreServiceJDBC();
		
		scoreService.addScore(score);
		


		System.out.println(scoreService.getTopScores("Mines"));
		
		
		
		
		Date date = new Date();
		Comment comment = new Comment();
		comment.setUsername("Jakub");
		comment.setGame("mines");
		comment.setContent("Hra bola na ... ");
		 java.util.Date utilDate = new java.util.Date();
		    java.sql.Timestamp sqlDate = new java.sql.Timestamp(utilDate.getTime());
		comment.setCreatedOn(sqlDate);
		CommentService commentService = new CommentServiceJDBC();
		commentService.addComment(comment);
		
		System.out.println(commentService.getComments("mines"));

//			Rating rating = new Rating();
//			rating.setGame("mines");
//			rating.setUsername("Jakobo");
//			rating.setValue(5);
//			RatingService ratingService = new RatingServiceJDBC();
//			ratingService.setRating(rating);
//			
//		System.out.println(ratingService.getAverageRating("mines"));
//			
	//
	}

}
