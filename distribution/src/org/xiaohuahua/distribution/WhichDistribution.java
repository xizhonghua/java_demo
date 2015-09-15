package org.xiaohuahua.distribution;

import java.util.ArrayList;
import java.util.List;

public class WhichDistribution {

	public static List<FitResult> whichDistribution(double[] samples, double r_squared_threshold) {
		List<IDistribution> dists = new ArrayList<>();
		
//		
//		BeanDefinitionRegistry bdr = new SimpleBeanDefinitionRegistry();
//		ClassPathBeanDefinitionScanner s = new ClassPathBeanDefinitionScanner(bdr);
//
//		TypeFilter tf = new AssignableTypeFilter(CLASS_YOU_WANT.class);
//		s.addIncludeFilter(tf);
//		s.scan("org.xiaohuahua.distribution");       
//		String[] beans = bdr.getBeanDefinitionNames();

		dists.add(new ExpDist());
		dists.add(new NormalDist());
		dists.add(new LogNormalDist());

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
