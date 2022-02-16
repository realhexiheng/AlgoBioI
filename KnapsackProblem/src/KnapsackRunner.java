import algorithm.MaxKnapsack;
import algorithm.OptionParser;

/**
 * @Author Xiheng He
 * @ClassName KnapsackRunner
 * @Description TODO
 * @Date 2021/06/2021/6/21/2:25
 * @Version 1.0
 */
public class KnapsackRunner {

    public static void main(String[] args) {
        OptionParser parser = new OptionParser("-mode", "-r");
        parser.setMandatoryOptions("-mode");
        parser.setUsage("Usage: -mode [PPTAS|FPTAS] -r" + "\n" + "default case: price: {1, 6, 18, 22, 28}" +
                " weight: {1, 2, 5, 6, 7} with capacity = 20");
        parser.setDouble("-r");
        parser.parseArguments(args);

        // testcase 1
        int[] value = new int[] {1, 6, 18, 22, 28 };
        int[] weight = new int[] {1, 2, 5, 6, 7};
        int volume = 20;
        int n = value.length;

        if (parser.getValue("-mode").equals("PPTAS")) {
            MaxKnapsack maxKnapsack_PPTAS = new MaxKnapsack(n, value, weight, volume);
            maxKnapsack_PPTAS.printResult(maxKnapsack_PPTAS.pptas());
        }
        else if (parser.getValue("-mode").equals("FPTAS")) {
            MaxKnapsack maxKnapsack_FPTAS = new MaxKnapsack(n, value, weight, volume, parser.getDouble("-r"));
            maxKnapsack_FPTAS.printResult(maxKnapsack_FPTAS.fptas());
        }
    }
}
