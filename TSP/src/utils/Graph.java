package utils;

import java.util.ArrayList;

/**
 * @Author Xiheng He
 * @ClassName Graph
 * @Description Graph data structure for undirected Graph
 * @Date 2021/06/2021/6/19/16:21
 * @Version 1.0
 */
public class Graph {

    private Integer vertices;
    private Boolean[] visited;
    private ArrayList<Integer>[] matrix;

    /**
     * Graph constructor
     * @param adj adjacency matrix for undirected Graph
     */
    public Graph(int[][] adj) {
        this.vertices = adj.length;
        this.visited = new Boolean[this.vertices];
        this.matrix = new ArrayList[vertices];
        for(int i = 0; i < vertices; i++) {
            matrix[i] = new ArrayList<>();
            visited[i] = false;
        }
        for (int i = 0; i < vertices; i++) {
            for (int j = i; j < vertices; j++) {
                if (adj[i][j] != 0 && i != j)
                    addEdge(i, j);
            }
        }
    }

    /**
     * add edges for undirected Graph
     * @param v1 vertex
     * @param v2 vertex
     */
    public void addEdge(Integer v1, Integer v2) {
        this.matrix[v1].add(v2);
        this.matrix[v2].add(v1);
    }

    /**
     * remove edges for undirected Graph
     * @param v1 vertex
     * @param v2 vertex
     */
    public void removeEdge(Integer v1, Integer v2) {
        this.matrix[v1].remove(v2);
        this.matrix[v2].remove(v1);
    }

    public ArrayList<Integer>[] getMatrix() {
        return this.matrix;
    }

    public int getVertices() {
        return this.vertices;
    }
}
