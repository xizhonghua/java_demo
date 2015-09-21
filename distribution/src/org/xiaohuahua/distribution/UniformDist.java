package org.xiaohuahua.distribution;

import jdistlib.Uniform;

public class UniformDist extends DistributionBase implements IDistribution {

	@Override
	protected void estimateParameters() {		
		this.a_ = this.sample_mean - Math.sqrt(3) * this.sample_stddev;
		this.b_ = this.sample_mean + Math.sqrt(3) * this.sample_stddev;
		
		this.parameters_.put("a", this.a_);
		this.parameters_.put("b", this.b_);
		
		this.dist_ = new Uniform(this.a_, this.b_);
	}

	// Parameters
	private double a_;
	private double b_;

}
