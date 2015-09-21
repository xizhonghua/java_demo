package org.xiaohuahua.distribution;

import jdistlib.Poisson;

public class PoissonDist extends DistributionBase implements IDistribution {

	@Override
	protected void estimateParameters() {
		
		this.lambda_ = this.sample_mean;
		// what about k????
		
		this.parameters_.put("lambda", this.lambda_);

		this.dist_ = new Poisson(this.lambda_);
		
//		super.printSamples(this.dist_.random(100));
	}
	
	private double lambda_;
	private int k_;

}
