package calories.tracker.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


import calories.tracker.app.model.MealType;
import calories.tracker.app.services.MealTypeService;


@Controller
@RequestMapping("meal/type")
public class MealTypeController {
	/**
     * search Meals for the current user by date and time ranges.
     *
     *
     * @param principal  - the current logged in user
     * @return - @see MealType with the current page, total pages and the list of meals
     */
	@Autowired
	MealTypeService mealTypeService;
	
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET)
    public List<MealType> getMealType() {
    	List<MealType> mealTypes= mealTypeService.getMealTypes();
    	return mealTypes;
    }
}