## Distribution Estimation

Given sample points, try to fit the samples to a distribution and estimate the parameters of the distribution. 

Supported distributions:
* Uniform
* Normal
* LogNormal
* Exponential
* Poisson

See [DistributionType.java](src/org/xiaohuahua/distribution/DistributionType.java) for the complete list

#### Usage:

```java
double[] samples = get_your_samples();
double confidence_level = 0.95;
List<FitResult> results = WhichDistribution.whichDistribution(samples, confidence_level);
```

#### Example:

```java
RandomEngine re = new MersenneTwister();
re.setSeed(new Date().getTime());
double[] samples = Normal.random(500, 10.0, 2.0, re);
List<FitResult> results = WhichDistribution.whichDistribution(samples, 0.95);

// Output
[
  {
    type = Normal, 
    p_val = 0.33620107417241696, 
    parameters = {
      sigma:2.1015778878687157, 
      mu:9.844090121182338
    }
]
```

#### Implementaion
1. For each distrition D in the pool, estimate the parameters from samples
2. Draw the same size of random variables from D with estimated parameters
3. Perform Kolmogorov Smirnov Test on two sets of samples
4. Compare p value and the given confidence level


#### Dependencies
* JDistlib http://jdistlib.sourceforge.net/
