package calories.tracker.app;

import static calories.tracker.app.TestUtils.date;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import calories.tracker.app.model.Meal;
import calories.tracker.app.model.MealType;
import calories.tracker.app.model.SearchResult;
import calories.tracker.app.services.MealService;
import calories.tracker.app.services.MealTypeService;
import calories.tracker.config.root.RootContextConfig;
import calories.tracker.config.root.TestConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
@ContextConfiguration(classes={TestConfiguration.class, RootContextConfig.class})
public class MealTypeServiceTest {
    @Autowired
    private MealTypeService mealTypeService;

    @PersistenceContext
    private EntityManager em;
    
    @Test 
    public void testGetMealTypes() {
        List<MealType> result = mealTypeService.getMealTypes();
        assertTrue("results not expected, total " + result.size(), result.size() == 3);
    }
}
