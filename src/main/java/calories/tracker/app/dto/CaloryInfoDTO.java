package calories.tracker.app.dto;

import java.sql.Time;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import calories.tracker.app.dto.serialization.CustomTimeDeserializer;
import calories.tracker.app.dto.serialization.CustomTimeSerializer;

public class CaloryInfoDTO {
	   private Long id;

	    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "CET")
	    private Date date;

	    @JsonSerialize(using = CustomTimeSerializer.class)
	    @JsonDeserialize(using = CustomTimeDeserializer.class)
	    private Time time;

	    private String description;
	    private Long calories;

	    public CaloryInfoDTO() {
	    }

	    public CaloryInfoDTO(Long id, Date date, Time time, String description, Long calories) {
	        this.id = id;
	        this.date = date;
	        this.time = time;
	        this.description = description;
	        this.calories = calories;
	    }

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public Time getTime() {
			return time;
		}

		public void setTime(Time time) {
			this.time = time;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public Long getCalories() {
			return calories;
		}

		public void setCalories(Long calories) {
			this.calories = calories;
		}
}
