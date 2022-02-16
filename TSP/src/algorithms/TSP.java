package algorithms;

import java.util.Arrays;

/**
 * @Author Xiheng He
 * @ClassName TSP
 * @Description this class find all permutation in TSP problem
 * @Date 2021/06/2021/6/17/0:20
 * @Version 1.0
 */
public class TSP {
    
    private int count;
    private int minCost;
    private int[][] matrix;
    private int[] vertex;
    private int[] bestTour;
    private int permutation = 1;

    public TSP(int[][] matrix) {
        this.count = 0;
        this.minCost = Integer.MAX_VALUE;
        this.matrix = matrix;
        this.vertex = new int[matrix.length];
        for (int j = 1; j <= matrix.length; j++) {
            this.permutation = this.permutation * j;
            this.vertex[j - 1] = j - 1;
        }
    }

    /**
     * swap two element in array
     * @param a element a
     * @param x element x
     * @param y element y
     */
    public void swap(int[] a, int x, int y) {
        int temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }


    public void travel(int start, int currPos) {
        // if all vertices were already visited, traveling finished
        if (currPos == this.matrix.length) {
            count++;
            findTour(this.vertex);
        }
        // all permutations found, output result
        if (count == this.permutation) {
            System.out.print("Optimal tour is: ");
            for (Integer v: this.bestTour)
                System.out.print("c" + (v + 1) + " -> ");
            System.out.println("c" + (bestTour[0] + 1));
            System.out.println("cost is: " + minCost);
        }
        else {
            for (int i = start; i < this.matrix.length; i++) {
                swap(this.vertex, start, i);
                travel(start + 1, currPos + 1);
                swap(this.vertex, i, start);
            }
        }
    }

    public int[] findTour(int[] tour) {
        int cites = this.matrix.length;
        int currCost = this.matrix[tour[0]][tour[cites - 1]];
        for (int i = 0; i < cites - 1; i++) {
            currCost += this.matrix[tour[i]][tour[i + 1]];
        }
        if (this.minCost > currCost) {
            this.minCost = currCost;
            this.bestTour = Arrays.copyOf(tour, tour.length);
        }
        return this.bestTour;
    }
}
