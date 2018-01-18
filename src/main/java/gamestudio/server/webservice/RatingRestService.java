package gamestudio.server.webservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import gamestudio.entity.Rating;
import gamestudio.entity.Score;
import gamestudio.service.RatingService;

@Path("/rating")
public class RatingRestService {

	@Autowired
	private RatingService ratingService;

	@POST
	@Consumes("application/json")
	public Response setRating(Rating rating) {
		ratingService.setRating(rating);
		return Response.ok().build();
	}

	// http://localhost:8080/rest/score/mines
	@GET
	@Path("/{game}")
	@Produces("application/json")
	public double getAvarageRating(@PathParam("game") String game) {
		System.out.println("--------- " + game);
		return ratingService.getAverageRating(game);
	
	}
}