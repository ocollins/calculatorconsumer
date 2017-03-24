package edu.matc.CaloriesCalculator;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Activities{

	@JsonProperty("activities")
	private List<Activity> activities;

	public void setActivities(List<Activity> activities){
		this.activities = activities;
	}

	public List<Activity> getActivities(){
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