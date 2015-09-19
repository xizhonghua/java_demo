package org.xiaohuahua.distribution;

import jdistlib.Exponential;

public class ExpDist extends DistributionBase implements IDistribution {

	public ExpDist() {
	}

	@Override
	protected void estimateParameters() {

		this.lambda_ = 1.0 / this.sample_mean;
		this.parameters_.put("lambda", this.lambda_);
		this.dist_ = new Exponential(lambda_);
	}

	@Override
	protected double getQuantile(double q) {
		return this.dist_.quantile(q);
	}
	
	@Override
	protected double[] getRandomVals(int n)
	{
		return dist_.random(n);
	}
	
	private double lambda_;

}
