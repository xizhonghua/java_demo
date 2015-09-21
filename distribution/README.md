## Distribution Estimation

Given sample points, try to fit the samples to a distribution and estimate the parameters of the distribution. 

Supported distributions:
* Uniform
* Normal
* LogNormal
* Exponential
* Poisson

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
```
```
[
  {
    name = NormalDist, 
    p_val = 0.33620107417241696, 
    parameters = {
      sigma:2.1015778878687157, 
      mu:9.844090121182338
    }
]
```

#### Dependencies
* JDistlib http://jdistlib.sourceforge.net/
