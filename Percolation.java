/* *****************************************************************************
 *  Name: Percolation
 *  Date: 4.19.20
 *  Description:  We model a percolation system using an n-by-n grid of sites.
 * Each site is either open or blocked.
 * A full site is an open site that can be connected to an open site in the top row via a chain of neighboring (left, right, up, down) open sites.
 * We say the system percolates if there is a full site in the bottom row.
 * In other words, a system percolates if we fill all open sites connected to the top row and that process fills some open site on the bottom row.
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF unionFindDatatype;
    private int sizeOfGrid;
    private int virtualTopIndex;
    private int virtualBottomIndex;
    private boolean[][] percolationSystem;


    //creates n-by-n grid, with all sites initially blocked
    // param n = size of grid
    public Percolation(int n) {
        this.sizeOfGrid = n;
        this.virtualTopIndex = 0;
        this.virtualBottomIndex = (sizeOfGrid * sizeOfGrid) + 1;
        percolationSystem = new boolean[sizeOfGrid][sizeOfGrid];
        
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){

    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){

    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){

    }

    // returns the number of open sites
    public int numberOfOpenSites(){

    }

    // does the system percolate?
    public boolean percolates(){
        
    }

    public static void main(String[] args) {

    }
}
