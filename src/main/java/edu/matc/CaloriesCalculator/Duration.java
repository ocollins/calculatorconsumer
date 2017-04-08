package edu.matc.CaloriesCalculator;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Duration{

	@JsonProperty("Duration:")
	private double duration;

	public void setDuration(double duration){
		this.duration = duration;
	}

	public double getDuration(){
		return duration;
	}

	@Override
	public String toString(){
		return
				"Response{" +
						"duration: = '" + duration + '\'' +
						"}";
	}
}