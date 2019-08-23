import edu.princeton.cs.algs4.StdRandom;
public class PercolationStats {
    private Percolation percolation;
    private double mean;
    private double stddev;
    private double confidenceLow;
    private double confidenceHigh;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
        double[] thresholds = new double[trials];
        double sumThresholds = 0.0;
        for (int i = 0; i < trials; i++) {
            percolation = new Percolation(n);
            while (!percolation.percolates()){
                int row = StdRandom.uniform(1, n+1);
                int col = StdRandom.uniform(1, n+1);
                percolation.open(row, col);
            }
            double currentThreshold = ((double)percolation.numberOfOpenSites()/(double)(n * n));
            sumThresholds += currentThreshold;
            thresholds[i] = currentThreshold;
        }
        // mean of thresholds
        mean = sumThresholds / (double)trials;
        // sum of squared mean difference
        double sqMean = 0.0;
        for (int i = 0; i < trials; i++) {
            sqMean += Math.pow((thresholds[i] - mean), 2);
        }
        stddev = Math.pow((sqMean/((double)trials - 1)), 0.5);
        confidenceLow = mean - (1.96*stddev/(Math.pow(trials, 0.5)));
        confidenceHigh = mean + (1.96*stddev/(Math.pow(trials, 0.5)));
    }
    // sample mean of percolation threshold
    public double mean(){
        return mean;
    }
    // sample standard deviation of percolation threshold
    public double stddev(){
        return  stddev;
    }
    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return  confidenceLow;
    }
    // high endpoint of 95% confidence interval
    public double confidenceHi(){

        return  confidenceHigh;
    }
    // test client (see below)
    public static void main(String[] args){
        PercolationStats pstat = new PercolationStats(200, 100);
        System.out.println("mean                    = "+pstat.mean());
        System.out.println("stddev                  = "+pstat.stddev());
        System.out.println("95% confidence interval = ["+pstat.confidenceLo()+", "+pstat.confidenceHi()+"]");
    }
}