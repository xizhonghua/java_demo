package org.xiaohuahua.distribution;

public interface IDistribution {
	public FitResult fit(double[] samples, double r_squared_threshold);
}
