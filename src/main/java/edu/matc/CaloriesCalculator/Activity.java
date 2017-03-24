package edu.matc.CaloriesCalculator;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Activity{

	@JsonProperty("mets")
	private double mets;

	@JsonProperty("name")
	private String name;

	@JsonProperty("id")
	private int id;

	public void setMets(double mets){
		this.mets = mets;
	}

	public double getMets(){
		return mets;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"ActivitiesItem{" + 
			"mets = '" + mets + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}