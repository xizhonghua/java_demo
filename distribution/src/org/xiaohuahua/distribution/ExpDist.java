package org.xiaohuahua.distribution;

public class ExpDist extends DistributionBase implements IDistribution {

	public ExpDist() {		
	}

	@Override
	protected void fitDist() {	
		
		this.lambda_ = 1.0 / this.sample_mean;
		
		for(int i=0;i<this.sample_size;++i)
			q[i] = Math.log(1.0 - p[i]) / this.lambda_;
		
		this.parameters_.put("lambda", this.lambda_);		
	}

	
	private double lambda_;

}
