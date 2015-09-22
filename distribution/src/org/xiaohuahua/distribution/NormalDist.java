package org.xiaohuahua.distribution;

import jdistlib.Normal;

public class NormalDist extends ContinusDistributionBase {

	@Override
	protected void estimateParameters() {
		this.mu_ = this.sample_mean;
		this.sigma_ = this.sample_stddev;
		
		this.parameters_.put("mu", this.mu_);
		this.parameters_.put("sigma", this.sigma_);
		
		this.dist_ = new Normal(this.mu_, this.sigma_);
				
	}
	
	@Override
	protected DistributionType getType() {
		return DistributionType.Normal;
	}
		
	private double mu_;
	private double sigma_;

}
