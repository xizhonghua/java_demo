package org.xiaohuahua.distribution;

import jdistlib.Exponential;

public class ExpDist extends DistributionBase implements IDistribution {

	public ExpDist() {
	}

	@Override
	protected void estimateParameters() {

		this.lambda_ = 1.0 / this.sample_mean;
		this.parameters_.put("lambda", this.lambda_);
		this.exp_ = new Exponential(lambda_);
	}

	@Override
	protected double getQuantile(double q) {
		return this.exp_.quantile(q);
	}

	private Exponential exp_;
	private double lambda_;

}
