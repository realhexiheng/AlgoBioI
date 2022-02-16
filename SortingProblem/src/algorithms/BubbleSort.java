package algorithms;

/**
 * @Author Xiheng He
 * @ClassName algorithms.BubbleSort
 * @Description TODO
 * @Date 2021/05/2021/5/22/2:13
 * @Version 1.0
 */
public class BubbleSort implements Sort {

    private long nComp = 0;
    private long time = 0;

    /**
     * sort method
     * @param array
     * @return
     */
    @Override
    public int[] sort(int[] array) {

        nComp = 0;
        long start = System.currentTimeMillis();

        for (int k = 0; k < array.length - 1; k++, nComp++)  {
            boolean isSorted = true;

            for (int i = 1; i < array.length - k; i++, nComp++)  {
                nComp++;
                if (array[i] < array[i - 1]){
                    int tempVariable = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = tempVariable;

                    isSorted = false;
                }
            }

            nComp++;
            if (isSorted)
                break;
        }

        long end = System.currentTimeMillis();
        this.time = end - start;

        return array;
    }

    @Override
    public long getTotalOfComparison() {
        return this.nComp;
    }

    @Override
    public long getElapsedTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return "Bubble Sort";
    }
}
