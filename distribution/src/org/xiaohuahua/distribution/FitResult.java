package org.xiaohuahua.distribution;

import java.util.Map;

public class FitResult {
	
	private String distName_;
	private boolean fitted_;
	private double r_squared_;
	private Map<String, Double> parameters_;
	
	public FitResult(String distName,  boolean fitted, double r_squared, Map<String, Double> parameters)
	{
		this.distName_ = distName;
		this.fitted_ = fitted;
		this.r_squared_ = r_squared;
		this.parameters_ = parameters;
	}
	
	public String getDistName() {
		return distName_;
	}
	
	public boolean getFitted() {
		 return fitted_;
	}
	
	public double getRSquared() {
		return r_squared_;
	}
	
	public Map<String, Double> getParameters() {
		return parameters_;
	}
	
	public String toString() {
		String str = "{ name = " + this.getDistName() + ", r^2 = " + this.r_squared_ + ", parameters = {";
		
		Map<String, Double> parameters = this.getParameters(); 
		
		boolean first = true;
		for(String key : parameters.keySet()) {
			if(!first) str += ", ";
			first = false;
			str += key + ':' + parameters.get(key);
		}
		
		str+= "}";
		return str;
	}
}
