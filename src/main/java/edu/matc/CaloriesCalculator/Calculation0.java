package edu.matc.CaloriesCalculator;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Calculation0{

	@JsonProperty("Calories Burned:")
	private double caloriesBurned;

	@JsonProperty("Duration:")
	private double duration;

	public void setCaloriesBurned(double caloriesBurned){
		this.caloriesBurned = caloriesBurned;
	}

	public double getCaloriesBurned(){
		return caloriesBurned;
	}

	public void setDuration(double duration){
		this.duration = duration;
	}

	public double getDuration(){
		return duration;
	}

	@Override
 	public String toString(){
		return 
			"Calculation0{" + 
			"calories Burned: = '" + caloriesBurned + '\'' + 
			",duration: = '" + duration + '\'' + 
			"}";
		}
}