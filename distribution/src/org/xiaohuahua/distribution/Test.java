package org.xiaohuahua.distribution;

import java.util.Date;
import java.util.List;
import jdistlib.Exponential;
import jdistlib.LogNormal;
import jdistlib.Normal;
import jdistlib.rng.MersenneTwister;
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
		RandomEngine re = new MersenneTwister();
		re.setSeed(new Date().getTime());
		
		double[] expSamples = Exponential.random(500, 1, re);
		
		List<FitResult> results = WhichDistribution.whichDistribution(expSamples, 0.95);
		
		printResult("ExpDist", results);
		
		double[] normalSamples = Normal.random(500, 1.0, 2.0, re);
		
		results = WhichDistribution.whichDistribution(normalSamples, 0.95);
		
		printResult("NormalDist", results);
		
		
		double[] logNormalSamples = LogNormal.random(500, 0.0, 1.0, re);
		
		results = WhichDistribution.whichDistribution(logNormalSamples, 0.90);
		
		printResult("LogNormalDist", results);
		
	}
}
