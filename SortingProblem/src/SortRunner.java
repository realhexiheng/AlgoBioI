import algorithms.*;
import algorithms.BucketSort;
import algorithms.MergeSort;
import benchmark.TestCaseGenerator;

/**
 * @Author Xiheng He
 * @ClassName SortRunner
 * @Description TODO
 * @Date 2021/05/2021/5/22/4:07
 * @Version 1.0
 */
public class SortRunner {

    public static void main(String[] args) {

        TestCaseGenerator generator = new TestCaseGenerator();

        int[] test = generator.randomArrayGenerator(100000, true);

        BubbleSort bubbleSort = new BubbleSort();
        MergeSort mergeSort = new MergeSort();
        BucketSort bucketSort = new BucketSort();

        int[]res = bucketSort.sort(test);

        long time = bucketSort.getElapsedTime();
        long nCmp = bucketSort.getTotalOfComparison();

        System.out.println(time);
        System.out.println(nCmp);

        for(int x: res)
            System.out.print(x + ", ");
    }
}
