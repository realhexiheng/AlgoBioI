import algorithms.*;
import benchmark.*;
/**
 * @Author Xiheng He
 * @ClassName BenchmarkRunner
 * @Description TODO
 * @Date 2021/05/2021/5/22/4:07
 * @Version 1.0
 */
public class BenchmarkRunner {

    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        Benchmark benchmark = new Benchmark();
        benchmark.run(bubbleSort, 5, false, true);
        benchmark.getResult(false);
    }
}
