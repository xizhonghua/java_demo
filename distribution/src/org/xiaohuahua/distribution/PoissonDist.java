package org.xiaohuahua.distribution;

import jdistlib.Poisson;

public class PoissonDist extends DiscreteDistributionBase {

	@Override
	protected void estimateParameters() {

		this.lambda_ = this.sample_mean;

		this.parameters_.put("lambda", this.lambda_);

		this.dist_ = new Poisson(this.lambda_);
	}

	@Override
	protected DistributionType getType() {
		return DistributionType.Poisson;
	}

	private double lambda_;

}
