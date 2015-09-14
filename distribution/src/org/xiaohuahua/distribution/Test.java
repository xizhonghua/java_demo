package org.xiaohuahua.distribution;

import java.util.Date;
import java.util.List;
import jdistlib.Exponential;
import jdistlib.rng.RandomCMWC;
import jdistlib.rng.RandomEngine;

public class Test {
	
	public static void printResult(String expected, List<FitResult> actuals)
	{
		System.out.print("Expected = " + expected + " Actuals = [");
		boolean first = true;
		for(FitResult fit : actuals) {
			if(!first) System.out.println(", ");
			first = false;
			System.out.print(fit.toString());
			
		}
		System.out.println("]");
	}
	
	public static void main(String[] args) {
		RandomEngine re = new RandomCMWC();
		re.setSeed(new Date().getTime());
		
		double[] expSamples = Exponential.random(500, 1, re);
		
		List<FitResult> results = WhichDistribution.whichDistribution(expSamples, 0.95);
		
		printResult("ExpFit", results);
		
	}
}
