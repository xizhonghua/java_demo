package org.xiaohuahua.distribution;

import java.util.ArrayList;
import java.util.List;

public class WhichDistribution {

	public static List<FitResult> whichDistribution(double[] samples, double r_squared_threshold) {
		List<IDistribution> dists = new ArrayList<>();

		dists.add(new ExpDist());

		List<FitResult> results = new ArrayList<>();

		for (IDistribution dist : dists) {
			FitResult r = dist.fit(samples, r_squared_threshold);
			if (r.getFitted()) {
				results.add(r);
			}
		}

		return results;
	}
}
