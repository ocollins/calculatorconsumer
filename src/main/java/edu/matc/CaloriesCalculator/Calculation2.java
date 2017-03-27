package edu.matc.CaloriesCalculator;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Calculation2{

	@JsonProperty("Calories Burned:")
	private int caloriesBurned;

	@JsonProperty("Duration:")
	private double duration;

	public void setCaloriesBurned(int caloriesBurned){
		this.caloriesBurned = caloriesBurned;
	}

	public int getCaloriesBurned(){
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
			"Calculation2{" + 
			"calories Burned: = '" + caloriesBurned + '\'' + 
			",duration: = '" + duration + '\'' + 
			"}";
		}
}