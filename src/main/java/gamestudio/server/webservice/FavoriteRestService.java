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

import gamestudio.entity.Comment;
import gamestudio.entity.Favorite;
import gamestudio.service.FavoriteService;

@Path("/favorite")
public class FavoriteRestService {

	@Autowired
	private FavoriteService favoriteService;

	@POST
	@Consumes("application/json")
	public Response setFavorite(Favorite favorite) {
		favoriteService.setFavorite(favorite);
		return Response.ok().build();
	}

	// http://localhost:8080/rest/score/mines
	@GET
	@Path("/{game}")
	@Produces("application/json")
	public List<Favorite> getFavorite(@PathParam("game") String game) {
		System.out.println("--------- " + game);
		return favoriteService.getFavorite(game);
	}

	
	
	
}