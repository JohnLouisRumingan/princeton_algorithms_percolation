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
    private boolean[][] percolationSystem; //binary true/false maps to open/close
    private int openLocationsCount;

    private final static int NUM_VIRTUAL = 2;
    //below needed due to starting at 0 array vs. 1 numerical start
    private final static int INDEX_SHIFT = 1;

    //error catch for boundaries
    // param i row, param j column 
    // checks boundaries of percolation system 
    private void checkBoundaries(int i, int j){
        if(i >= sizeOfGrid || j >= sizeOfGrid || i < 0 || j < 0){
            throw new java.lang.IndexOutOfBoundsException(
                "Index out of bounds"
            );
        }
    }

    //maps row col to 1D union find index
    private int ijCoordsTo1DCoords(int i, int j){
        return sizeOfGrid * (i - INDEX_SHIFT ) + j;
    }

    private void connectAdjacentLocations(int row, int col, int sitePosition){
        try {
            if(isOpen(row, col)){
                unionFindDatatype.union(sitePosition, ijCoordsTo1DCoords(row, col))
            }
        }
        catch (IndexOutOfBoundsException e){
        }
    }


    //creates n-by-n grid, with all sites initially blocked
    // param n = size of grid
    public Percolation(int n) {
        this.sizeOfGrid = n;
        this.virtualTopIndex = 0;
        this.virtualBottomIndex = (sizeOfGrid * sizeOfGrid) + 1;
        percolationSystem = new boolean[sizeOfGrid][sizeOfGrid];
        unionFindDatatype = new WeightedQuickUnionUF(sizeOfGrid * sizeOfGrid + NUM_VIRTUAL);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        if (isOpen(row, col)){
            return;
        }
        //opens site
        percolationSystem[row - INDEX_SHIFT][col - INDEX_SHIFT] = true;
        openLocationsCount++;
        //connect newly opened site to all adjacent sites
        int sitePosition = ijCoordsTo1DCoords(row, col);
        //using union which connects locations p and q
        connectAdjacentLocations(row - 1, col, sitePosition); //above
        connectAdjacentLocations(row + 1, col, sitePosition); //below
        connectAdjacentLocations(row, col - 1, sitePosition); //left
        connectAdjacentLocations(row, col + 1, sitePosition); //right
        //connect to virtual top and/or bottom
        if (row == 1){
            unionFindDatatype.union(sitePosition, 0);
        }
        if (row == sizeOfGrid){
            unionFindDatatype.union(virtualBottomIndex, sitePosition);
        }
    }

    // is the site (row, col) open?
    //invoke boundary check, then check if it is open
    public boolean isOpen(int row, int col){
        checkBoundaries( row - INDEX_SHIFT, col - INDEX_SHIFT);
        return percolationSystem[row - INDEX_SHIFT][col - INDEX_SHIFT];
    }

    // is the site (row, col) full?
    // result if open site can be connected to another open site via chain of neighboring sites
    public boolean isFull(int row, int col){
        //check boundaries
        checkBoundaries(row, col);
        // converts 2D coords to 1D coords, then returns true if the two sites are in the same component.
        int sitePosition = ijCoordsTo1DCoords(row, col);
        return unionFindDatatype.connected(0, sitePosition);
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return openLocationsCount;
    }

    // does the system percolate?
    public boolean percolates(){
        boolean result;

        //edge case, if 1 it must percolate or not 
        if (sizeOfGrid == 1 ){
            return percolationSystem[0][0];
        }
        else {
            result = unionFindDatatype.connected(0, virtualBottomIndex);
        }

        return result;
    }

    public static void main(String[] args) {

    }
}
