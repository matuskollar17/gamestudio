package gamestudio.service.impl;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import gamestudio.entity.Rating;
import gamestudio.service.RatingService;

public class RatingServiceRestClient implements RatingService {
	private static final String URL = "http://localhost:8080/rest/score";

	@Override
	public void setRating(Rating rating) {
		Client client = ClientBuilder.newClient();
		Response response = client.target(URL).request(MediaType.APPLICATION_JSON)
				.post(Entity.entity(rating, MediaType.APPLICATION_JSON), Response.class);
		
	}

	@Override
	public double getAverageRating(String game) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
