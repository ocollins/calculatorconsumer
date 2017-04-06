package edu.matc.CaloriesCalculator;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class ActivitiesItem{

	@JsonProperty("activityID")
	private String activityID;

	@JsonProperty("activityTitle")
	private String activityTitle;

	public void setActivityID(String activityID){
		this.activityID = activityID;
	}

	public String getActivityID(){
		return activityID;
	}

	public void setActivityTitle(String activityTitle){
		this.activityTitle = activityTitle;
	}

	public String getActivityTitle(){
		return activityTitle;
	}

	@Override
 	public String toString(){
		return 
			"ActivitiesItem{" + 
			"activityID = '" + activityID + '\'' + 
			",activityTitle = '" + activityTitle + '\'' + 
			"}";
		}
}