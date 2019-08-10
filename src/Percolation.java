import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    // Weighted quick union find
    WeightedQuickUnionUF weightedUF;

    // Sites
    private int[][] sites;

    // open or closed site track
    private boolean[][] openOrBlocked;

    // size of n
    private int sizeOfN;

    // Number of open sites

    private  int countOpenSites;

//    private


    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){

        if(n <= 0){
            throw  new IllegalArgumentException("N must be greater than 0");
        }
        else {
            weightedUF = new WeightedQuickUnionUF(n*n);
            sizeOfN = n;
            sites = new int[sizeOfN][sizeOfN];
            openOrBlocked = new boolean[sizeOfN][sizeOfN];
            for (int i = 0; i < sizeOfN; i++) {
                for (int j = 0; j < sizeOfN; j++) {
                    sites[i][j] = i + j;
                    openOrBlocked[i][j] = false;
                }
            }
        }

    }

    // opens the site (row, col) if it is not open already
    // It will also connect to any neighbour site that is already open



    public void open(int row, int col){

        if(row <= 0 || col <= 0){
            throw new IllegalArgumentException("Row and column must be grater than 0");
        }
        else {
            row = row - 1;
            col = col -1;

            // check if the selected site resides on the side of the grid
            int[][] neighbours;


            /*
                open from the top row, has 2 rows if the sites are in the corner and 3 neighbours if the
                the site is not a corner
            */
            if(row == 0){

                // The first corner at sites[0][0]

                if(col == 0){

                    // It has always 2 neighbours

                    neighbours = new int[2][2];

                    // The right side neighbour
                    neighbours[0][0] = row;
                    neighbours[0][1] = col + 1;
                    // The bottom neighbour
                    neighbours[1][0] = row + 1;
                    neighbours[1][1] = col;
                }

                // the second corner at [0][sizeOfN - 1], it has always 2 neighbours
                else if(col == sizeOfN -1){

                    neighbours = new int[2][2];

                    // the left side neighbour
                    neighbours[0][0] = row;
                    neighbours[0][1] = col - 1;
                    // the bottom neighbour
                    neighbours[1][0] = row + 1;
                    neighbours[1][1] = col;

                }

                // all other top row items row[0][col], they have always 3 neighbours
                else {
                    neighbours = new int[3][2];

                    // the left side neighbour
                    neighbours[0][0] = row;
                    neighbours[0][1] = col - 1;
                    // the right side neighbour
                    neighbours[1][0] = row;
                    neighbours[1][1] = col + 1;
                    // the bottom side neighbour
                    neighbours[2][0] = row + 1;
                    neighbours[2][1] = col;
                }
            }

            // open from the bottom row

            else if(row == sizeOfN -1){

                // the third corner has always only 2 neighbours
                if(col == 0){
                    neighbours = new int[2][2];

                    // the top neighbour it has always 2 neighbours
                    neighbours[0][0] = row - 1;
                    neighbours[0][1] = col;
                    // the right side neighbour
                    neighbours[1][0] = row ;
                    neighbours[1][1] = col + 1;
                }
                // the fourth corner
                else if( col == sizeOfN - 1){
                    neighbours = new int[2][2];

                    // the top neighbour it has always 2 neighbours
                    neighbours[0][0] = row -1;
                    neighbours[0][1] = col;
                    // the left side neighbour
                    neighbours[1][0] = row;
                    neighbours[1][1] = col - 1;
                }

                // all other rows from the bottom row. The always have 3 neighbours
                else {

                    neighbours = new int[3][2];

                    // the top neighbour
                    neighbours[0][0] = row - 1;
                    neighbours[0][1] = col;
                    // the left side neighbour
                    neighbours[1][0] = row;
                    neighbours[1][1] = col - 1;
                    // the right side neighbour
                    neighbours[2][0] = row;
                    neighbours[2][1] = col + 1;

                }
            }

            // open from the left side except the corner sites (already addressed above) they always have 3 neighbors

            else if(col == 0){

                neighbours = new int[3][2];

                // the first neighbour
                neighbours[0][0] = row;
                neighbours[0][1] = col + 1;
                // the second neighbour
                neighbours[1][0] = row -1;
                neighbours[1][1] = col;
                // the third neighbour
                neighbours[2][0] = row + 1;
                neighbours[2][1] = col;


            }

            // open from the right side sites
            else if(col == sizeOfN -1){
                neighbours = new int[3][2];

                // the first neighbour

                neighbours[0][0] = row - 1;
                neighbours[0][1] = col;

                // the second neighbour
                neighbours[1][0] = row + 1;
                neighbours[1][1] = col;

                // the third neighbour
                neighbours[2][0] = row;
                neighbours[2][1] = col - 1;
            }

            // open from any other internal sites they have 4 each have four neighbours

            else {

            }


        }

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        if(row <= 0 || col <= 0){
            throw new IllegalArgumentException("Row and column must be grater than 0");
        }
        else {
            return  openOrBlocked[row - 1][col - 1];
        }
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        return true;
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return  countOpenSites;
    }

    // does the system percolate?
    public boolean percolates(){
        return  true;
    }

    // test client (optional)
    public static void main(String[] args){

    }
}