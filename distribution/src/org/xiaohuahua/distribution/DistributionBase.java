package org.xiaohuahua.distribution;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.stat.inference.ChiSquareTest;
import org.apache.commons.math3.stat.regression.RegressionResults;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import jdistlib.disttest.DistributionTest;
import jdistlib.generic.GenericDistribution;

import java.util.Arrays;;

public abstract class DistributionBase implements IDistribution {

	public DistributionBase() {

	}

	protected abstract void estimateParameters();

	protected abstract DistributionType getType();

	public abstract boolean isContinous();

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

			if (this.isContinous()) {
				p_val = this.fitContinous(this.samples, confidence_level);
			} else {
				p_val = this.fitDiscrete(this.samples, confidence_level);
			}
		}

		FitResult result = new FitResult(this.getType(), p_val >= 1 - confidence_level, p_val, parameters_);

		return result;
	}

	private double fitContinous(double[] samples, double confidence_level) {
		double[] ps = DistributionTest.kolmogorov_smirnov_test(samples, this.dist_, false);

		return ps[1];
	}

	private double fitDiscrete(double[] samples, double confidence_level) {
		// number of estimated parameters
		int c = this.parameters_.size();

		// 6 ~ 16 bins
		int bins = 6; //Math.min(16, Math.max(samples.length / 5, 5 + c));
		System.out.println("bins = " + bins);

		final int augment = 10;

		double[] expected_samples = this.dist_.random(samples.length * augment);
		long[] hist_O1 = Histogram.getHistrogram(samples, bins);
		long[] hist_O2 = Histogram.getHistrogram(expected_samples, bins);

		for (int i = 0; i < hist_O2.length; ++i) {
			hist_O2[i] /= augment;

			if (hist_O1[i] == 0 && hist_O2[i] == 0) {
				hist_O1[i] = hist_O2[i] = 1; // hack...
			}
		}

		ChiSquareTest test = new ChiSquareTest();

		
		double p_val = test.chiSquareTestDataSetsComparison(hist_O1, hist_O2);
		return p_val;

	}

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
