package calories.tracker.app.model;


import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Time;
import java.util.Date;

/**
 *
 * The Meal JPA entity
 *
 */
@Entity
@Table(name = "MEAL_TYPE")
public class MealType extends AbstractEntity {

    private String description;

    public MealType() {

    }

    public MealType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "MealType{" +
                "description='" + description + '\'' +
                '}';
    }
}
