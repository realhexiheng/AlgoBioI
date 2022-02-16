package algorithms;

import utils.Graph;

import java.util.*;

/**
 * @Author Xiheng He
 * @ClassName MST
 * @Description Minimal Spanning tree algorithm for TSP
 * @Date 2021/06/2021/6/20/0:57
 * @Version 1.0
 */
public class MST {

    private int vertices;
    private int[][] matrix;
    private Graph tsp;

    public MST(int[][] adj) {
        this.vertices = adj.length;
        this.matrix = adj;
        this.tsp = new Graph(adj);
    }

    /**
     * This method based on prim Algorithm to build minimal spanning tree
     */
    public void buildMST(int start) {
        // store all MSTs;
        int[] parent = new int[this.vertices];
        // edges with minimal value
        int[] edge = new int[this.vertices];
        // MST vertices
        Boolean minVertex[] = new Boolean[this.vertices];
        // add nearest vertex to the vertex with minimal value
        int[] adjacent = new int[this.vertices];

        // initialize all arrays for MST
        for (int i = 0; i < this.vertices; i++) {
            edge[i] = Integer.MAX_VALUE;
            minVertex[i] = false;
        }

        // add first vertex to MST
        edge[start] = start;
        // set MST root
        parent[start] = -1;
        // add adjacent
        adjacent[start] = start;

        // run prim Algorithm
        for (int i = 0; i < this.vertices - 1; i++) {
            // construct MST
            int u = this.getMinVertex(edge, minVertex);
            minVertex[u] = true;
            // update matrix and use any other not included vertices to construct MST
            for (int v = 0; v < this.vertices; v++) {
                // check there is edge and edge has a cost less than edge[v] and not in MST yet
                if (this.matrix[u][v] != 0 && this.matrix[u][v] < edge[v] && !minVertex[v]) {
                    // add vertex to parent and replace with a edge which has less cost
                    parent[v] = u;
                    edge[v] = this.matrix[u][v];
                    adjacent[v] = u;
                }
            }
        }
        printMST(parent);
        System.out.print("TSP according to MST: c" + (start + 1) + " -> ");
        preorder(start, adjacent);
        System.out.print("c" + (start + 1));
    }

    public void printMST(int parent[])
    {
        System.out.println("Edge \t Weight");
        for (int i = 1; i < this.vertices; i++)
            System.out.println((parent[i] + 1) + " - " + (i + 1) + " \t " + this.matrix[i][parent[i]]);
    }


    public void preorder(int start, int[] array){

        ArrayList list = nextVertex(start, array);

        Iterator it = list.iterator();

        if(!list.isEmpty()){
            while(it.hasNext()){
                int x = (int)it.next();
                System.out.print("c" + (x + 1) + " -> ");
                if(x != start)
                    preorder(x, array);
            }
        }
    }

    public static ArrayList nextVertex(int vertex, int[] arr){
        ArrayList list = new ArrayList();
        for(int i = 1; i < arr.length; i++){
            if(vertex == arr[i]){
                list.add(i);
            }
        }

        return list;
    }

    private int getMinVertex(int[] edge, Boolean[] minVertex) {
        int min = Integer.MAX_VALUE;
        int minPos = -1;

        for (int i = 0; i < this.vertices; i++) {
            // check if edge is minimum and not in MST yet
            if (!minVertex[i] && edge[i] < min) {
                // set minimum
                min = edge[i];
                // set vertex with minimum edge
                minPos = i;
            }
        }
        return minPos;
    }


    public void setMatrix(int[][] adj) {
        this.matrix = adj;
    }
}
