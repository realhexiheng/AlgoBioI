import algorithms.*;
import utils.Graph;
import utils.OptionParser;

/**
 * @Author Xiheng He
 * @ClassName TestRunner
 * @Description Runner for testing
 * @Date 2021/06/2021/6/16/23:50
 * @Version 1.0
 */
public class TestRunner {

    public static void main(String[] args) {
        OptionParser parser = new OptionParser("-mode");
        parser.setMandatoryOptions("-mode");
        parser.setUsage("-mode [TSP|EKP|NN|MST]");
        parser.parseArguments(args);

        // undirected adjacency matrix
        int[][] weight = {{0, 10, 5, 9}, {10, 0, 6, 9}, {5, 6, 0, 3}, {9, 9, 3, 0}};
        // adjacency matrix
        int[][] adj = {{1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}};

        // other testcases;
        int[][] test_euler = {
                {0, 3, 0, 1, 0, 0, 0, 0},
                {3, 0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 2, 0, 1, 0, 0},
                {1, 0, 2, 0, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 2, 0, 1},
                {0, 0, 1, 0, 2, 0, 1, 0},
                {0, 0, 0, 0, 0, 1, 0, 3},
                {0, 0, 0, 0, 1, 0, 3, 0}
        };
        int[][] test_MST1 = {{0, 10, 15, 20}, {10, 0, 35, 25}, {15, 35, 0, 30}, {20, 25, 30, 0}};
        int[][] test_MST2 = {{0, 4, 3, 0, 0, 0}, {4, 0, 1, 2, 0, 0}, {3, 1, 0, 4, 0, 0}, {0, 2, 4, 0, 2, 0},
                {0, 0, 0, 2, 0, 6}, {0, 0, 0, 0, 6, 0}};
        int[][] test_MST_euler = {{0, 0, 1, 0}, {0, 0, 1, 0}, {1, 1, 0, 1}, {0, 0, 1, 0}};

        if (parser.getValue("-mode").equals("TSP")) {
            TSP tsp = new TSP(weight);
            tsp.travel(0,0);
        }
        else if (parser.getValue("-mode").equals("EKP")) {
            EKP ekp = new EKP(test_MST_euler);
            ekp.findCircuit(0);
            ekp.printCircuit();
        }
        else if (parser.getValue("-mode").equals("NN")) {
            NN nn = new NN(weight);
            nn.travel();
            nn.printTour();
        }
        else if (parser.getValue("-mode").equals("MST")) {
            MST mst = new MST(weight);
            mst.buildMST(0);
        }
        else
            throw new RuntimeException("please specify your mode!");
    }
}
