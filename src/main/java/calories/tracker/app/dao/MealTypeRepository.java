package calories.tracker.app.dao;

import calories.tracker.app.model.MealType;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

/**
 *
 * Repository class for the Meal entity
 *
 */
@Repository
public class MealTypeRepository {

	private static final Logger LOGGER = Logger.getLogger(MealTypeRepository.class);

	@PersistenceContext
	EntityManager em;

	/**
	 *
	 * counts the matching meals, given the bellow criteria
	 *
	 * 
	 * @return - a list of mealTypes
	 */
	public List<MealType> getMealTypes() {

		List<MealType> mealTypes = em.createNamedQuery(MealType.FIND_MealTypes, MealType.class).getResultList();

		return mealTypes;
	}

}