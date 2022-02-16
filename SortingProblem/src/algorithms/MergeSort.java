package algorithms;

/**
 * @Author Xiheng He
 * @ClassName algorithms.MergeSort
 * @Description TODO
 * @Date 2021/05/2021/5/22/2:37
 * @Version 1.0
 */
public class MergeSort implements Sort {

    private long nComp = 0;
    private long time = 0;

    @Override
    public int[] sort(int[] array) {
        nComp = 0;
        long start = System.currentTimeMillis();

        nComp++;
        if(array.length > 1){
            int elementsInA1 = array.length/2;
            int elementsInA2 = array.length - elementsInA1;

            int arr1[] = new int[elementsInA1];
            int arr2[] = new int[elementsInA2];

            for(int i = 0; i < elementsInA1; i++, nComp++)
                arr1[i] = array[i];

            for(int i = elementsInA1; i < elementsInA1 + elementsInA2; i++, nComp++)
                arr2[i - elementsInA1] = array[i];

            sort(arr1);
            sort(arr2);
            int i = 0, j = 0, k = 0;


            while(arr1.length != j && arr2.length != k) {
                nComp+=4;
                if(arr1[j] <= arr2[k]) {
                    array[i] = arr1[j];
                    i++;
                    j++;
                } else {
                    array[i] = arr2[k];
                    i++;
                    k++;
                }
            }

            while(arr1.length != j) {
                array[i] = arr1[j];
                i++;
                j++;
            }

            while(arr2.length != k) {
                array[i] = arr2[k];
                i++;
                k++;
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
        return "Merge Sort";
    }
}
