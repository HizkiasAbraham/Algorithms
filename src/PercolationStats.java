import edu.princeton.cs.algs4.StdRandom;



public class PercolationStats {

    Percolation percolation;
    int[] thresholds;
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){
        thresholds = new int[trials];

        for (int i = 0; i < trials; i++) {
            percolation = new Percolation(n);
            while (!percolation.percolates()){
                int row = StdRandom.uniform(1, n+1);
                int col = StdRandom.uniform(1, n+1);
                percolation.open(row, col);
                System.out.println("Opening ["+row+"]"+"["+col+"]");
            }
            thresholds[i] = percolation.numberOfOpenSites();
            System.out.println("Percolated "+percolation.numberOfOpenSites());
        }
    }

    // sample mean of percolation threshold
    public double mean(){
            int sum = 0;
        for (int i = 0; i < thresholds.length; i++) {
            sum += thresholds[i];
        }

        return sum/thresholds.length;
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        double squaredMean = 0.0;
        for (int i = 0; i < thresholds.length; i++) {
            squaredMean += Math.pow((thresholds[i] - mean()), 2);
        }

        return  squaredMean/(thresholds.length - 1);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return  0.0;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
    return  0.0;
    }

    // test client (see below)
    public static void main(String[] args){
        PercolationStats pstat = new PercolationStats(200, 100);
        System.out.println("Mean "+pstat.mean());
    }

}