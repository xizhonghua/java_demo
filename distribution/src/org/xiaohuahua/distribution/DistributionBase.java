package org.xiaohuahua.distribution;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.math3.stat.regression.RegressionResults;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import jdistlib.disttest.DistributionTest;
import jdistlib.generic.GenericDistribution;

import java.util.Arrays;;

public abstract class DistributionBase implements IDistribution {

	public DistributionBase() {

	}

	protected void preproses(double samples[]) {
		this.samples = samples.clone();
		this.sample_size = this.samples.length;
		this.p = new double[this.sample_size];
		this.q = new double[this.sample_size];
		this.parameters_ = new HashMap<>();

		Arrays.sort(this.samples);

		for (int i = 0; i < this.sample_size; ++i)
			p[i] = (i + 0.5) / this.sample_size;
		this.sample_mean = this.mean();
		this.sample_stddev = this.stddev(this.sample_mean);
	}

	@Override
	public FitResult fit(double[] samples, double confidence_level) {
		this.preproses(samples);

		this.estimateParameters();

		double p_val = 0;

		if (dist_ != null) {
			double[] ps = DistributionTest.kolmogorov_smirnov_test(this.samples, dist_, false);

			p_val = ps[1];
		}

		FitResult result = new FitResult(this.getType(), p_val >= 1 - confidence_level, p_val, parameters_);

		return result;
	}

	protected abstract void estimateParameters();

	protected abstract DistributionType getType();

	protected double[] getRandomVals(int n) {
		return dist_.random(n);
	}

	protected double mean() {
		double sum = 0.0;
		for (double sample : this.samples) {
			sum += sample;
		}
		return sum / this.sample_size;
	}

	protected double stddev(double mean) {
		double sum = 0.0;
		for (double sample : this.samples) {
			sum += (sample - mean) * (sample - mean);
		}

		return Math.sqrt(sum / (this.sample_size - 1));
	}

	public String toString() {
		return "{ N = " + this.sample_size + " mean = " + this.sample_mean + ", stddev = " + this.sample_stddev + "}";
	}

	public static double computeRSquared(double[] samples, double[] q) {
		SimpleRegression sr = new SimpleRegression();
		for (int i = 0; i < samples.length; ++i)
			sr.addData(samples[i], q[i]);
		RegressionResults result = sr.regress();

		return result.getRSquared();
	}

	Map<String, Double> parameters_;

	protected double[] samples;
	protected double sample_mean;
	protected double sample_stddev;
	protected double[] p;
	protected double[] q;
	protected int sample_size;
	protected GenericDistribution dist_;

	public static void printSamples(double[] samples) {
		Arrays.sort(samples);
		System.out.print("Samples = [");
		for (double sample : samples)
			System.out.print(" " + sample);
		System.out.println("]");
	}

}
