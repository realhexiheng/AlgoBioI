package algorithms;

import utils.Graph;

/**
 * @Author Xiheng He
 * @ClassName EPP
 * @Description TODO
 * @Date 2021/06/2021/6/18/18:04
 * @Version 1.0
 */
public class EPP {

    Graph eulerGraph;

    public EPP(Graph graph) {
        this.eulerGraph = graph;
    }

    public void printCircuit() {
        // vertex with odd degree
        Integer u = 0;
        for (int i = 0; i < this.eulerGraph.getVertices(); i++)
        {
            if (this.eulerGraph.getMatrix()[i].size() % 2 == 1)
            {
                u = i;
                break;
            }
        }

        printEulerTour(u);
        System.out.println();
    }

    public void printEulerTour(Integer u) {
        for (int i = 0; i < this.eulerGraph.getMatrix()[u].size(); i++)
        {
            Integer v = this.eulerGraph.getMatrix()[u].get(i);
            if (isValidNextEdge(u, v))
            {
                System.out.print(u + "-" + v + " ");
                this.eulerGraph.removeEdge(u, v);
                printEulerTour(v);
            }
        }
    }

    private boolean isValidNextEdge(Integer u, Integer v) {
        if (this.eulerGraph.getMatrix()[u].size() == 1) {
            return true;
        }
        boolean[] isVisited = new boolean[this.eulerGraph.getVertices()];
        int count1 = dfsCount(u, isVisited);

        this.eulerGraph.removeEdge(u, v);
        isVisited = new boolean[this.eulerGraph.getVertices()];
        int count2 = dfsCount(u, isVisited);

        this.eulerGraph.addEdge(u, v);
        return (count1 > count2) ? false : true;
    }

    private int dfsCount(Integer v, boolean[] isVisited)
    {
        // Mark the current node as visited
        isVisited[v] = true;
        int count = 1;
        // Recur for all vertices adjacent to this vertex
        for (int adj : this.eulerGraph.getMatrix()[v])
        {
            if (!isVisited[adj])
            {
                count = count + dfsCount(adj, isVisited);
            }
        }
        return count;
    }
}
