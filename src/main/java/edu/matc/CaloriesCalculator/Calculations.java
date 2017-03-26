package edu.matc.CaloriesCalculator;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Calculations{

	@JsonProperty("Calculation 1:")
	private Calculation1 calculation1;

	@JsonProperty("Calculation 0:")
	private Calculation0 calculation0;

	@JsonProperty("Calculation 2:")
	private Calculation2 calculation2;

	public void setCalculation1(Calculation1 calculation1){
		this.calculation1 = calculation1;
	}

	public Calculation1 getCalculation1(){
		return calculation1;
	}

	public void setCalculation0(Calculation0 calculation0){
		this.calculation0 = calculation0;
	}

	public Calculation0 getCalculation0(){
		return calculation0;
	}

	public void setCalculation2(Calculation2 calculation2){
		this.calculation2 = calculation2;
	}

	public Calculation2 getCalculation2(){
		return calculation2;
	}

	@Override
 	public String toString(){
		return 
			"Calculations{" + 
			"calculation 1: = '" + calculation1 + '\'' + 
			",calculation 0: = '" + calculation0 + '\'' + 
			",calculation 2: = '" + calculation2 + '\'' + 
			"}";
		}
}