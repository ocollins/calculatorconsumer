package edu.matc.CaloriesCalculator;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Activities{

	@JsonProperty("Activities")
	private List<ActivitiesItem> activities;

	public void setActivities(List<ActivitiesItem> activities){
		this.activities = activities;
	}

	public List<ActivitiesItem> getActivities(){
		return activities;
	}

	@Override
 	public String toString(){
		return 
			"Activities{" + 
			"activities = '" + activities + '\'' + 
			"}";
		}
}