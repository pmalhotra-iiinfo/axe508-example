package calories.tracker.app.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import calories.tracker.app.dao.MealTypeRepository;
import calories.tracker.app.model.MealType;

@Service
public class MealTypeService {
	private static final Logger LOGGER = Logger.getLogger(MealTypeService.class);
	@Autowired
	private MealTypeRepository mealTypeRepository;

	@Transactional(readOnly = true)
	public List<MealType> getMealTypes() {
		return mealTypeRepository.getMealTypes();
	}

}