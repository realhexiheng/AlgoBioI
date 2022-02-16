package algorithms;

/**
 * @Author Xiheng He
 * @ClassName algorithms.BucketSort
 * @Description TODO
 * @Date 2021/05/2021/5/22/2:52
 * @Version 1.0
 */
public class BucketSort implements Sort {

    private long nComp = 0;
    private long time = 0;
    private int numBuckets;
    private boolean findBuckets = false;

    public BucketSort() {
        this.findBuckets = true;
    }

    /**
     * BucketSort method
     * @param array
     * @return
     */
    @Override
    public int[] sort(int[] array) {

        int min = Integer.MAX_VALUE;
        if(findBuckets){
            int max = 0;
            for (int i = 0; i < array.length; i++) {
                if (array[i] > max) max = array[i];
                if (array[i] < min) min = array[i];
            }
            this.numBuckets = max - min;
        }

        nComp = 0;
        long start = System.currentTimeMillis();

        int [] bucket = new int[numBuckets + 1];

        for (int i = 0; i < bucket.length; i++) {
            bucket[i] = 0;
        }

        for (int i = 0; i < array.length; i++) {
            // watch out  array[i] - min = bucket start value
            bucket[array[i] - min]++;
        }
        nComp += array.length;

        int outPos = 0;
        for (int i = 0; i < bucket.length; i++) {
            nComp++;
            for (int j = 0; j < bucket[i]; j++) {
                array[outPos++] = i;
                nComp++;
            }
        }

        long end = System.currentTimeMillis();
        this.time = end - start;

        return array;
    }

    @Override
    public long getTotalOfComparison() {
        return nComp;
    }


    @Override
    public long getElapsedTime(){
        return time;
    }

    @Override
    public String toString(){
        return "Bucket Sort";
    }
}
