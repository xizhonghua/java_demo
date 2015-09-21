package org.xiaohuahua.distribution;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import jdistlib.Exponential;
import jdistlib.LogNormal;
import jdistlib.Normal;
import jdistlib.Poisson;
import jdistlib.Uniform;
import jdistlib.rng.MersenneTwister;
import jdistlib.rng.RandomEngine;

public class Test {

	public static void printResult(String expected, List<FitResult> actuals) {
		System.out.print("Expected = " + expected + " Actuals = [");
		boolean first = true;
		for (FitResult fit : actuals) {
			if (!first)
				System.out.println(", ");
			first = false;
			System.out.print(fit.toString());

		}
		System.out.println("]");
	}	

	public static void main(String[] args) {
		RandomEngine re = new MersenneTwister();
		re.setSeed(new Date().getTime());
		
		double[] samples;
		
		samples = Normal.random(500, 10.0, 2.0, re);

		List<FitResult> results = WhichDistribution.whichDistribution(samples, 0.95);

		printResult("NormalDist", results);	
		

		samples = Exponential.random(500, 1, re);

		results = WhichDistribution.whichDistribution(samples, 0.95);

		printResult("ExpDist", results);

		samples = LogNormal.random(500, 0.0, 1.0, re);

		results = WhichDistribution.whichDistribution(samples, 0.95);

		printResult("LogNormalDist", results);
		
		samples = Uniform.random(500, -10, 20, re);
		
		results = WhichDistribution.whichDistribution(samples, 0.95);

		printResult("UnifromDist", results);
		
		samples = Poisson.random(500, 1000, re);
		
		results = WhichDistribution.whichDistribution(samples, 0.95);

		printResult("PoissonDist", results);
	}
}
