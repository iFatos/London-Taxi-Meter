package com.conjure.LondonTaxiMeter;

public class ParsedData {
	
	private double parsedDistance = 0.0;
	private int parsedTime = 0;
	private String parsedString = null;

	public double getParsedDistance(){
		return parsedDistance;
	}
	
	public String getParsedString(){
		return parsedString;
	}
	public void setParsedString(String s){
		parsedString = s;
	}
	
	public void setParsedDistance(double d){
		parsedDistance = d;
	}
	
	public int getParsedTime(){
		return parsedTime;
	}
	
	public void setParsedTime(int t){
		parsedTime = t;
	}
	
	public String toString(){
		return parsedString;
	}
	
	
	

}
