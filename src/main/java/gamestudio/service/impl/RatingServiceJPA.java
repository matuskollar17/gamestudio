package gamestudio.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import gamestudio.entity.Rating;
import gamestudio.service.RatingService;

@Transactional
public class RatingServiceJPA implements RatingService {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void setRating(Rating rating) {
				
		
		try {
			Rating r = (Rating) entityManager
					.createQuery("SELECT r FROM Rating r WHERE r.username = :username AND r.game = :game")
					.setParameter("username", rating.getUsername()).setParameter("game", rating.getGame())
					.getSingleResult();
			r.setValue(rating.getValue());
		} catch (NoResultException e) {
			entityManager.persist(rating);
		}
		
		
		
	}

	@Override
	public double getAverageRating(String game) {
		Object o = entityManager.createQuery("SELECT AVG(r.value) FROM Rating r WHERE r.game = :game ").setParameter("game", game).getSingleResult();
		return o == null ? 0 : (double)o ;
	}
	
	
	
	
	//@Override
	//public void getValue(int value) {
		
	//}
	
	
}
