package org.xiaohuahua.distribution;

import java.util.Map;

public class FitResult {
	
	private String distName_;
	private boolean fitted_;
	private double p_val_;
	private Map<String, Double> parameters_;
	
	public FitResult(String distName,  boolean fitted, double p_val, Map<String, Double> parameters)
	{
		this.distName_ = distName;
		this.fitted_ = fitted;
		this.p_val_ = p_val;
		this.parameters_ = parameters;
	}
	
	public String getDistName() {
		return distName_;
	}
	
	public boolean getFitted() {
		 return fitted_;
	}
	
	public double getPValue() {
		return p_val_;
	}
	
	public Map<String, Double> getParameters() {
		return parameters_;
	}
	
	public String toString() {
		String str = "{ name = " + this.getDistName() + ", p_val = " + this.p_val_ + ", parameters = {";
		
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
