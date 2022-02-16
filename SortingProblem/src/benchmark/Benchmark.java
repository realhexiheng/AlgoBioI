package benchmark;

import algorithms.*;

import java.util.ArrayList;

/**
 * @Author Xiheng He
 * @ClassName Benchmark
 * @Description TODO
 * @Date 2021/05/2021/5/22/4:07
 * @Version 1.0
 */
public class Benchmark {

    private TestCaseGenerator generator;
    private BubbleSort bubbleSort;
    private MergeSort mergeSort;
    private BucketSort bucketSort;
    private String singleTestRes;
    private ArrayList<String> MultiTestRes;


    /**
     * this method run Benchmarks
     * @param sortAlgo
     * @param power indicates the scale of input array for a single test and the end-scale of input array for multi-tests.
     * @param singleTest
     * @param duplicated
     */
    public void run(Sort sortAlgo, int power, boolean singleTest, boolean duplicated) {
        this.generator = new TestCaseGenerator();
        if(singleTest) {
            int length = (int) Math.pow(10, power);
            int[] singleTestCase = this.generator.randomArrayGenerator(length, duplicated);
            sortAlgo.sort(singleTestCase);

            long time = sortAlgo.getElapsedTime();
            long nComp = sortAlgo.getTotalOfComparison();

            this.singleTestRes = sortAlgo.toString() + " ||" + "\t" + time + "\t" + nComp;
        }
        else {
            bubbleSort = new BubbleSort();
            mergeSort = new MergeSort();
            bucketSort = new BucketSort();

            int startSize = 100;
            this.MultiTestRes = new ArrayList<>();
            this.MultiTestRes.add(bubbleSort.toString() + "\t||" + "\t");

            for (int i = 0; i < power; i++) {
                int[] multiTestCase = this.generator.randomArrayGenerator(startSize, duplicated);
                bubbleSort.sort(multiTestCase);
                long time = bubbleSort.getElapsedTime();
                this.MultiTestRes.add(time + "\t");
                // nComps[i] = sortAlgo.getTotalOfComparison();
                startSize *= 10;
            }
            this.MultiTestRes.add("\n");

            startSize = 100;
            this.MultiTestRes.add(mergeSort.toString() + "\t||" + "\t");


            for (int i = 0; i < power; i++) {
                int[] multiTestCase = this.generator.randomArrayGenerator(startSize, duplicated);
                mergeSort.sort(multiTestCase);
                long time = mergeSort.getElapsedTime();
                this.MultiTestRes.add(time + "\t");
                // nComps[i] = sortAlgo.getTotalOfComparison();
                startSize *= 10;
            }
            this.MultiTestRes.add("\n");

            startSize = 100;
            this.MultiTestRes.add(bucketSort.toString() + "\t||" + "\t");

            for (int i = 0; i < power; i++) {
                int[] multiTestCase = this.generator.randomArrayGenerator(startSize, duplicated);
                bucketSort.sort(multiTestCase);
                long time = bucketSort.getElapsedTime();
                this.MultiTestRes.add(time + "\t");
                startSize *= 10;
            }

        }
    }

    public void getResult(boolean singleTest) {
        if (singleTest)
            System.out.println(this.singleTestRes);
        else {
            for (String res: this.MultiTestRes)
                System.out.print(res);
        }
    }
}
