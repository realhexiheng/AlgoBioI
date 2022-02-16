package benchmark;

import java.util.*;

/**
 * @Author Xiheng He
 * @ClassName TestCaseGenerator
 * @Description TODO
 * @Date 2021/05/2021/5/22/4:08
 * @Version 1.0
 */
public class TestCaseGenerator {

    private Random generator = new Random();

    public int[] randomArrayGenerator(int size, boolean duplicated) {
        int[] res = new int[size];
        if (duplicated) {
            for (int i = 0; i < size; i++) {
                res[i] = generator.nextInt(size);
            }
        }
        else {
            res = sortedArrayGenerator(size);
            shuffle(res);

        }
        return res;
    }

    public int[] sortedArrayGenerator(int size) {
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = i;
        }
        return res;
    }

    public int[] invertArray(int[] target){

        int[] res = new int[target.length];

        for (int i = 0; i < target.length; i++) {
            res[i] = target[target.length - 1 - i];
        }

        return res;
    }

    private void swap(int [] a, int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private void shuffle(int [] arr) {
        int length = arr.length;
        for ( int i = length; i > 0; i-- ){
            int randInd = generator.nextInt(i);
            swap(arr, randInd, i - 1);
        }
    }
}
