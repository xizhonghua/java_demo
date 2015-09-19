package org.xiaohuahua.distribution;

import java.util.ArrayList;
import java.util.List;

public class WhichDistribution {

	public static List<FitResult> whichDistribution(double[] samples, double confidence_level) {
		List<IDistribution> dists = new ArrayList<>();

		dists.add(new ExpDist());
		dists.add(new NormalDist());
		dists.add(new LogNormalDist());

		List<FitResult> results = new ArrayList<>();

		for (IDistribution dist : dists) {
			FitResult r = dist.fit(samples, confidence_level);
			if (r.getFitted()) {
				results.add(r);
			}
		}

		return results;
	}
}
