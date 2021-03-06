package org.xiaohuahua.distribution;

import jdistlib.LogNormal;

public class LogNormalDist extends ContinusDistributionBase {

	@Override
	protected void estimateParameters() {
		valid_ = true;

		for (double sample : samples) {
			if (sample <= 0) {
				valid_ = false;
				return;
			}
		}

		double[] logedSamples = this.samples.clone();
		for (int i = 0; i < logedSamples.length; ++i)
			logedSamples[i] = Math.log(logedSamples[i]);

		NormalDist normalDist = new NormalDist();
		FitResult r = normalDist.fit(logedSamples, 1.0);

		double sigma = r.getParameters().get("sigma");
		double mu = r.getParameters().get("mu");

		this.shape_ = sigma;
		this.scale_ = Math.exp(mu);
		this.loc_ = 0.0;

		this.parameters_.put("shape", this.shape_);
		this.parameters_.put("scale", this.scale_);
		this.parameters_.put("loc", this.loc_);

		// double mean = Math.exp(this.scale_ + this.shape_ * this.shape_ / 2);
		// double variance = (Math.exp(this.shape_ * this.shape_) - 1) * mean *
		// mean;

		this.dist_ = new LogNormal(mu, sigma);
	}

	@Override
	protected double[] getRandomVals(int n) {
		if (!valid_)
			return new double[n];
		return super.getRandomVals(n);
	}
	
	@Override
	protected DistributionType getType() {
		return DistributionType.LogNormal;
	}
	
	private boolean valid_;

	private double shape_;
	private double loc_;
	private double scale_;

}
