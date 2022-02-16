package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * @Author Xiheng He
 * @ClassName NN
 * @Description Nearest Neighbor greedy algorithm for TSP
 * @Date 2021/06/2021/6/20/0:52
 * @Version 1.0
 */
public class NN {

    private int vertices;
    private int[][] matrix;
    private Stack<Integer> nn;
    private ArrayList<String> tours;

    public NN(int[][] adj) {
        this.nn = new Stack<>();
        this.tours = new ArrayList<>();
        this.vertices = adj.length;
        this.matrix = Arrays.copyOf(adj, adj.length);
    }

    public void travel() {
        int[] visited = new int[this.matrix.length];
        visited[0] = 1;
        this.nn.push(1);
        this.tours.add("The best possible route is: " + 1 + " -> ");

        int currVertex, distance = 0, minCost = Integer.MAX_VALUE;
        boolean isMin = false;

        int index;
        while (!this.nn.isEmpty()) {
            minCost = Integer.MAX_VALUE;
            currVertex = this.nn.peek();
            index = 1;
            while (index < this.vertices) {
                int nextCost = this.matrix[currVertex][index];
                if (nextCost > 1 && visited[index] == 0) {
                    if (minCost > nextCost) {
                        minCost = nextCost;
                        distance = index;
                        isMin = true;
                    }
                }
                index++;
            }
            if (isMin) {
                visited[distance] = 1;
                this.nn.push(distance);
                this.tours.add((distance + 1) + " -> ");
                isMin = false;
                continue;
            }
            this.nn.pop();
        }
    }

    public void printTour() {
        for (String route: this.tours)
            System.out.print(route);
        System.out.println(1);
    }
}
