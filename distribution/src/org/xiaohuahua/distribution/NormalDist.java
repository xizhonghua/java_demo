package org.xiaohuahua.distribution;

import jdistlib.Normal;

public class NormalDist extends DistributionBase implements IDistribution {

	@Override
	protected void estimateParameters() {
		this.mu_ = this.sample_mean;
		this.sigma_ = this.sample_stddev;
		
		this.parameters_.put("mu", this.mu_);
		this.parameters_.put("sigma", this.sigma_);
		
		this.normal_ = new Normal(this.mu_, this.sigma_);
	}
	
	@Override
	protected double getQuantile(double q) {
		return normal_.quantile(q);
	}
	
	private Normal normal_;
	private double mu_;
	private double sigma_;

}
