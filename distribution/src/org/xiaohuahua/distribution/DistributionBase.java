package org.xiaohuahua.distribution;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.math3.stat.regression.RegressionResults;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import jdistlib.util.*;
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
	public FitResult fit(double[] samples, double r_squared_threshold) {
		this.preproses(samples);
		this.fitDist();

		double r_squared = computeRSquared(this.samples, this.q);

		FitResult result = new FitResult(this.getClass().getSimpleName(), r_squared >= r_squared_threshold, r_squared,
				parameters_);

		System.out.println(this.toString());
		System.out.println(result.toString());

		return result;
	}

	protected abstract void fitDist();

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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
