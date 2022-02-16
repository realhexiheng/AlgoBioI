package algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Xiheng He
 * @ClassName EKP
 * @Description find Euler Circuit in a Graph
 * @Date 2021/06/2021/6/19/23:22
 * @Version 1.0
 */


public class EKP {

    private int[][] matrix;
    private String[] vertices;
    private List<String> circuits;

    public EKP(int[][] adj) {
        this.vertices = new String[adj.length];
        this.matrix = Arrays.copyOf(adj, adj.length);
        this.circuits = new ArrayList<>();
        for (int i = 0; i < adj.length; i++)
            this.vertices[i] = "c" + (i + 1);
    }

    /**
     * Determine whether to traverse all edges
     * @param matrix adjacency matrix after update
     * @return return true when euler circuit was found
     */
    public static boolean isFinished(int[][] matrix) {
        for (int[] ints : matrix) {
            for (int j = 0; j < matrix.length; j++) {
                if (ints[j] > 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * copy Set<Integer> object
     * @param origin
     * @return copyList
     */
    public static List<Integer> copyList(List<Integer> origin) {
        List<Integer> copy = new ArrayList<>();
        copy.addAll(origin);
        return copy;
    }

    public static int[][] copyMatrix(int[][] matrix) {
        //拷贝副本
        int[][] newMatrix = new int[matrix.length][matrix.length];

        //复制所有值
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                newMatrix[i][j] = matrix[i][j];
            }
        }

        //返回拷贝副本
        return newMatrix;
    }

    public void findCircuit(int[][] matrix, int nextVertex, List<Integer> route, String[] vertices) {
        //先复制一份当前的邻接矩阵
        int[][] newMatrix = copyMatrix(matrix);
        int currentVertex = route.get(route.size() - 1);
        newMatrix[currentVertex][nextVertex]--;
        newMatrix[nextVertex][currentVertex]--;
        List<Integer> newRoute = copyList(route);
        newRoute.add(nextVertex);
        List<Integer> nextList = new ArrayList<>();

        for (int i = 0; i < newMatrix.length; i++) {
            if (newMatrix[nextVertex][i] > 0) {
                nextList.add(i);
            }
        }
        if (nextList.size() > 0) {
            for (Integer next : nextList) {
                findCircuit(copyMatrix(newMatrix), next, copyList(newRoute), vertices);
            }
        } else {
            if (isFinished(newMatrix)) {
                List<String> solutionRoute = new ArrayList<>();
                for (Integer vertexIndex : newRoute) {
                    solutionRoute.add(vertices[vertexIndex]);
                }

                String solution = solutionRoute.toString()
                        .replaceAll("\\[", "")
                        .replaceAll("]", "")
                        .replaceAll(", ", " -> ");

                this.circuits.add(solution);
            }
        }
    }

    public void findCircuit(int startVertex) {
        List<Integer> route = new ArrayList<>();
        route.add(startVertex);
        List<Integer> nextVertexList = new ArrayList<>();

        for (int i = 0; i < this.matrix.length; i++) {
            if (this.matrix[startVertex][i] > 0) {
                nextVertexList.add(this.matrix[startVertex][i]);
            }
        }

        for (Integer nextVertex : nextVertexList) {
            findCircuit(this.matrix, nextVertex, route, this.vertices);
        }
    }

    public void printCircuit() {
        if (this.circuits.size() != 0) {
            for (String circuit: circuits) {
                System.out.println("euler circuit：" + circuit);
            }
            System.out.println("number of euler circuits in total " + this.circuits.size());
        }
        else
            System.out.println("no Euler Circuit in graph.");
    }
}

